package com.learning.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginBody {
    private String grant_type;
    private String username;
    private String password;
}
