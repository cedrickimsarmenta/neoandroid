package com.neoandroid.app;

import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

public interface GitHubService {
    @GET("/users/list")
    Response userList();
}