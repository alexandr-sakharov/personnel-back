package ru.personnel.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.personnel.dto.user.UserDto;
import ru.personnel.dto.user.UserOutDto;
import ru.personnel.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);
    UserEntity userDtoToUserEntity(UserDto userData);
    UserOutDto toDto(UserEntity user);
}