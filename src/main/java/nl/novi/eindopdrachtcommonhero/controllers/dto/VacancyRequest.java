package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;
import nl.novi.eindopdrachtcommonhero.models.User;

public class VacancyRequest {
    public User publisher;
    public int hours;
    public String searchOrOffer;
    public String description;
    public FileUploadResponse file;
}

