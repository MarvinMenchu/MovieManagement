package gt.core.MovieManagement.mapper;

import gt.core.MovieManagement.dto.request.SaveUser;
import gt.core.MovieManagement.dto.response.GetUser;
import gt.core.MovieManagement.persistence.entity.User;

import java.util.List;

public class UserMapper {

    public static GetUser toGetDto(User entity) {
        if (entity == null) return null;
        return new GetUser(
                entity.getUsername(),
                entity.getName(),
                RatingMapper.toGetUserRatingDtoList(entity.getRatings())
        );
    }

    public static List<GetUser> toGetDto(List<User> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(UserMapper::toGetDto)
                .toList();
    }

    public static User toEntity(SaveUser dto) {
        if (dto == null) return null;
        User entity = new User();
        entity.setUsername(dto.username());
        entity.setName(dto.name());
        entity.setPassword(dto.password());
        return entity;
    }

    public static void updateEntity(User oldUser, SaveUser saveDto) {
        if (oldUser == null || saveDto == null) return;
        oldUser.setName(saveDto.name());
        oldUser.setPassword(saveDto.password());
    }
}