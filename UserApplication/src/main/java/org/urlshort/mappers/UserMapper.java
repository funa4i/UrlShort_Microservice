package org.urlshort.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.urlshort.domain.data.UserInfo;
import org.urlshort.domain.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserInfo toUserInfo(User user);
}
