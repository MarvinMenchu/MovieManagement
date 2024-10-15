package gt.core.MovieManagement.controller;

import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.service.MovieService;
import gt.core.MovieManagement.util.MovieGenre;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //@RequestMapping(method = RequestMethod.GET)
    //@ResponseBody
    @GetMapping
    public ResponseEntity<List<Movie>> getAll(@RequestParam(required = false, name = "titulo") String title,
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

        //HttpHeaders headers = new HttpHeaders();
        //return new ResponseEntity<>(peliculas, headers, HttpStatus.OK); //opcion 1
        //return ResponseEntity.status(200).body(peliculas); //opcion 2
        return ResponseEntity.ok(peliculas); //opcion 3
    }

    //@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> findOneById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(movieService.getOneById(id));
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //@RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public ResponseEntity<Movie> createOne(@RequestBody Movie movie,
                                           HttpServletRequest request) {
        Movie movieCreated = movieService.createOne(movie);

        String baseURL = request.getRequestURL().toString();
        URI newLocation = URI.create(baseURL + "/" + movieCreated.getId());
        //return ResponseEntity.status(HttpStatus.CREATED).location(newLocation).body(movieCreated); opcion 1
        return ResponseEntity.created(newLocation).body(movieCreated); //opcion 2
    }

    //@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Movie> updateOneById(@PathVariable Long id,
                                               @RequestBody Movie movie) {
        try{
            Movie updatedMovie = movieService.updateOneById(id, movie);
            return ResponseEntity.ok(updatedMovie);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();

        }
    }

    //@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable Long id) {
        try {
            movieService.deleteOneById(id);
            return ResponseEntity.noContent().build();
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Movie> createOneV1(@RequestParam String title,
                                           @RequestParam String director,
                                           @RequestParam MovieGenre genre,
                                           @RequestParam Integer releaseYear,
                                            HttpServletRequest request) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setGenre(genre);
        movie.setReleaseYear(releaseYear);
        Movie movieCreated = movieService.createOne(movie);

        String baseURL = request.getRequestURL().toString();
        URI newLocation = URI.create(baseURL + "/" + movieCreated.getId());
        //return ResponseEntity.status(HttpStatus.CREATED).location(newLocation).body(movieCreated); opcion 1
        return ResponseEntity.created(newLocation).body(movieCreated); //opcion 2
    }
}