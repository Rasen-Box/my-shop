package com.shop.mapper;

import com.shop.dto.UserRequestDto;
import com.shop.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User toEntity(UserRequestDto userRequestDto);
}
