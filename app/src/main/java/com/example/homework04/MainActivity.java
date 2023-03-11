package com.example.homework04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_USER = "user";
    private RecyclerView rvUsers;
    private UserAdapter adapter;
    private UserApi userApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userApi = NetworkService.getInstance().getUserApi();
        Call<List<User>> call = userApi.getAllUsers();
        List<User> data = new ArrayList<>();
        MainActivity parent = this;
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                for (User user : users) {
                    data.add(user);
                }
                adapter = new UserAdapter(parent, R.layout.name_item_list, data);
                rvUsers = findViewById(R.id.rvUsers);
                rvUsers.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(
                        parent,
                        RecyclerView.VERTICAL,
                        false
                );
                rvUsers.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


    }
}