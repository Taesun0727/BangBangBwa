package com.bangbang.service;

import com.bangbang.domain.sign.User;
import com.bangbang.dto.sign.*;

import java.util.List;
import java.util.Map;

public interface UserService {

    void signUp(SignUp SignUpInfo) throws Exception;

    ResponseSignIn login(SignIn signInResult) throws Exception;

    String refreshToken(Long uid, String token) throws Exception;

    void findPassword(FindPassword signIn) throws Exception;

    User findUser(Long userId) throws Exception;

    Long findUserId(String token) throws  Exception;

//    List<UserDto> findAllUsers() throws Exception;
}
