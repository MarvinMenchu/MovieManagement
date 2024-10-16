package gt.core.MovieManagement.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import gt.core.MovieManagement.util.MovieGenre;

import java.io.Serializable;
import java.util.List;

public record GetMovie(
        long id,
        String title,
        String director,
        MovieGenre genre,
        @JsonProperty(value = "release_year") int releaseYear,
        List<GetRatings> ratings
)  implements Serializable {
    public static record GetRatings(
            long id,
            int rating,
            String username
    ) implements Serializable {}
}