package com.neoandroid.app;

import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by francis on 4/15/15.
 */
public interface CriminalService {

    @GET("/securityx/criminalRest/index")
    Response listCriminals();
}
