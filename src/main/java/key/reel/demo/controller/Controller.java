package key.reel.demo.controller;

import io.swagger.annotations.Api;
import key.reel.demo.dao.RoleJpaDao;
import key.reel.demo.dao.UserJpaDao;
import key.reel.demo.dto.UserCreateRequests;
import key.reel.demo.entity.Role;
import key.reel.demo.entity.User;
import org.springframework.lang.NonNull;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@Api
public class Controller {
    private final UserJpaDao userDao;
    private final Validator validator;
    private final RoleJpaDao roleJpaDao;

    public Controller(UserJpaDao userDao, Validator validator, RoleJpaDao roleJpaDao) {
        this.userDao = userDao;
        this.validator = validator;
        this.roleJpaDao = roleJpaDao;
    }

    @GetMapping("/index")
    public String getString(@RequestParam String name) {
        return "me_dummy_string" + name;
    }

    //    @GetMapping("/all")
//    public List<Role> getAll() {
//        return userDao.getRoles();
//    }
    @GetMapping("/one")
    public Role getById(@RequestParam int id) {
        return roleJpaDao.findById(id).get();
    }

    @GetMapping("/users")
    public Iterable<User> allUsers() {
        return userDao.findAll();
    }

    //    @GetMapping("/user")
//    public String getUserByLogin(@RequestParam String name) {
//        return "Пользователь: " + userDao.getUserNameByLogin(name) + " Роли пользователя: " + userDao.getRolesByLogin(name).toString();
//    }
    @DeleteMapping("/remove")
    public void removeUser(@RequestParam @NonNull String name) {
        //validator.validate(name, );
        userDao.deleteById(name);
    }

    @PostMapping("/add")
    public User addUser(@RequestBody @Valid UserCreateRequests userCreateRequests) {
        List<Role> roles = new ArrayList<>();
        roles = (List<Role>) roleJpaDao.findAllById(userCreateRequests.roles);
        return userDao.save(new User(userCreateRequests.login, userCreateRequests.name, userCreateRequests.password, roles));
    }
//    @PostMapping("/add")
//    public User addUser(@RequestParam @NotBlank @Size(min = 5) String inputLogin, @RequestParam @NotBlank @Size(min = 5) String inputName, @RequestParam @NotBlank @Size(min = 5) String inputPassword) {
//        return userDao.save(new User(inputLogin, inputName, inputPassword));
//    }
//    @PutMapping("/edit")
//    public User editUser(@RequestBody @Valid UserCreateRequests userCreateRequests) {
//        List<Role> roles = new ArrayList<>();
//        for (RoleCreateRequest request : userCreateRequests.roles) {
//            roles.add(new Role(request.name));
//        }
//            return userDao.save(new User(userCreateRequests.login, userCreateRequests.name, userCreateRequests.password, roles));
//    }

}
