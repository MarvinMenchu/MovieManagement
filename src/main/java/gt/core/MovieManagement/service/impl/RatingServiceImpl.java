package gt.core.MovieManagement.service.impl;

import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.persistence.entity.Rating;
import gt.core.MovieManagement.persistence.repository.RatingCrudRepository;
import gt.core.MovieManagement.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingCrudRepository ratingCrudRepository;

    @Autowired
    public RatingServiceImpl(RatingCrudRepository ratingCrudRepository) {
        this.ratingCrudRepository = ratingCrudRepository;
    }

    @Override
    public List<Rating> getAll() {
        return ratingCrudRepository.findAll();
    }

    @Override
    public List<Rating> getAllByMovieId(Long id) {
        return ratingCrudRepository.getByMovieId(id);
    }

    @Override
    public List<Rating> getAllByUserUsername(String username) {
        return ratingCrudRepository.getByUsername(username);
    }

    @Override
    public Rating getOneById(Long id) {
        return ratingCrudRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("[rating:" + Long.toString(id) + "]"));
    }

    @Override
    public Rating createOne(Rating rating) {
        return ratingCrudRepository.save(rating);
    }

    @Override
    public Rating updateOneById(Long id, Rating rating) {
        Rating oldRating = this.getOneById(id);
        oldRating.setUserId(rating.getUserId());
        oldRating.setMovie(rating.getMovie());
        return ratingCrudRepository.save(oldRating);
    }

    @Override
    public void deleteOneById(Long id) {
        if (ratingCrudRepository.existsById(id)){
            ratingCrudRepository.deleteById(id);
            return;
        }
        throw new ObjectNotFoundException("[rating:" + Long.toString(id) + "]");
    }
}