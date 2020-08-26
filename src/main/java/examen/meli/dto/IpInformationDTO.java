package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IpInformationDTO {


    @JsonProperty("ip")
    private String ip;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("country")
    private String country;

    @JsonProperty("iso_code")
    private String iso_code;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("times")
    private List<String> times;

    @JsonProperty("estimated_distance")
    private Double estimated_distance;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = format.format(date);
        return dateString;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
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

    public String getEstimated_distance() {
        DecimalFormat df = new DecimalFormat("#.00");
         return df.format(estimated_distance) + " KM";
    }

    public void setEstimated_distance(Double estimated_distance) {
        this.estimated_distance = estimated_distance;
    }
}
