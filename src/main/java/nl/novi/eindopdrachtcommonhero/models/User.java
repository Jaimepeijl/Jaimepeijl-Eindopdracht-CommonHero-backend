package nl.novi.eindopdrachtcommonhero.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name ="users")
public class User {

    @GeneratedValue
    private Long id;

    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;
    private String name;
    private String city;
    boolean enabled = true;

    @OneToOne
    FileUploadResponse file;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Set<Authority> authorities = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setId(Long id) {
        this.id = id;
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
    public FileUploadResponse getFile() {
        return file;
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
    public void setFile(FileUploadResponse file) {
        this.file = file;
    }
    public Set<Authority> getAuthorities() {
        return authorities;
    }
    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
    public void addAuthority(String authorityString) {
        this.authorities.add(new Authority(this.username, authorityString));
    }
    public void removeAuthority(Authority authority) {
        this.authorities.remove(authority);
    }


}
