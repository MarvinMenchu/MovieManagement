package gt.core.MovieManagement.controller;

import gt.core.MovieManagement.dto.request.SaveUser;
import gt.core.MovieManagement.dto.response.GetUser;
import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<GetUser>> getAll(@RequestParam(required = false) String name) {

        List<GetUser> users = null;

        if (StringUtils.hasText(name)) {
            users = userService.getAllByName(name);
        } else {
            users = userService.getAll();
        }

        System.out.println("Entro al metodo findAll de UserController");
        return ResponseEntity.ok(users);
    }

    //@RequestMapping(method = RequestMethod.GET, path = "/{username}")
    @GetMapping(value = "/{username}")
    public ResponseEntity<GetUser> findOneByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(userService.getOneByUsername(username));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<GetUser> createOne(@RequestBody SaveUser saveDato,
                                          HttpServletRequest request) {
        GetUser userCreated = userService.createOne(saveDato);
        String baseURL = request.getRequestURL().toString();
        URI newLocation = URI.create(baseURL + "/" + userCreated.username());
        return ResponseEntity.created(newLocation).body(userCreated);
    }

    //@RequestMapping(method = RequestMethod.PUT, path = "/{username}")
    @PutMapping(value = "/{username}")
    public ResponseEntity<GetUser> updateOneByUsername(@PathVariable String username,
                                                    @RequestBody SaveUser saveDto) {
        try {
            return ResponseEntity.ok(userService.updateOneByUsername(username, saveDto));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //@RequestMapping(method = RequestMethod.DELETE, path = "/{username}")
    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Void> deleteOneByUsername(@PathVariable String username) {
        try {
            userService.deleteOneByUsername(username);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        userService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}