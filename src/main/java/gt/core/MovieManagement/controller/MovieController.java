package gt.core.MovieManagement.controller;

import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.service.MovieService;
import gt.core.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Movie> getAll(@RequestParam(required = false, name = "titulo") String title,
                              @RequestParam(required = false, name = "genero") MovieGenre genre) {
        List<Movie> peliculas = null;

        if (StringUtils.hasText(title) && genre != null) {
            peliculas = movieService.getAllByGenreAndTitle(genre, title);
        } else if (StringUtils.hasText(title)) {
            peliculas = movieService.getAllByTitle(title);
        } else if (genre != null) {
            peliculas = movieService.getAllByGenre(genre);
        } else {
            peliculas = movieService.getAll();
        }
        System.out.println("Entro al metodo findAll de MovieController");
        return peliculas;
    }

//    @RequestMapping(method = RequestMethod.GET, params = {"title", "genre"})
//    @ResponseBody
//    public List<Movie> getAllByGenreAndTitle(@RequestParam String title,
//                              @RequestParam MovieGenre genre) {
//        System.out.println("Metodo: getAllByGenreAndTitle");
//        return movieService.getAllByGenreAndTitle(genre, title);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = "title")
//    @ResponseBody
//    public List<Movie> getAllByGenreAndTitle(@RequestParam String title) {
//        System.out.println("Metodo: getAllByGenreAndTitle");
//        return movieService.getAllByTitle(title);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = "genre")
//    @ResponseBody
//    public List<Movie> getAllByGenre(@RequestParam MovieGenre genre) {
//        System.out.println("Metodo: getAllByGenre");
//        return movieService.getAllByGenre(genre);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, params = {"!title", "!genre"})
//    @ResponseBody
//    public List<Movie> getAll() {
//        System.out.println("Metodo: getAll");
//        return movieService.getAll();
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Movie findOneById(@PathVariable Long id) {
        return movieService.getOneById(id);
    }
}