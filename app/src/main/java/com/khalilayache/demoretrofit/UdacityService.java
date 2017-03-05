package com.khalilayache.demoretrofit;

import com.khalilayache.demoretrofit.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by USUARIO
 * on 05/03/2017.
 */

public interface UdacityService {

     String BASE_URL = "https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<UdacityCatalog> listCatalog();

}
