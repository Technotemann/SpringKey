package key.reel.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UserCreateRequests {

    @JsonProperty("login")
    @NotBlank(message = "empty login")
    @Size(min = 5, message = "short login")
    public final String login;
    @JsonProperty("name")
    @NotBlank(message = "empty name")
    @Size(min = 5, message = "short name")
    public final String name;
    @JsonProperty("password")
    @NotBlank(message = "empty password")
    @Size(min = 5, message = "short password")
    public final String password;
    @Valid
    public final List<Integer> roles;

    @JsonCreator
    public UserCreateRequests(
            @JsonProperty("login")
            @NotBlank(message = "empty login") @Size(
                    min = 5,
                    message = "short login") String login,
            @JsonProperty("name")
            @NotBlank(message = "empty name") @Size(
                    min = 5,
                    message = "short name") String name,
            @JsonProperty("password")
            @NotBlank(message = "empty password") @Size(
                    min = 5,
                    message = "short password") String password,
            List<Integer> roles) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }
}