package com.shop.mapper;

import com.shop.dto.UserRequestDto;
import com.shop.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-22T00:00:51+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userRequestDto.getUserName() );
        user.setEmail( userRequestDto.getEmail() );
        user.setPassword( userRequestDto.getPassword() );
        user.setRole( userRequestDto.getRole() );

        return user;
    }
}
