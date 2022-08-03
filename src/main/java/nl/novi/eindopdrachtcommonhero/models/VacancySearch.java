package nl.novi.eindopdrachtcommonhero.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="vacancy_search")
public class VacancySearch {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "vacancy_search_sequence"),
                    @Parameter(name = "initial_value", value = "1003"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;

    private String publisher;
    private String title;
    private int hours;
    private String description;
    private String city;
    private String repeats;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

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
    public String getCity() {
        return city;
    }
    public String getRepeats() {
        return repeats;
    }
    public Date getDate() {
        return date;
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
    public void setCity(String city) {
        this.city = city;
    }
    public void setRepeats(String repeats) {
        this.repeats = repeats;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
