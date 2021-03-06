package examen.meli.model;


import java.util.Date;
import java.util.List;

public class IpInformation {

    private String ip;
    private Date date;
    private String country;
    private String iso_code;
    private List<String> languages;
    private List<String> currency;
    private List<String> times;
    private Double estimated_distance;


    public IpInformation(String ip,String country,String iso_code,List<String> languages,List<String> currency,List<String> times,Double estimated_distance){
        this.setIp(ip);
        this.setDate(new Date());
        this.setCountry(country);
        this.setIso_code(iso_code);
        this.setLanguages(languages);
        this.setCurrency(currency);
        this.setTimes(times);
        this.setEstimated_distance(estimated_distance);
    }



    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public List<String> getCurrency() {
        return currency;
    }

    public void setCurrency(List<String> currency) {
        this.currency = currency;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public Double getEstimated_distance() {
        return estimated_distance;
    }

    public void setEstimated_distance(Double estimated_distance) {
        this.estimated_distance = estimated_distance;
    }
}
