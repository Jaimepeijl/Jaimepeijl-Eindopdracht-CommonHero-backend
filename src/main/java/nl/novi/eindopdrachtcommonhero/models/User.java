package nl.novi.eindopdrachtcommonhero.models;


import javax.persistence.*;


@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;
    private String apikey;
    private String email;
    private String name;
    private String city;

    public User(String username, String password, boolean enabled, String apikey, String email, String name, String city) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.apikey = apikey;
        this.email = email;
        this.name = name;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {}
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public boolean isEnabled() {
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
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEnabled(boolean enabled) {
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
