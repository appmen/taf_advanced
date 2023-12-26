package com.learning.model.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FailedRequestResponse {
    private int errorCode;
    private String message;

    public FailedRequestResponse(){}
}
