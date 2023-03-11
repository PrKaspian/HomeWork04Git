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

public class PostActivity extends AppCompatActivity {
    private RecyclerView rvPosts;
    private PostAdapter adapter;
    private PostApi postApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        User selectedUser = (User) getIntent().getSerializableExtra(MainActivity.KEY_USER);
        postApi = NetworkService.getInstance().getPostApi();
        Call<List<Post>> call = postApi.getPostsOfUser(selectedUser.getId());
        List<Post> data = new ArrayList<>();
        PostActivity parent = this;
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<Post> posts = response.body();
                for (Post post : posts) {
                    data.add(post);
                }
                adapter = new PostAdapter(parent, R.layout.title_item_list, data);
                rvPosts = findViewById(R.id.rvPosts);
                rvPosts.setAdapter(adapter);
                LinearLayoutManager layoutManager = new LinearLayoutManager(
                        parent,
                        RecyclerView.VERTICAL,
                        false
                );
                rvPosts.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}