package org.urlshort.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.entities.User;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", expression = "java(org.urlshort.util.HashUtil.generateHash(createUserRequest.getPassword()))")
    User toUser(CreateUserRequest createUserRequest);
}
