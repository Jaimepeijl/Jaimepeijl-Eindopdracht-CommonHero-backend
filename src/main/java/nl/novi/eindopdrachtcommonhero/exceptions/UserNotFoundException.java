package nl.novi.eindopdrachtcommonhero.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public UserNotFoundException() {
        super("The user is not found!");
    }

}
