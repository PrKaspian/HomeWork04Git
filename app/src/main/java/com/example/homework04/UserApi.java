package com.example.homework04;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {
    @GET("/users/{id}")
    Call<Post> getPostByID(@Path("id") int id);

    @GET("/users")
    Call<List<User>> getAllUsers();
}
