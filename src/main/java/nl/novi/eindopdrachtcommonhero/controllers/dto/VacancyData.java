package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.User;

public class VacancyData {
    public Long id;
    public User publisher;
    public int hours;
    public String searchOrOffer;
    public String description;

    public VacancyData(Long id, User publisher, int hours, String searchOrOffer, String description) {
        this.id = id;
        this.publisher = publisher;
        this.hours = hours;
        this.searchOrOffer = searchOrOffer;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public User getPublisher() {
        return publisher;
    }

    public int getHours() {
        return hours;
    }

    public String getSearchOrOffer() {
        return searchOrOffer;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
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
}
