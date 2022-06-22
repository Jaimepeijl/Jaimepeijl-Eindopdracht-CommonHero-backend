package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.User;

public class VacancyRequest {
    public User jobPublisher;
    public int aantalUur;
    public boolean jobOffer; //TODO: twijfels over de naam.
    public String beschrijving;
    public String afbeelding;
}
