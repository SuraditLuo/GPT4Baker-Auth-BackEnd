package Authentication.security.controller;

import java.io.Serializable;


public class JwtAuthenticationRequest implements Serializable {

    private static final long serialVersionUID = -8445943548965154778L;

    private String username;
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

}
