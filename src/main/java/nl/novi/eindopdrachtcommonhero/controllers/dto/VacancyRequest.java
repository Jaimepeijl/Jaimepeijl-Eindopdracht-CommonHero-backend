package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;

public class VacancyRequest {
    public Long id;
    public User publisher;
    public int hours;
    public String searchOrOffer;
    public String description;
    public FileUploadResponse file;

    public Long getId() {
        return id;
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
}

