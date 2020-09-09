package key.reel.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api
public class Controller {
    @GetMapping("/index")
    public String getString(@RequestParam String name) {
        return "me_dummy_string" + name;
    }
}
