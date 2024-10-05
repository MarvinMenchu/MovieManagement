package gt.core.MovieManagement.controller;

import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getAll() {
        System.out.println("Entro al metodo findAll de MovieController");
        return movieService.getAll();
    }
}