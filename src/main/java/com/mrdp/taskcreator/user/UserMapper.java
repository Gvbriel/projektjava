package com.mrdp.taskcreator.user;

import com.mrdp.taskcreator.user.dto.UserDto;
import com.mrdp.taskcreator.user.dao.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserDto userDto);
}
