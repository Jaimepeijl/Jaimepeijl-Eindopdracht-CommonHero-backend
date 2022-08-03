package nl.novi.eindopdrachtcommonhero.exceptions;

public class VacancyNotFoundException extends RuntimeException {


    public VacancyNotFoundException() {
        super("De vacature is niet gevonden!");
    }

}
