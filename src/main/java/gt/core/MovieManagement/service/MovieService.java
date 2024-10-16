package gt.core.MovieManagement.service;

import gt.core.MovieManagement.dto.request.SaveMovie;
import gt.core.MovieManagement.dto.response.GetMovie;
import gt.core.MovieManagement.util.MovieGenre;

import java.util.List;

public interface MovieService {
    List<GetMovie> getAll();
    List<GetMovie> getAllByTitle(String title);
    List<GetMovie> getAllByGenre(MovieGenre genre);
    List<GetMovie> getAllByGenreAndTitle(MovieGenre genre, String title);
    GetMovie getOneById(Long id);
    GetMovie createOne(SaveMovie saveDto);
    GetMovie updateOneById(Long id, SaveMovie saveDto);
    void deleteOneById(Long id);
}