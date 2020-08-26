package examen.meli.service.Impl;

import examen.meli.model.IpInformation;
import examen.meli.entity.LogEntity;
import examen.meli.model.*;
import examen.meli.repository.LogRepository;
import examen.meli.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class IpServiceImpl implements IpService {

    @Autowired
    private LogRepository logRepository;

    public IpInformation findByIP(String ip){
        IpInformation ipInformation = new IpInformation();

//        if (!validIP(ip)) {
//            throw new IpInvalidException(IpEntity.class.getSimpleName(), ip);
//        }

        //Consumir Servicios
        ip = "5.6.7.8";

        RestTemplate restTemplate = new RestTemplate();
        Country resultCountry = restTemplate.getForObject("https://api.ip2country.info/ip?"+ip, Country.class);
        CountryInformation resultCountryInformation = restTemplate.getForObject("https://restcountries.eu/rest/v2/alpha/"+resultCountry.getCountryCode()+"?fields=currencies;languages;timezones;translations", CountryInformation.class);
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

//        “ip”:“83.44.196.93”,
//        “date”:“21/11/2020 15:12:03”,
//        “country”:”España (spain)”,
//        “iso_code”:”es”,
//        “languages”: [“Español (es)”],
//        “currency”: “EUR (1 EUR = 1.0631 U$S)”,
//        “times”: [“20:01:23 (UTC)”, “19:01:23 (UTC-01)”],
//        “estimated_distance”: “10270 kms”



        Long distance = 10270L;

        LogEntity logEntity = logRepository.findByCountry(countryDescSpanish);
        if(logEntity!=null){
            long invocaciones = logEntity.getInvocations();
            invocaciones++;
            logEntity.setInvocations(invocaciones);
            logRepository.save(logEntity);
        }else{
            LogEntity logAGuardar = new LogEntity(countryDescSpanish, distance);
            logRepository.save(logAGuardar);
        }


        return ipInformation;
    }



    private static boolean validIP(String ip) {
        if (ip == null || ip.isEmpty()) return false;
            ip = ip.trim();
        if ((ip.length() < 6) & (ip.length() > 15)) return false;

        try {
            Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        } catch (PatternSyntaxException ex) {
            return false;
        }
    }
}
