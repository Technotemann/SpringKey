package key.reel.demo.controller;

import io.swagger.annotations.Api;
import key.reel.demo.dao.UserDao;
import key.reel.demo.entity.Role;
import key.reel.demo.ventity.NameRoles;
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
    public List<Role> getAll() {
        return userDao.getRoles();
    }
    @GetMapping("/one")
    public Role getById(@RequestParam int id) {
        return userDao.getRoleById(id);
    }
    @GetMapping("/users")
    public List<String> allUsers() { return userDao.getAllUsers(); }
    @GetMapping("/user")
    public NameRoles getUserByLogin(@RequestParam String name) {
        return userDao.getNameRolesByLogin(name);
    }
    @GetMapping("/remove")
    public String removeUser(@RequestParam String name)
    {
        if (userDao.removeUserByLogin(name) > 0) {
            return "success: true";
        }
        else return "success: fail";
    }
    @GetMapping("/add")
    public String addUser(@RequestParam String inputLogin, @RequestParam String inputName, @RequestParam String inputPassword)
    {
        if (userDao.addNewUser(inputLogin, inputName, inputPassword) > 0) {
            return "success: true";
        }
        else return "success: fail";
    }
}
