package gt.core.MovieManagement.service.impl;

import gt.core.MovieManagement.dto.request.SaveMovie;
import gt.core.MovieManagement.dto.response.GetMovie;
import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.mapper.MovieMapper;
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
    public List<GetMovie> getAll() {
        List<Movie> entities = movieCrudRepository.findAll();
        return MovieMapper.toGetDto(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetMovie> getAllByTitle(String title) {
        return MovieMapper.toGetDto(movieCrudRepository.getByTitleContaining(title));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetMovie> getAllByGenre(MovieGenre genre) {
        return MovieMapper.toGetDto(movieCrudRepository.getByGenre(genre));
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetMovie> getAllByGenreAndTitle(MovieGenre genre, String title) {
        return MovieMapper.toGetDto(movieCrudRepository.getByGenreAndTitleContaining(genre, title));
    }

    @Transactional(readOnly = true)
    @Override
    public GetMovie getOneById(Long id) {
        return MovieMapper.toGetDto(
                this.getOneEntityById(id)
        );
    }

    @Transactional(readOnly = true)
    public Movie getOneEntityById(Long id) {
        return movieCrudRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[movie:" + Long.toString(id) + "]"));
    }

    @Override
    public GetMovie createOne(SaveMovie saveDto) {
        Movie newMovie = MovieMapper.toEntity(saveDto);
        return MovieMapper.toGetDto(
                movieCrudRepository.save(newMovie)
        );
    }

    @Override
    public GetMovie updateOneById(Long id, SaveMovie saveDto) {
        Movie oldMovie = this.getOneEntityById(id);
        MovieMapper.updateEntity(oldMovie, saveDto);
        return MovieMapper.toGetDto(movieCrudRepository.save(oldMovie));
    }

    @Override
    public void deleteOneById(Long id) {
        Movie movie = this.getOneEntityById(id);
        movieCrudRepository.delete(movie);
    }
}