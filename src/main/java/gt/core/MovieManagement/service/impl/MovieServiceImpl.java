package gt.core.MovieManagement.service.impl;

import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.persistence.repository.MovieCrudRepository;
import gt.core.MovieManagement.service.MovieService;
import gt.core.MovieManagement.util.MovieGenre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final MovieCrudRepository movieCrudRepository;

    @Autowired
    public MovieServiceImpl(MovieCrudRepository movieCrudRepository) {
        this.movieCrudRepository = movieCrudRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAll() {
        return movieCrudRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAllByTitle(String title) {
        return movieCrudRepository.getByTitleContaining(title);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAllByGenre(MovieGenre genre) {
        return movieCrudRepository.getByGenre(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Movie> getAllByGenreAndTitle(MovieGenre genre, String title) {
        return movieCrudRepository.getByGenreAndTitleContaining(genre, title);
    }

    @Transactional(readOnly = true)
    @Override
    public Movie getOneById(Long id) {
        return movieCrudRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[movie:" + Long.toString(id) + "]"));
    }

    @Override
    public Movie createOne(Movie movie) {
        return movieCrudRepository.save(movie);
    }

    @Override
    public Movie updateOneById(Long id, Movie movie) {
        Movie oldMovie = this.getOneById(id);
        oldMovie.setTitle(movie.getTitle());
        oldMovie.setDirector(movie.getDirector());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setReleaseYear(movie.getReleaseYear());
        return movieCrudRepository.save(oldMovie);
    }

    @Override
    public void deleteOneById(Long id) {
        Movie movie = this.getOneById(id);
        movieCrudRepository.delete(movie);
    }
}