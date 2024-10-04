package gt.core.MovieManagement.service;

import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.util.MovieGenre;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    List<Movie> getAllByTitle(String title);
    List<Movie> getAllByGenre(MovieGenre genre);
    List<Movie> getAllByGenreAndTitle(MovieGenre genre, String title);
    Movie getOneById(Long id);
    Movie createOne(Movie movie);
    Movie updateOneById(Long id, Movie movie);
    void deleteOneById(Long id);
}