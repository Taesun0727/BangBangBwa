package com.bangbang.dto.sign;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseSignIn {

    private boolean result;
    private String msg;
    private String level;
    private String accessToken;
    private String refreshToken;
    private String nickName;
    private String email;
    private Long id;
    private List<String> userRoles;

    @Builder
    public ResponseSignIn(boolean result, String msg, String level, String accessToken, String refreshToken, String nickName, String email, Long id, List<String> userRoles) {
        this.result = result;
        this.msg = msg;
        this.level = level;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.nickName = nickName;
        this.email = email;
        this.id = id;
        this.userRoles = userRoles;
    }
}
