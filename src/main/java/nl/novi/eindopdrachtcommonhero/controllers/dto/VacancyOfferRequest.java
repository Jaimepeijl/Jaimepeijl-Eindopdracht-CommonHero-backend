package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class VacancyOfferRequest {

    @GeneratedValue(strategy= GenerationType.TABLE)
    public Long id;

    public String publisher;
    public String title;
    public int hours;
    public String description;

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

    public FileUploadResponse getFile() {
        return file;
    }
}

