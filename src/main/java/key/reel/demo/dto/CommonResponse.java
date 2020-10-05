package key.reel.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommonResponse {

    @JsonProperty("success")
    public final boolean success;

    @JsonProperty("errors")
    public final List<String> errors;

    @JsonCreator
    public CommonResponse(@JsonProperty("success") boolean success, @JsonProperty("errors") List<String> errors) {
        this.success = success;
        this.errors = errors;
    }

    public static CommonResponse ok() {
        return new CommonResponse(true, null);
    }

    public static CommonResponse fail(List<String> errors) {
        return new CommonResponse(false, errors);
    }
}

