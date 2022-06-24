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
    private User publisher;
    private int hours;
    private String searchOrOffer;
    private String description;

    @OneToOne
    FileUploadResponse file;

    public Vacancy(User jobPublisher, int aantalUur, String searchOrOffer, String description) {
        this.publisher = jobPublisher;
        this.hours = aantalUur;
        this.searchOrOffer = searchOrOffer;
        this.description = description;
    }

    public Vacancy() {

    }

    public User getPublisher() {
        return publisher;
    }

    public int getHours() {
        return hours;
    }

    public String isSearchOrOffer() {
        return searchOrOffer;
    }

    public String getDescription() {
        return description;
    }

    public FileUploadResponse getFile() {
        return file;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setPublisher(User jobPublisher) {
        this.publisher = jobPublisher;
    }

    public void setHours(int aantalUur) {
        this.hours = aantalUur;
    }

    public void setSearchOrOffer(boolean jobOffer) {
        this.searchOrOffer = searchOrOffer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
