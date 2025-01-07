package org.urlshort.service;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.urlshort.feign.data.UserInfo;
import org.urlshort.feign.data.UserMailRequest;
import org.urlshort.feign.clients.UserApplicationApi;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserApplicationApi userApi;

    public UserInfo userByEmail(@Valid UserMailRequest mail){
        return userApi.userByEmail(mail);
    }


    public Page<UserInfo> getUsers(@NotNull @Min(0) Integer page,
                                   @NotNull @Min(1) Integer limits){

        return userApi.allUsers(page, limits);

    }

    public void setUserLinks(@Min(1) @NotNull Long userId,
                             @Min(1) @NotNull Integer linksCount){

        userApi.refactorUserLinks(linksCount, userId);

    }
}
