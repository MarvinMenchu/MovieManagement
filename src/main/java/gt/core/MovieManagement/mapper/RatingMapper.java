package gt.core.MovieManagement.mapper;

import gt.core.MovieManagement.dto.response.GetMovie;
import gt.core.MovieManagement.dto.response.GetUser;
import gt.core.MovieManagement.persistence.entity.Rating;

import java.util.List;

public class RatingMapper {

    public static GetMovie.GetRatings toGetMovieRatingDto(Rating entity) {
        if (entity == null) return null;

        String username = entity.getUser() != null
                ? entity.getUser().getUsername()
                : null;

        return new GetMovie.GetRatings(
                entity.getId(),
                entity.getRating(),
                username
        );
    }

    public static GetUser.GetRatings toGetUserRatingDto(Rating entity) {
        if (entity == null) return null;

        String movieTitle = entity.getMovie() != null
                ? entity.getMovie().getTitle()
                : null;

        return new GetUser.GetRatings(
                entity.getId(),
                movieTitle,
                entity.getMovie().getId(),
                entity.getRating()
        );
    }

    public static List<GetMovie.GetRatings> toGetMovieRatingDtoList(List<Rating> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(RatingMapper::toGetMovieRatingDto)
                .toList();
    }

    public static List<GetUser.GetRatings> toGetUserRatingDtoList(List<Rating> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(RatingMapper::toGetUserRatingDto)
                .toList();
    }
}