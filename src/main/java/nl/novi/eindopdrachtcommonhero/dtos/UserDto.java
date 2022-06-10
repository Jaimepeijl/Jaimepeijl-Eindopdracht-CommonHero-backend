package nl.novi.eindopdrachtcommonhero.dtos;

public class UserDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;
    public String name;
    public String city;

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
    public String GetName() {
        return name;
    }
    public String GetCity() {
        return city;
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
