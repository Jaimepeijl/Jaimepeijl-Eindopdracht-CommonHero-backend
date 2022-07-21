package nl.novi.eindopdrachtcommonhero.models;

import java.io.Serializable;

public class AuthorityKey implements Serializable {
    private String username;
    private String authority;

    public AuthorityKey(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }
}
