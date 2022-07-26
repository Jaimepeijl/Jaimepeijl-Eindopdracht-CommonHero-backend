package nl.novi.eindopdrachtcommonhero.models;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="vacancies")
public class Vacancy {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

    private String publisher;
    private String title;
    private int hours;
    private String vactype;
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

    public String getVactype() {
        return vactype;
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

    public void setVactype(String vactype) {
        this.vactype = vactype;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
