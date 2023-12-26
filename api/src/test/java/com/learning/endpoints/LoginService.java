package com.learning.endpoints;

import com.learning.model.login.LoginResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface LoginService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("uat/sso/oauth/token")
    Call<LoginResponse> getToken(
            @Header("Authorization") String auth,
            @Field("grant_type") String grant_type,
            @Field("username") String username,
            @Field("password") String password
    );
}
