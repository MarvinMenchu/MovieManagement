package gt.core.MovieManagement.persistence.repository;

import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.util.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCrudRepository extends JpaRepository<Movie, Long> {
    List<Movie> getByTitleContaining(String title);
    List<Movie> getByGenre(MovieGenre genre);
    List<Movie> getByGenreAndTitleContaining(MovieGenre genre, String title);
}