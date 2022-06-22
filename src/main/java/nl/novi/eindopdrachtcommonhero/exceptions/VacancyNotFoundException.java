package nl.novi.eindopdrachtcommonhero.exceptions;

public class VacancyNotFoundException extends RuntimeException {


    public VacancyNotFoundException() {
        super("The user is not found!");
    }

}
