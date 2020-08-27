package examen.meli.service.Impl;

import examen.meli.entity.LogEntity;
import examen.meli.exception.ConexionErrorException;
import examen.meli.exception.DataNotFoundException;
import examen.meli.exception.IpInvalidException;
import examen.meli.model.*;
import examen.meli.repository.LogRepository;
import examen.meli.service.IpInformationService;
import examen.meli.util.URL_APIs;
import examen.meli.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class IpInformationServiceImpl implements IpInformationService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private RestTemplate restTemplate;

    public IpInformation findByIP(String ip){

        if (Utilities.isIpAdress(ip)) {

            try{
                Country resultCountry = restTemplate.getForObject(URL_APIs.URL_GEO_COUNTRY +ip, Country.class);
                if(resultCountry.getCountryCode() == null || resultCountry.getCountryCode().equals("")){
                    throw new DataNotFoundException();

                }else{
                    CountryInformation resultCountryInformation = restTemplate.getForObject(URL_APIs.URL_INFO_COUNTRY +resultCountry.getCountryCode()+"?fields=latlng;currencies;languages;timezones;translations;", CountryInformation.class);
                    CurrencyInformation resultCurrencyInformation = restTemplate.getForObject(URL_APIs.URL_INFO_CURRENCY, CurrencyInformation.class);

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
                    double distanceKM = Utilities.distanceFromBsAs(resultCountryInformation.getLatlng().get(0), resultCountryInformation.getLatlng().get(1));

                    IpInformation ipInformation = new IpInformation();
                    ipInformation.setIp(ip);
                    ipInformation.setDate(new Date());
                    ipInformation.setCountry(countryDescSpanish + " ("+resultCountry.getCountryName()+")");
                    ipInformation.setIso_code(resultCountry.getCountryCode());
                    ipInformation.setLanguages(listLanguages);
                    ipInformation.setCurrency(listCurrency);
                    ipInformation.setTimes(listTimeZone);
                    ipInformation.setEstimated_distance(distanceKM);

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
                    return ipInformation;
                }

            }catch (DataNotFoundException e){
                throw e;
            } catch (Exception ex){
                throw ex;
            }

        }else{
            throw new IpInvalidException();
        }

    }


}
