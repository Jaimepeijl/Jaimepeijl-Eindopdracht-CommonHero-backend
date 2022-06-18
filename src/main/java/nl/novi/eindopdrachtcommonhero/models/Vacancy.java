package nl.novi.eindopdrachtcommonhero.models;

import javax.persistence.*;

@Entity
public class Vacancy {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne()
    private User jobPublisher;
    private int aantalUur;
    private boolean jobOffer; //TODO: twijfels over de naam.
    private String beschrijving;
    private String afbeelding;

    public Vacancy(Long id, int aantalUur, boolean jobOffer, String beschrijving, String afbeelding, User jobPublisher) {
        this.id = id;
        this.jobPublisher = jobPublisher;
        this.aantalUur = aantalUur;
        this.jobOffer = jobOffer;
        this.beschrijving = beschrijving;
        this.afbeelding = afbeelding;
    }

    public Vacancy() {
    }

    public User getJobPublisher() {
        return jobPublisher;
    }

    public int getAantalUur() {
        return aantalUur;
    }

    public boolean isJobOffer() {
        return jobOffer;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public String getAfbeelding() {
        return afbeelding;
    }
}
