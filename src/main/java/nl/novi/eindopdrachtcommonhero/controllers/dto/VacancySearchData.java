package nl.novi.eindopdrachtcommonhero.controllers.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class VacancySearchData {
    @GeneratedValue(generator = "sequence-generator")
    public Long id;
    public String publisher;
    private String title;
    public int hours;
    public String description;

    public VacancySearchData(String publisher, String title, int hours, String description) {
        this.publisher = publisher;
        this.title = title;
        this.hours = hours;
        this.description = description;
    }

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
}
