package com.neoandroid.app;

import retrofit.client.Response;
import retrofit.http.GET;

import java.util.List;

/**
 * Created by francis on 4/15/15.
 */
public interface CriminalService {

    @GET("/securityx/criminalRest/index")
    List<Criminal> listCriminals();
}
