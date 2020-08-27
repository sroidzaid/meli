package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;
import java.util.List;

public class StatisticsDTO {

    @JsonProperty("distancia_promedio")
    private Double distance_prom;

    @JsonProperty("list_min")
    private List<LogDTO> list_min;

    @JsonProperty("list_max")
    private List<LogDTO> list_max;


    public List<LogDTO> getList_min() {
        return list_min;
    }

    public void setList_min(List<LogDTO> list_min) {
        this.list_min = list_min;
    }

    public List<LogDTO> getList_max() {
        return list_max;
    }

    public void setList_max(List<LogDTO> list_max) {
        this.list_max = list_max;
    }

    public String getDistance_prom() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(distance_prom) + " KM";
    }

    public void setDistance_prom(Double distance_prom) {
        this.distance_prom = distance_prom;

    }
}
