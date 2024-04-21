package com.bangbang.service;

import com.bangbang.domain.sign.UserRoles;
import com.bangbang.dto.sign.*;
import com.bangbang.domain.sign.User;
import com.bangbang.domain.sign.UserRepository;
import com.bangbang.exception.BaseException;
import com.bangbang.exception.ErrorMessage;
import com.bangbang.util.EmailHandler;
import com.bangbang.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EmailHandler emailHandler;
    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public void signUp(SignUp SignUpInfo) throws Exception {
        if (userRepository.findByUserEmail(SignUpInfo.getUserEmail()) != null) {
            throw new BaseException(ErrorMessage.EXIST_EMAIL);
        }

        if (userRepository.findByUserNickname(SignUpInfo.getUserNickname()).isPresent()) {
            throw new BaseException(ErrorMessage.EXIST_NICKNAME);
        }

        User user = User.builder()
                .userEmail(SignUpInfo.getUserEmail())
                .userNickname(SignUpInfo.getUserNickname())
                .userPassword(passwordEncoder.encode(SignUpInfo.getUserPassword()))
                .userRoles(Collections.singletonList(UserRoles.ROLES_USER.getName()))
                .userStatus(1).build();
        userRepository.save(user);

    }

    @Override
    public ResponseSignIn login(SignIn signIn) throws Exception {
        System.out.println(signIn);
        User user = userRepository.findByUserEmail(signIn.getUserEmail());
            if (user == null) {
                throw new BaseException(ErrorMessage.NOT_EXIST_ID);
            }
        System.out.println(user);

        if (user.getUserStatus() == 0) {
            throw new BaseException(ErrorMessage.DONT_EXIST_ACCOUNT);
        }

        if (!passwordEncoder.matches(signIn.getUserPassword(), user.getUserPassword())) {
            throw new BaseException(ErrorMessage.NOT_PASSWORD);
        }

        String level = "";

        if (user.getUserRoles().equals("ROLE_USER")) {
            level = "1";
        } else if (user.getUserRoles().equals("ROLE_BROKER")) {
            level = "2";
        } else if (user.getUserRoles().equals("ROLE_ADMIN")) {
            level = "3";
        }

        // 존재할시
        String accessToken = jwtTokenProvider.createToken(user.getUserId(), user.getUserRoles());
        String refreshToken = jwtTokenProvider.createRefresh(user.getUserId(), user.getUserRoles());
        user.setUserRefreshToken(refreshToken);
        userRepository.save(user);

        ResponseSignIn responseSignIn = ResponseSignIn.builder()
                .result(true)
                .msg("로그인을 성공하였습니다.")
                .level(level)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .nickName(user.getUserNickname())
                .email(user.getUserEmail())
                .id(user.getUserId())
                .userRoles(user.getUserRoles())
                .build();

        return responseSignIn;
    }

    @Override
    public String refreshToken(Long uid, String token) throws Exception {
        User object = userRepository.findByUserId(uid);
        if (object != null) {
            User user = object;
            System.out.println(token);
            System.out.println(user.getUserRefreshToken());
            if (token.equals(user.getUserRefreshToken())) {
                if (jwtTokenProvider.validateToken(token)) {
                    return jwtTokenProvider.createToken(user.getUserId(), user.getUserRoles());
                } else {
                    throw new BaseException(ErrorMessage.ACCESS_TOKEN_EXPIRE);
                }
            } else {
                throw new BaseException(ErrorMessage.REFRESH_TOKEN_NOT_MATCH);
            }
        } else {
            throw new BaseException(ErrorMessage.NOT_USER_INFO);
        }
    }

    @Override
    public void findPassword(FindPassword findPasswordEmail) throws Exception {
        User signUser = userRepository.findByUserEmail(findPasswordEmail.getId());

        if (signUser != null) {
            if (signUser.getUserEmail().equals(findPasswordEmail.getId())) {
                Random rnd = new Random();
                StringBuilder temp_pw = new StringBuilder();
                for (int i = 0; i < 20; i++) {
                    if (rnd.nextBoolean()) {
                        temp_pw.append((char) ((int) (rnd.nextInt(26)) + 97));
                    } else {
                        temp_pw.append((rnd.nextInt(10)));

                    }
                }
                String epw = passwordEncoder.encode(temp_pw);
                signUser.setUserPassword(epw);
                userRepository.save(signUser);

                emailHandler.sendMail(signUser.getUserEmail(), "임시 비밀번호입니다.", "임시 비밀번호는 " + temp_pw + " 입니다.", false);
            } else {
                throw new BaseException(ErrorMessage.NOT_USER_INFO_MATCH);
            }
        } else {
            throw new BaseException(ErrorMessage.NOT_USER_INFO);
        }
    }

    @Override
    public User findUser(Long userId) throws Exception {
        User user = userRepository.findByUserId(userId);

        return user;
    }

    @Override
    public Long findUserId(String token) throws Exception {
        return Long.valueOf(jwtTokenProvider.getUserId(token));
    }

//    @Override
//    public List<UserDto> findAllUsers() throws Exception {
//        return userRepository.findAllUsers();
//    }
}
