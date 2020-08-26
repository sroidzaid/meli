package examen.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DecimalFormat;

public class MinMaxPromDTO {

    @JsonProperty("filtro")
    private String filtro;

    @JsonProperty("distancia")
    private Double distance;


    public MinMaxPromDTO(){}

    public MinMaxPromDTO (String filtro, Double distancia){
        this.setFiltro(filtro);
        this.setDistance(distancia);
    }


    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }


    public String getDistance() {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(distance) + " KM";
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
