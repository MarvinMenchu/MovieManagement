package gt.core.MovieManagement.controller;

import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.persistence.entity.User;
import gt.core.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String name) {

        List<User> users = null;

        if (StringUtils.hasText(name)) {
            users = userService.getAllByName(name);
        } else {
            users = userService.getAll();
        }

        System.out.println("Entro al metodo findAll de UserController");
        return ResponseEntity.ok(users);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{username}")
    public ResponseEntity<User> findOneByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getOneByUsername(username));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}