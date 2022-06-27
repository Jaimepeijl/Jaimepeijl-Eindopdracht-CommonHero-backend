package nl.novi.eindopdrachtcommonhero.controllers.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.novi.eindopdrachtcommonhero.models.Authority;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

public class UserData {

    @Id
    @GeneratedValue
    public Long id;

    public String username;
    public String password;
    public String email;
    public String name;
    public String city;
    @JsonSerialize
    public Set<Authority> authorities;

    public UserData(Long id, String username, String password, String email, String name, String city) {
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
    public Set<Authority> getAuthorities() {
        return authorities;
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
    public void setEmail(String email) {
        this.email = email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }


}
