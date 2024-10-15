package gt.core.MovieManagement.service;

import gt.core.MovieManagement.persistence.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    List<User> getAllByName(String name);
    User getOneByUsername(String username);
    User createOne(User user);
    User updateOneByUsername(String username, User user);
    void deleteOneByUsername(String username);
    void deleteAll();
}