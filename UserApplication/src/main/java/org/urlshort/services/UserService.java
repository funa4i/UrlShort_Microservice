package org.urlshort.services;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.urlshort.domain.UserModel;
import org.urlshort.domain.data.*;
import org.urlshort.mappers.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserModel userModel;

    private final UserMapper userMapper;

    public void createUser(@Valid UserCreateRequest user){
        userModel.createUserWithId(user.getId(), user.getEmail());
    }

    public DecreaseResultAnswer decreaseUserLinks(@Min(1) @NotNull Long id){
        return new DecreaseResultAnswer(
                userModel.decreaseUserLinksLeft(id)
        );
    }

    public UserInfo userByEmail(@Valid UserMailRequest mail){
        return userMapper.toUserInfo(
                userModel.getUserByMail(mail.getEmail())
        );
    }

    public void deleteUser(@Min(1) @NotNull Long id){
        userModel.deleteUserById(id);
    }

    public Page<UserInfo> getUsers(@NotNull @Min(0) Integer page,
                                   @NotNull @Min(1) Integer limits){
        return userModel.getAllUsers(page, limits)
                .map(userMapper::toUserInfo);
    }

    public void setUserLinks(@Min(1) @NotNull Long userId,
                             @Min(1) @NotNull Integer linksCount){
        userModel.setUserLinksPerDay(userId, linksCount);
    }
}
