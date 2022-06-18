package nl.novi.eindopdrachtcommonhero.controllers.dto;

import javax.persistence.Column;

public class UserRequest {

    public Long id;
    public String username;
    public String password;
    public boolean enabled;
    public String apikey;
    public String email;
    public String name;
    public String city;

}
