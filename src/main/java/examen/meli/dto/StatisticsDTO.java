package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public class StatisticsDTO {

    @JsonProperty("distancia_minima")
    private Double distance_min;

    @JsonProperty("distancia_maxima")
    private Double distance_max;

    @JsonProperty("distancia_promedio")
    private Double distance_prom;

    public String getDistance_min() {
        return distance_min + " KM";
    }

    public void setDistance_min(Double distance_min) {
        this.distance_min = distance_min;
    }

    public String getDistance_max() {
        return distance_max + " KM";
    }

    public void setDistance_max(Double distance_max) {
        this.distance_max = distance_max;
    }

    public String getDistance_prom() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(distance_prom) + " KM";
    }

    public void setDistance_prom(Double distance_prom) {
        this.distance_prom = distance_prom;

    }
}
