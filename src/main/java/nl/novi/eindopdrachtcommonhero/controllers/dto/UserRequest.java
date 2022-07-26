package nl.novi.eindopdrachtcommonhero.controllers.dto;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;

public class UserRequest {

    public Long id;
    public String username;
    public String password;
    public String email;
    public String name;
    public String city;
    boolean enabled = true;

    FileUploadResponse file;

}
