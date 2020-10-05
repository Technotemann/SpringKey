package key.reel.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserGetResponse {
    @JsonProperty("login")
    private String login;

    @JsonCreator
    public UserGetResponse(@JsonProperty("login") String login) {
        this.login = login;
    }

}
