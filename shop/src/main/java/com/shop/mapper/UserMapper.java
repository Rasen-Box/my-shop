package com.shop.mapper;

import com.shop.dto.UserRequestDto;
import com.shop.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);
}
