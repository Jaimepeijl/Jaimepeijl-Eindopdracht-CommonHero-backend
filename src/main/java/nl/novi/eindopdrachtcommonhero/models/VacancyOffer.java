package nl.novi.eindopdrachtcommonhero.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name="vacancy_offer")
public class VacancyOffer {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "vacancy_offer_sequence"),
                    @Parameter(name = "initial_value", value = "1004"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    private String publisher;
    private String title;
    private int hours;
    private String description;

    @OneToOne
    FileUploadResponse file;

    public Long getId() {
        return id;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getTitle(){
        return title;
    }

    public int getHours() {
        return hours;
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

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
