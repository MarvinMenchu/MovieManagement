package gt.core.MovieManagement.persistence.repository;

import gt.core.MovieManagement.persistence.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RatingCrudRepository extends JpaRepository<Rating, Long> {
    List<Rating> getByMovieId(Long id);
    List<Rating> getByUserUsername(Long id);

    @Query("SELECT r FROM Rating r JOIN r.user u WHERE u.username = ?1")
    List<Rating> getByUsername(String username);
}