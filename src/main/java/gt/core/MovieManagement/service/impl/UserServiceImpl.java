package gt.core.MovieManagement.service.impl;

import gt.core.MovieManagement.exception.ObjectNotFoundException;
import gt.core.MovieManagement.persistence.entity.User;
import gt.core.MovieManagement.persistence.repository.UserCrudRepository;
import gt.core.MovieManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserCrudRepository userCrudRepository;

    @Autowired
    public UserServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public List<User> getAll() {
        return userCrudRepository.findAll();
    }

    @Override
    public List<User> getAllByName(String name) {
        return userCrudRepository.getByNameContaining(name);
    }

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
    public int deleteOneByUsername(String username) {
//        User user = this.getOneByUsername(username);
//        userCrudRepository.delete(user);
        if (userCrudRepository.deleteByUsername(username) != 1) {
            throw new ObjectNotFoundException("[user:" + username + "]");
        }
    }
}