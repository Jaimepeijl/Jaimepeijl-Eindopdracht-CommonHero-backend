package nl.novi.eindopdrachtcommonhero.models;

import javax.persistence.*;

@Entity
@Table(name="vacancy_offer")
public class VacancyOffer {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
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
