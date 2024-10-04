package gt.core.MovieManagement.service;

import gt.core.MovieManagement.persistence.entity.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAll();
    List<Rating> getAllByMovieId(Long id);
    List<Rating> getAllByUserUsername(String username);
    Rating getOneById(Long id);
    Rating createOne(Rating rating);
    Rating updateOneById(Long id, Rating rating);
    void deleteOneById(Long id);
}