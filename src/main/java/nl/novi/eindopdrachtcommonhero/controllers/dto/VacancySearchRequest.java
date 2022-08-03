package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;

import javax.persistence.GeneratedValue;
import java.util.Date;

public class VacancySearchRequest {

    @GeneratedValue(generator = "sequence-generator")
    public Long id;

    public String publisher;
    public String title;
    public int hours;
    public String description;
    public String city;
    public String repeats;
    public Date date;

    public FileUploadResponse file;

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


}

