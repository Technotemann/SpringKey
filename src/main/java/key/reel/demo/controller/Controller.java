package key.reel.demo.controller;

import io.swagger.annotations.Api;
import key.reel.demo.dao.UserDao;
import key.reel.demo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api
public class Controller {
    private final UserDao userDao;

    public Controller(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/index")
    public String getString(@RequestParam String name) {
        return "me_dummy_string" + name;
    }
    @GetMapping("/all")
    public List<User> getAll() {
        return userDao.getUsers();
    }
    @GetMapping("/one")
    public User getById(@RequestParam int id) {
        return userDao.getUsersById(id);
    }
}
