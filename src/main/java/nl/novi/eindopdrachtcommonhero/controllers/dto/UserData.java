package nl.novi.eindopdrachtcommonhero.controllers.dto;

import nl.novi.eindopdrachtcommonhero.models.Authority;

import lombok.Data;
import nl.novi.eindopdrachtcommonhero.models.FileUploadResponse;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.util.Set;

@Data
public class UserData {

    @GeneratedValue
    public Long id;

    @Id
    public String username;

    public String password;
    public String email;
    public String name;
    public String city;
    public Set<Authority> authority;
    boolean enabled = true;

    @OneToOne
    FileUploadResponse file;

    public Set<Authority> authorities;

    public UserData(Long id, String username, String password, String email, String name, String city, FileUploadResponse file, Set<Authority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.city = city;
        this.file = file;
        this.authorities = authorities;
    }

    public UserData() {
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

    public FileUploadResponse getFile() {
        return file;
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
    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
}
