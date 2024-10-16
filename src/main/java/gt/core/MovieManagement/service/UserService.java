package gt.core.MovieManagement.service;

import gt.core.MovieManagement.dto.request.SaveUser;
import gt.core.MovieManagement.dto.response.GetUser;
import gt.core.MovieManagement.persistence.entity.User;

import java.util.List;

public interface UserService {
    List<GetUser> getAll();
    List<GetUser> getAllByName(String name);
    GetUser getOneByUsername(String username);
    GetUser createOne(SaveUser saveDto);
    GetUser updateOneByUsername(String username, SaveUser saveDto);
    void deleteOneByUsername(String username);
    void deleteAll();
}