package nl.novi.eindopdrachtcommonhero.dtos;

public class UserData {

    public Long id;
    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    public String name;
    public String city;

    public UserData(Long id, String username, String password, Boolean enabled, String apikey, String email, String name, String city) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.apikey = apikey;
        this.email = email;
        this.name = name;
        this.city = city;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Boolean getEnabled() {
        return enabled;
    }
    public String getApikey() {
        return apikey;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public void setApikey(String apikey) {
        this.apikey = apikey;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }


}
