package org.urlshort.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.urlshort.domain.data.UserCreateRequest;
import org.urlshort.domain.data.UserInfo;
import org.urlshort.domain.entity.User;

import java.time.LocalDateTime;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {LocalDateTime.class})
public interface UserMapper {

    UserInfo toUserInfo(User user);


    @Mapping(target = "id", source = "userCreateRequest.id")
    @Mapping(target = "email", source = "userCreateRequest.email")
    @Mapping(target = "dayCreate", expression = "java(LocalDateTime.now())")
    User toUser(UserCreateRequest userCreateRequest);
}
