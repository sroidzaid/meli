package examen.meli.service.Impl;

import examen.meli.entity.LogEntity;
import examen.meli.exception.IpInvalidException;
import examen.meli.model.*;
import examen.meli.repository.LogRepository;
import examen.meli.service.IpInformationService;
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


    public IpInformation findByIP(String ip){

        if (Utilities.isIpAdress(ip)) {

            IpInformation ipInformation = new IpInformation();
            RestTemplate restTemplate = new RestTemplate();
            Country resultCountry = restTemplate.getForObject("https://api.ip2country.info/ip?"+ip, Country.class);
            CountryInformation resultCountryInformation = restTemplate.getForObject("https://restcountries.eu/rest/v2/alpha/"+resultCountry.getCountryCode()+"?fields=latlng;currencies;languages;timezones;translations;", CountryInformation.class);
            CurrencyInformation resultCurrencyInformation = restTemplate.getForObject("http://data.fixer.io/api/latest?access_key=7f7ff77e1aa156330036406b6f9d2932", CurrencyInformation.class);

            ipInformation.setIp(ip);
            ipInformation.setDate(new Date());
            String countryDescSpanish = resultCountryInformation.getTranslations().get("es");
            ipInformation.setCountry(countryDescSpanish + " ("+resultCountry.getCountryName()+")");
            ipInformation.setIso_code(resultCountry.getCountryCode());

            List<String> listLanguages = new ArrayList<>();
            for(Language language : resultCountryInformation.getLanguages()){
                listLanguages.add(language.getName() + " ("+language.getIso639_1()+")");
            }
            ipInformation.setLanguages(listLanguages);

            List<String> listCurrency = new ArrayList<>();
            for(Currency currency : resultCountryInformation.getCurrencies()){
                listCurrency.add(currency.getName() + " (1 "+currency.getCode()+" = "+ resultCurrencyInformation.getRates().get(currency.getCode())+" "+currency.getCode() +")");
            }
            ipInformation.setCurrency(listCurrency);

            double distanceKM = Utilities.distanceFromBsAs(resultCountryInformation.getLatlng().get(0), resultCountryInformation.getLatlng().get(1));

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

        }else{
            throw new IpInvalidException(IpInformation.class.getSimpleName(), ip);
        }

    }


}
