package com.learning.tests;

import com.learning.configuration.APIConfig;
import com.learning.configuration.ClientManager;
import com.learning.endpoints.LoginService;
import com.learning.model.login.FailedRequestResponse;
import com.learning.model.login.LoginResponse;
import com.learning.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests {
    private static final String RP_URL = new APIConfig().getConfig().get("host");

    private final ThreadLocal<LoginService> loginService = new ThreadLocal<>();
    private static final String BASIC_AUTH_HEADER = Utils.getAuthHeader("ui", "uiman");
    private static final String BAD_CREDENTIALS_MESSAGE = "You do not have enough permissions. Bad credentials";
    private final ThreadLocal<Retrofit> retrofit = new ThreadLocal<>();

    @BeforeAll
    void setUp() {
        retrofit.set(new Retrofit.Builder()
                .baseUrl(RP_URL)
                .client(ClientManager.getClient().build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build());

        loginService.set(retrofit.get().create(LoginService.class));
    }

    @Test
    void testSuccessfulLogin() throws IOException {
        Response<LoginResponse> loginResponse = loginService.get().getToken(
                BASIC_AUTH_HEADER,
                "password",
                "advanced",
                "Qwerty123456"
        ).execute();

        assertTrue(loginResponse.isSuccessful());
        LoginResponse login = loginResponse.body();

        assert login != null;
        assertFalse(login.getAccess_token().isBlank());
        assertFalse(login.getRefresh_token().isBlank());
    }

    @Test
    void testWrongUsernameLogin() throws IOException {

        Response<LoginResponse> loginResponse = loginService.get().getToken(
                BASIC_AUTH_HEADER,
                "password",
                "advance1d",
                "Qwerty123456"
        ).execute();

        assertEquals(400, loginResponse.code());
        assert loginResponse.errorBody() != null;
        String actualMessage = Utils.getErrorResponse(loginResponse.errorBody().string(), FailedRequestResponse.class).getMessage();
        assertEquals(BAD_CREDENTIALS_MESSAGE, actualMessage);
    }

    @Test
    void testWrongPasswordLogin() throws IOException {
        Response<LoginResponse> loginResponse = loginService.get().getToken(
                BASIC_AUTH_HEADER,
                "password",
                "advanced",
                "Qw1erty123456"
        ).execute();

        assertEquals(400, loginResponse.code());
        assert loginResponse.errorBody() != null;
        String actualMessage = Utils.getErrorResponse(loginResponse.errorBody().string(), FailedRequestResponse.class).getMessage();
        assertEquals(BAD_CREDENTIALS_MESSAGE, actualMessage);
    }
}
