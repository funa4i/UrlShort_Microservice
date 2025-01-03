package org.urlshort.services;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.stereotype.Service;
import org.urlshort.domain.AuthModel;
import org.urlshort.domain.data.CreateUserRequest;
import org.urlshort.domain.data.UserValid;
import org.urlshort.domain.entities.User;
import org.urlshort.exceptions.InvalidAuthorizeException;
import org.urlshort.util.HashUtil;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtServ jwtServ;

    private final AuthModel authModel;

    public String logIn(@Valid UserValid userValid){
        var user = authModel.getUser(userValid.getLogin())
                .orElseThrow(InvalidAuthorizeException::new);

        if (!HashUtil.validatePassword(
                userValid.getPassword(), user.getPassword())){
            throw new InvalidAuthorizeException();
        }
        return jwtServ.generateToken(user);
    }

    public String singIn(@Valid CreateUserRequest newUser){
        return jwtServ.generateToken(
                authModel.createNewUser(newUser)
        );
    }




}
