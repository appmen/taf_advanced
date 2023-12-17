package com.learning.endpoints;

import com.learning.model.dashboards.BodyMessageResponse;
import com.learning.model.dashboards.CreateDashboardBody;
import com.learning.model.dashboards.CreateDashboardResponse;
import com.learning.model.dashboards.DashboardsResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface DashboardsService {

    @Headers("Content-Type: application/json")
    @GET("api/v1/{projectName}/dashboard")
    Call<DashboardsResponse> getAllDashboards(
            @Header("Authorization") String auth,
            @Header("Host") String host,
            @Path("projectName") String projectName);

    @Headers("Content-Type: application/json")
    @POST("api/v1/{projectName}/dashboard")
    Call<CreateDashboardResponse> createDashboard(
            @Header("Authorization") String auth,
            @Header("Host") String host,
            @Path("projectName") String projectName,
            @Body CreateDashboardBody createDashboardBody);

    @Headers("Content-Type: application/json")
    @PUT("api/v1/{projectName}/dashboard/{id}")
    Call<BodyMessageResponse> updateDashboard(
            @Header("Authorization") String auth,
            @Header("Host") String host,
            @Path("projectName") String projectName,
            @Body CreateDashboardBody createDashboardBody,
            @Path("id") int id);

    @Headers("Content-Type: application/json")
    @DELETE("api/v1/{projectName}/dashboard/{id}")
    Call<BodyMessageResponse> deleteDashboard(
            @Header("Authorization") String auth,
            @Header("Host") String host,
            @Path("projectName") String projectName,
            @Path("id") int id);
}
