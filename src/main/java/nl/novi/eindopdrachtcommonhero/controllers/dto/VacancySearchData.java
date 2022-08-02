package nl.novi.eindopdrachtcommonhero.controllers.dto;

import javax.persistence.GeneratedValue;
import java.util.Date;

public class VacancySearchData {
    @GeneratedValue(generator = "sequence-generator")
    public Long id;
    public String publisher;
    private String title;
    public int hours;
    public String description;
    public String city;
    public String repeats;
    public Date date;

    public VacancySearchData(String publisher, String title, int hours, String description, String city, String repeats, Date date) {
        this.publisher = publisher;
        this.title = title;
        this.hours = hours;
        this.description = description;
        this.city = city;
        this.repeats = repeats;
        this.date = date;
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
    public String getCity() {
        return city;
    }
    public String getRepeats() {
        return repeats;
    }
    public Date getDate() {
        return date;
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
}
