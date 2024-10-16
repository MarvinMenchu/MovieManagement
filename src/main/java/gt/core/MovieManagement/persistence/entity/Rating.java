package gt.core.MovieManagement.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "movie_id", nullable = false)
    //@JsonProperty("movie-id")
    private Long movieId;

    @Column(name = "user_id", nullable = false)
    //@JsonProperty("user-id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    //@JsonIgnore
    //@JsonBackReference("movie-to-rating")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    //@JsonIgnore
    //@JsonBackReference("user-to-rating")
    private User user;

    @Check(constraints = "rating >= 0 AND rating <= 5")
    @Column(nullable = false)
    private int rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}