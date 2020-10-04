package key.reel.demo.dto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleCreateRequest {

    @JsonProperty("name")
    public final String name;

    @JsonCreator
    public RoleCreateRequest( @JsonProperty("name") String name) {
        this.name = name;
    }
}
