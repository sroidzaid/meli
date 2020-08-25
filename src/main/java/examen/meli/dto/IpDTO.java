package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpDTO {


    @JsonProperty("ip")
    private String ip;

    @JsonProperty("date")
    private String date;

    @JsonProperty("country")
    private String country;

    @JsonProperty("iso_code")
    private String iso_code;

    @JsonProperty("languages")
    private String languages;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("times")
    private String times;

    @JsonProperty("estimated_distance")
    private String estimated_distance;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public String getEstimated_distance() {
        return estimated_distance;
    }

    public void setEstimated_distance(String estimated_distance) {
        this.estimated_distance = estimated_distance;
    }
}
