package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;

import javax.persistence.GeneratedValue;

public class VacancyRequest {

    @GeneratedValue
    public Long id;

    public String publisher;
    private String title;
    public int hours;
    public String searchOrOffer;
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



    public String isSearchOrOffer() {
        return searchOrOffer;
    }

    public String getDescription() {
        return description;
    }

    public FileUploadResponse getFile() {
        return file;
    }
}

