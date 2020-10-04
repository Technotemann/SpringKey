package key.reel.demo.entity;


import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
public class User {

    @Id
    private String login;
    private String name;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "ur",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Role> roles;

    public User() {
    }

    public User(String login, String name, String password) {
        this.login = login;
        this.name = name;
        this.password = password;
    }

    public User(String login, String name, String password, List<Role> roles) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
