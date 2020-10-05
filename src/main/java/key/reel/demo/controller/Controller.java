package key.reel.demo.controller;

import io.swagger.annotations.Api;
import key.reel.demo.dao.RoleJpaDao;
import key.reel.demo.dao.UserJpaDao;
import key.reel.demo.dto.CommonResponse;
import key.reel.demo.dto.UserCreateRequests;
import key.reel.demo.dto.UserGetResponse;
import key.reel.demo.dto.UserUpdateRequest;
import key.reel.demo.entity.Role;
import key.reel.demo.entity.User;
import key.reel.demo.exeptions.SQLNotFoundField;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@Api
public class Controller {
    private final UserJpaDao userJpaDao;

    private final RoleJpaDao roleJpaDao;


    public Controller(UserJpaDao userJpaDao, RoleJpaDao roleJpaDao) {
        this.userJpaDao = userJpaDao;

        this.roleJpaDao = roleJpaDao;

    }

    @GetMapping("/users")
    public Iterable<UserGetResponse> allUsers() {
        Iterable<User> u = userJpaDao.findAll();
        List<UserGetResponse> ul = new ArrayList<>();
        for (User user : u) {
            ul.add(new UserGetResponse(user.getLogin()));
        }
        return ul;
    }

    @GetMapping("/users/{userId}")
    public User getById(@PathVariable String userId) {
        Optional<User> u = userJpaDao.findById(userId);
        return u.orElse(null);
    }

    @DeleteMapping("/users/{userId}")
    public void removeUser(@PathVariable @NonNull String userId) {
        userJpaDao.deleteById(userId);
    }

    @PostMapping("/users")
    public CommonResponse addUser(@RequestBody @Valid UserCreateRequests userCreateRequests) {
        List<Role> roles = (List<Role>) roleJpaDao.findAllById(userCreateRequests.roles);
        userJpaDao.save(new User(userCreateRequests.login, userCreateRequests.name, userCreateRequests.password, roles));
        return CommonResponse.ok();
    }

    @PutMapping("/users/{userId}")
    public User editUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest, @PathVariable @NonNull String userId) throws SQLNotFoundField {
        if (!userJpaDao.existsById(userId)) {
            throw new SQLNotFoundField("Неверный логин");
        }
        List<Role> roles = (List<Role>) roleJpaDao.findAllById(userUpdateRequest.roles);
        return userJpaDao.save(new User(userId, userUpdateRequest.name, userUpdateRequest.password, roles));
    }


    @GetMapping("/index")
    public String getString(@RequestParam String name) {
        return "me_dummy_string" + name;
    }

    @GetMapping("/all")
    public Iterable<Role> getAll() {
        return roleJpaDao.findAll();
    }

    @GetMapping("/one")
    public Role getById(@RequestParam int id) {
        Optional<Role> r = roleJpaDao.findById(id);
        return r.orElse(null);
    }


}
