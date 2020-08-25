package examen.meli.entity;

import javax.persistence.*;

@Entity
@Table(name = "log")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "distance")
    private float distance;

    @Column(name = "invocations")
    private Long invocations;

    public LogEntity(){}

    public LogEntity (String country, Long distance){
        this.setCountry(country);
        this.setDistance(distance);
        this.setInvocations(1L);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Long getInvocations() {
        return invocations;
    }

    public void setInvocations(Long invocations) {
        this.invocations = invocations;
    }
}
