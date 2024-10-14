package gt.core.MovieManagement.controller;

import gt.core.MovieManagement.persistence.entity.User;
import gt.core.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll(@RequestParam(required = false) String name) {

        List<User> users = null;

        if (StringUtils.hasText(name)) {
            users = userService.getAllByName(name);
        } else {
            users = userService.getAll();
        }

        System.out.println("Entro al metodo findAll de UserController");
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{username}")
    public User findOneByUsername(@PathVariable String username) {
        return userService.getOneByUsername(username);
    }
}