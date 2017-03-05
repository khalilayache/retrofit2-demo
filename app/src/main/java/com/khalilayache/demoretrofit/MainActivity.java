package com.khalilayache.demoretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.khalilayache.demoretrofit.models.Course;
import com.khalilayache.demoretrofit.models.Instructors;
import com.khalilayache.demoretrofit.models.UdacityCatalog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RETROFIT DEMO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UdacityService udacityService = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> requestCatalog =  udacityService.listCatalog();

        requestCatalog.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {
                if(!response.isSuccessful()){
                    Log.wtf(TAG, "Erro: " + response.code());
                    return;
                }
                UdacityCatalog catalog = response.body();

                for(Course c : catalog.courses){
                    Log.i(TAG, "Titulo: " + c.title + ": " + c.subtitle);

                    for(Instructors i : c.instructors){
                        Log.i(TAG, i.name);
                    }
                    Log.i(TAG, "---------------");
                }
            }
            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.wtf(TAG, "Erro: " + t.getMessage());
            }
        });
    }


}
