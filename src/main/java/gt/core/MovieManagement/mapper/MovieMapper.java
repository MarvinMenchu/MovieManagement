package gt.core.MovieManagement.mapper;

import gt.core.MovieManagement.dto.request.SaveMovie;
import gt.core.MovieManagement.dto.response.GetMovie;
import gt.core.MovieManagement.persistence.entity.Movie;

import java.util.List;

public class MovieMapper {

    public static GetMovie toGetDto(Movie entity) {
        if (entity == null) return null;
        return new GetMovie(
                entity.getId(),
                entity.getTitle(),
                entity.getDirector(),
                entity.getGenre(),
                entity.getReleaseYear(),
                RatingMapper.toGetMovieRatingDtoList(entity.getRatings())
        );
    }

    public static List<GetMovie> toGetDto(List<Movie> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(MovieMapper::toGetDto)
                .toList();
    }

    public static Movie toEntity(SaveMovie dto) {
        if (dto == null) return null;
        Movie entity = new Movie();
        entity.setTitle(dto.title());
        entity.setDirector(dto.director());
        entity.setGenre(dto.genre());
        entity.setReleaseYear(dto.releaseYear());
        return entity;
    }

    public static void updateEntity(Movie oldMovie, SaveMovie saveDto) {
        if (oldMovie == null || saveDto == null) return;

        oldMovie.setTitle(saveDto.title());
        oldMovie.setDirector(saveDto.director());
        oldMovie.setGenre(saveDto.genre());
        oldMovie.setReleaseYear(saveDto.releaseYear());
    }
}