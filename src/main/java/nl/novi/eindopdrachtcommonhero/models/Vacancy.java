package nl.novi.eindopdrachtcommonhero.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Vacancy {

    @Id
//    @GeneratedValue(generator = "sequence-generator")
//    @GenericGenerator(
//            name = "sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "100"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
//            }
//    )
    @GeneratedValue
    private Long id;

    @OneToOne()
    private User publisher;
    private String title;
    private int hours;
    private String searchOrOffer;
    private String description;

    @OneToOne
    FileUploadResponse file;

    public Long getId() {
        return id;
    }

    public User getPublisher() {
        return publisher;
    }

    public String getTitle(){
        return title;
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

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setSearchOrOffer(String searchOrOffer) {
        this.searchOrOffer = searchOrOffer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
