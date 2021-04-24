import java.util.List;

public class User {
    private final Long id;
    private String login;
    private String password;
    private String email;
    private final Boolean admin;

    public User(Long id, String login, String password, String email, Boolean admin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Long getId() {
        return id;
    }
}
