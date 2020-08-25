package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogDTO {


    @JsonProperty("pais")
    private String country;

    @JsonProperty("distancia")
    private Long distance;

    @JsonProperty("invocaciones")
    private Long invocations;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getInvocations() {
        return invocations;
    }

    public void setInvocations(Long invocations) {
        this.invocations = invocations;
    }
}
