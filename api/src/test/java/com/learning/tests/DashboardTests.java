package com.learning.tests;

import com.learning.configuration.APIConfig;
import com.learning.configuration.ClientManager;
import com.learning.endpoints.DashboardsService;
import com.learning.endpoints.LoginService;
import com.learning.model.dashboards.BodyMessageResponse;
import com.learning.model.dashboards.CreateDashboardBody;
import com.learning.model.dashboards.CreateDashboardResponse;
import com.learning.model.dashboards.DashboardsResponse;
import com.learning.model.login.FailedRequestResponse;
import com.learning.model.login.LoginResponse;
import com.learning.utils.Utils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DashboardTests {
    private static final String RP_URL = new APIConfig().getConfig().get("host");
    private final ThreadLocal<Retrofit> retrofit = new ThreadLocal<>();
    private final ThreadLocal<LoginService> loginService = new ThreadLocal<>();
    private final ThreadLocal<DashboardsService> dashboardsService = new ThreadLocal<>();

    private static final String BASIC_AUTH_HEADER = Utils.getAuthHeader("ui", "uiman");
    private static final String HOST = RP_URL.replaceAll("/", "").split(":")[1] + ":443";
    private static final String WRONG_ID_MESSAGE_TEMPLATE = "Dashboard with ID '%s' not found on project '%s'. Did you use correct Dashboard ID?";
    private static final String ACCESS_DENIED_MESSAGE = "You do not have enough permissions. Access is denied";
    private static final String PROJECT_NAME = "advanced_personal";

    private final ThreadLocal<String> token = new ThreadLocal<>();
    private static final List<Integer> CREATED_DASHBOARDS = new ArrayList<>();

    private final ThreadLocal<String> authString = new ThreadLocal<>();

    @BeforeAll
    void setUp() throws IOException {
        retrofit.set(new Retrofit.Builder()
                .baseUrl(RP_URL).client(ClientManager.getClient().build())
                .addConverterFactory(JacksonConverterFactory.create())
                .build());

        loginService.set(retrofit.get().create(LoginService.class));
        dashboardsService.set(retrofit.get().create(DashboardsService.class));

        Response<LoginResponse> loginResponse = loginService.get().getToken(
                BASIC_AUTH_HEADER,
                "password",
                "advanced",
                "Qwerty123456"
        ).execute();

        assert loginResponse.body() != null;
        token.set(loginResponse.body().getAccess_token());
        authString.set("bearer " + token.get());
    }

    @AfterAll
    void deleteDashboards() {
        CREATED_DASHBOARDS.forEach(dashboardID ->
        {
            try {
                Response<BodyMessageResponse> deleteDashboardResponse =
                        dashboardsService.get().deleteDashboard(authString.get(), HOST, PROJECT_NAME, dashboardID)
                                .execute();
                assertTrue(deleteDashboardResponse.isSuccessful());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void getAllDashboardsTest() throws IOException {
        String mainDashboardName = "DEMO DASHBOARD";

        Response<DashboardsResponse> allDashboardsResponse =
                dashboardsService.get().getAllDashboards(authString.get(), HOST, PROJECT_NAME).execute();

        assertTrue(allDashboardsResponse.isSuccessful());
        assert allDashboardsResponse.body() != null;
        allDashboardsResponse.body().getContent().stream().filter(dashboard -> dashboard.getName().contains(mainDashboardName)).forEach(
                dashboard -> {
                    assertTrue(dashboard.getName().contains(mainDashboardName));
                    assertEquals("advanced", dashboard.getOwner());
                }
        );
    }

    @Test
    void getDashboardsForWrongProjectTest() throws IOException {
        Response<DashboardsResponse> allDashboardsResponse =
                dashboardsService.get().getAllDashboards(authString.get(), HOST, "my_dashboard")
                        .execute();

        assertFalse(allDashboardsResponse.isSuccessful());
        assert allDashboardsResponse.errorBody() != null;
        String actualMessage = Utils.getErrorResponse(allDashboardsResponse.errorBody().string(), FailedRequestResponse.class).getMessage();
        assertEquals(ACCESS_DENIED_MESSAGE, actualMessage);
    }

    @Test
    void createDashboardTest() throws IOException {
        createDefaultDashboard();
    }

    @Test
    void updateDashboardNameTest() throws IOException {
        int dashboardId = createDefaultDashboard();
        String successMessage = String.format("Dashboard with ID = '%s' successfully updated", dashboardId);

        CreateDashboardBody updateDashboardBody =
                new CreateDashboardBody("New test description", "New test Dashboard name");

        Response<BodyMessageResponse> updateDashboardResponse =
                dashboardsService.get().updateDashboard(
                        authString.get(),
                        HOST,
                        PROJECT_NAME,
                        updateDashboardBody,
                        dashboardId).execute();

        assertTrue(updateDashboardResponse.isSuccessful());
        assert updateDashboardResponse.body() != null;

        assertEquals(successMessage, updateDashboardResponse.body().getMessage());
    }


    @Test
    void updateDashboardWrongIdTest() throws IOException {
        int wrongDashboardId = 5;
        String error_message = String.format(WRONG_ID_MESSAGE_TEMPLATE, wrongDashboardId, PROJECT_NAME);
        CreateDashboardBody updateDashboardBody =
                new CreateDashboardBody("New test description", "New test Dashboard name");

        Response<BodyMessageResponse> updateDashboardResponse =
                dashboardsService.get().updateDashboard(
                        authString.get(),
                        HOST,
                        PROJECT_NAME,
                        updateDashboardBody,
                        wrongDashboardId).execute();

        assertFalse(updateDashboardResponse.isSuccessful());

        assert updateDashboardResponse.errorBody() != null;
        String actualMessage = Utils.getErrorResponse(updateDashboardResponse.errorBody().string(), FailedRequestResponse.class).getMessage();
        assertEquals(error_message, actualMessage);
    }

    @Test
    void updateDashboardWrongProjectTest() throws IOException {
        int wrongDashboardId = 5;
        CreateDashboardBody updateDashboardBody =
                new CreateDashboardBody("New test description", "New test Dashboard name");

        Response<BodyMessageResponse> updateDashboardResponse =
                dashboardsService.get().updateDashboard(
                        authString.get(),
                        HOST,
                        "PROJECT_NAME",
                        updateDashboardBody,
                        wrongDashboardId).execute();

        assertFalse(updateDashboardResponse.isSuccessful());

        assert updateDashboardResponse.errorBody() != null;
        String actualMessage = Utils.getErrorResponse(updateDashboardResponse.errorBody().string(), FailedRequestResponse.class).getMessage();
        assertEquals(ACCESS_DENIED_MESSAGE, actualMessage);
    }

    @Test
    void deleteIncorrectDashboardTest() throws IOException {
        int wrongDashboardId = 3;
        String error_message = String.format(WRONG_ID_MESSAGE_TEMPLATE, wrongDashboardId, PROJECT_NAME);

        Response<BodyMessageResponse> deleteDashboardResponse =
                dashboardsService.get().deleteDashboard(authString.get(), HOST, PROJECT_NAME, wrongDashboardId).execute();
        assertFalse(deleteDashboardResponse.isSuccessful());

        assert deleteDashboardResponse.errorBody() != null;
        String actualMessage = Utils.getErrorResponse(deleteDashboardResponse.errorBody().string(), FailedRequestResponse.class).getMessage();
        assertEquals(error_message, actualMessage);
    }

    private int createDefaultDashboard() throws IOException {
        long timestamp = new Date().getTime();

        CreateDashboardBody createDashboardBody =
                new CreateDashboardBody("Test description", "Test Dashboard name " + timestamp);

        Response<CreateDashboardResponse> createDashboardResponse =
                dashboardsService.get().createDashboard(authString.get(), HOST, PROJECT_NAME, createDashboardBody).execute();
        assertTrue(createDashboardResponse.isSuccessful());
        assert createDashboardResponse.body() != null;
        int dashboardId = createDashboardResponse.body().getId();
        assertTrue(dashboardId != 0);
        CREATED_DASHBOARDS.add(dashboardId);

        return dashboardId;
    }
}
