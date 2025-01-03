package org.urlshort.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.entities.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", expression = "java(org.springframework.security.crypto.bcrypt.BCrypt.hashpw(createUserRequest.getPassword(), " +
            "org.springframework.security.crypto.bcrypt.BCrypt.gensalt()))")
    User toUser(CreateUserRequest createUserRequest);
}
