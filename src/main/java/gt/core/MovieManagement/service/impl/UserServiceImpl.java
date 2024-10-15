package gt.core.MovieManagement.service.impl;

import gt.core.MovieManagement.exception.ObjectNotFoundException;
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
    public List<User> getAll() {
        return userCrudRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllByName(String name) {
        return userCrudRepository.getByNameContaining(name);
    }

    @Transactional(readOnly = true)
    @Override
    public User getOneByUsername(String username) {
        return userCrudRepository.getByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("[user:" + username + "]"));
    }

    @Override
    public User createOne(User user) {
        return userCrudRepository.save(user);
    }

    @Override
    public User updateOneByUsername(String username, User user) {
        User oldUser = this.getOneByUsername(username);
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        return userCrudRepository.save(oldUser);
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