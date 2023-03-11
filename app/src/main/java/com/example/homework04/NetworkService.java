package com.example.homework04;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService networkService;
    private  static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public UserApi getUserApi(){
        return retrofit.create(UserApi.class);
    }
    public PostApi getPostApi() {return retrofit.create(PostApi.class);}
}
