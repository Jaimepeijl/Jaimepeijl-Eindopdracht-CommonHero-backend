package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.User;

public class VacancyData {
    public Long id;
    public User publisher;
    private String title;
    public int hours;
    public String searchOrOffer;
    public String description;

    public VacancyData(Long id, User publisher, String title, int hours, String searchOrOffer, String description) {
        this.id = id;
        this.publisher = publisher;
        this.title = title;
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

    public String getTitle(){
        return title;
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
}
