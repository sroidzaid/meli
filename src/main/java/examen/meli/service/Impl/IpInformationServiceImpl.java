package examen.meli.service.Impl;

import examen.meli.dto.IpInformationDTO;
import examen.meli.entity.LogEntity;
import examen.meli.exception.*;
import examen.meli.model.*;
import examen.meli.repository.LogRepository;
import examen.meli.service.IpInformationService;
import examen.meli.util.URL_APIs;
import examen.meli.util.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;


@Service
public class IpInformationServiceImpl implements IpInformationService {
    public static final Semaphore semaphore = new Semaphore(1, false);
    @Autowired
    private LogRepository logRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;


    @Async
    public  CompletableFuture<IpInformationDTO> findByIP(String ip) {

        if (Utilities.isIpAdress(ip)) {

            try{
                Country resultCountry = restTemplate.getForObject(URL_APIs.URL_GEO_COUNTRY +ip, Country.class);
                if(resultCountry.getCountryCode() == null || resultCountry.getCountryCode().equals("")){
                    throw new DataNotFoundException();

                }else{
                    CountryInformation resultCountryInformation = restTemplate.getForObject(URL_APIs.URL_INFO_COUNTRY +resultCountry.getCountryCode()+"?fields=latlng;currencies;languages;timezones;translations;", CountryInformation.class);
                    CurrencyInformation resultCurrencyInformation = restTemplate.getForObject(URL_APIs.URL_INFO_CURRENCY, CurrencyInformation.class);

                    if(!resultCurrencyInformation.isSuccess()){
                        throw new CurrencyServiceError();
                    }else{

                        List<String> listLanguages = new ArrayList<>();
                        for(Language language : resultCountryInformation.getLanguages()){
                            listLanguages.add(language.getName() + " ("+language.getIso639_1()+")");
                        }

                        List<String> listCurrency = new ArrayList<>();
                        for(Currency currency : resultCountryInformation.getCurrencies()){
                            String cotizacion = resultCurrencyInformation.getRates().get(currency.getCode());
                            if(cotizacion == null || cotizacion.equals("")){
                                cotizacion = "No disponible";
                            }else{
                                cotizacion = cotizacion +" "+ currency.getCode();
                            }
                            listCurrency.add(currency.getName() + " (1 "+resultCurrencyInformation.getBase()+" = "+ cotizacion +")");
                        }

                        List<String> listTimeZone = new ArrayList<>();
                        for(String timeZone : resultCountryInformation.getTimezones()){
                            String strDate = Utilities.calcTime(timeZone);
                            listTimeZone.add(strDate + " ("+timeZone+")");
                        }

                        String countryDescSpanish = resultCountryInformation.getTranslations().get("es");
                        String countryDesc = countryDescSpanish + " ("+resultCountry.getCountryName()+")";
                        double distanceKM = Utilities.distanceFromBsAs(resultCountryInformation.getLatlng().get(0), resultCountryInformation.getLatlng().get(1));
                        IpInformation ipInformation = new IpInformation(ip,countryDesc,resultCountry.getCountryCode(),listLanguages,listCurrency,listTimeZone,distanceKM);

                        saveLog(countryDescSpanish,distanceKM);

                        IpInformationDTO ipInformationDTO = modelMapper.map(ipInformation,IpInformationDTO.class);
                        return CompletableFuture.completedFuture(ipInformationDTO);
                    }
                }

            }catch (CurrencyServiceError | DataNotFoundException e){
                throw e;
            }catch (HttpClientErrorException e){
                throw new ConexionErrorException();
            }catch (Exception ex){
                try {
                    throw ex;
                } catch (InterruptedException e) {
                    throw new ApiException();
                }
            }

        }else{
            throw new IpInvalidException();
        }

    }

    private void saveLog(String countryDescSpanish, Double distanceKM) throws InterruptedException {

        semaphore.acquire();
        LogEntity logEntity = logRepository.findByCountry(countryDescSpanish);
        if(logEntity!=null){
            long invocaciones = logEntity.getInvocations();
            invocaciones++;
            logEntity.setInvocations(invocaciones);
            logRepository.save(logEntity);
        }else{
            LogEntity logAGuardar = new LogEntity(countryDescSpanish, distanceKM);
            logRepository.save(logAGuardar);
        }
        semaphore.release();
    }
}
