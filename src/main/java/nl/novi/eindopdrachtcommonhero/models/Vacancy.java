package nl.novi.eindopdrachtcommonhero.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Vacancy {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    @OneToOne()
    private User jobPublisher;
    private int aantalUur;
    private boolean jobOffer; //TODO: twijfels over de naam.
    private String beschrijving;

    @OneToOne
    FileUploadResponse file;

    public Vacancy(User jobPublisher, int aantalUur, boolean jobOffer, String beschrijving) {
        this.jobPublisher = jobPublisher;
        this.aantalUur = aantalUur;
        this.jobOffer = jobOffer;
        this.beschrijving = beschrijving;
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

    public FileUploadResponse getFile() {
        return file;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setJobPublisher(User jobPublisher) {
        this.jobPublisher = jobPublisher;
    }

    public void setAantalUur(int aantalUur) {
        this.aantalUur = aantalUur;
    }

    public void setJobOffer(boolean jobOffer) {
        this.jobOffer = jobOffer;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
