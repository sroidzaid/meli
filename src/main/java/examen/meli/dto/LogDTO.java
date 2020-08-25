package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogDTO {


    @JsonProperty("pais")
    private String country;

    @JsonProperty("distancia")
    private String distance;

    @JsonProperty("invocaciones")
    private String invocations;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getInvocations() {
        return invocations;
    }

    public void setInvocations(String invocations) {
        this.invocations = invocations;
    }
}
