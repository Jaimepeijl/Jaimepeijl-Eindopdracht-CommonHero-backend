package nl.novi.eindopdrachtcommonhero.exceptions;

public class UserNotFoundException extends RuntimeException {


    public UserNotFoundException() {
        super("The user is not found!");
    }

}
