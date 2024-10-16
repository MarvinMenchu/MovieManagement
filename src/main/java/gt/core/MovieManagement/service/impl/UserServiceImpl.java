package gt.core.MovieManagement.service.impl;

import gt.core.MovieManagement.dto.request.SaveUser;
import gt.core.MovieManagement.dto.response.GetUser;
import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.mapper.UserMapper;
import gt.core.MovieManagement.persistence.entity.Movie;
import gt.core.MovieManagement.persistence.entity.User;
import gt.core.MovieManagement.persistence.repository.MovieCrudRepository;
import gt.core.MovieManagement.persistence.repository.UserCrudRepository;
import gt.core.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserCrudRepository userCrudRepository;
    private final MovieCrudRepository movieCrudRepository;


    @Autowired
    public UserServiceImpl(UserCrudRepository userCrudRepository, MovieCrudRepository movieCrudRepository) {
        this.userCrudRepository = userCrudRepository;
        this.movieCrudRepository = movieCrudRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GetUser> getAll() {
        return UserMapper.toGetDto(userCrudRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetUser> getAllByName(String name) {
        return UserMapper.toGetDto(userCrudRepository.getByNameContaining(name));
    }

    @Transactional(readOnly = true)
    @Override
    public GetUser getOneByUsername(String username) {
        return UserMapper.toGetDto(this.getOneEntityByUsername(username));
    }

    @Transactional(readOnly = true)
    private User getOneEntityByUsername(String username) {
        return userCrudRepository.getByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[user:" + username + "]"));
    }

    @Override
    public GetUser createOne(SaveUser saveDto) {
        User newUser = UserMapper.toEntity(saveDto);
        return UserMapper.toGetDto(userCrudRepository.save(newUser));
    }

    @Override
    public GetUser updateOneByUsername(String username, SaveUser saveDto) {
        User oldUser = this.getOneEntityByUsername(username);

        UserMapper.updateEntity(oldUser, saveDto);

        return UserMapper.toGetDto(userCrudRepository.save(oldUser));
    }

    @Override
    public void deleteOneByUsername(String username) {
//        User user = this.getOneByUsername(username);
//        userCrudRepository.delete(user);
        if (userCrudRepository.deleteByUsername(username) != 1) {
            throw new ObjectNotFoundException("[user:" + username + "]");
        }
    }

    @Override
    public void deleteAll() {
        Movie movie = new Movie();
        movie.setTitle("Spider man 2");
        movie.setReleaseYear(2004);
        movie.setDirector("Director");

        userCrudRepository.deleteAll();

        movieCrudRepository.save(movie);
    }
}