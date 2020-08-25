package examen.meli.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class IpEntity {

    private String ip;
    private Date date;
    private String country;
    private String iso_code;
    private String languages;
    private String currency;
    private String times;
    private Long estimated_distance;

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

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public Long getEstimated_distance() {
        return estimated_distance;
    }

    public void setEstimated_distance(Long estimated_distance) {
        this.estimated_distance = estimated_distance;
    }
}
