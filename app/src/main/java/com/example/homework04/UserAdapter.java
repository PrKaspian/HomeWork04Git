package com.example.homework04;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context context;
    private int resource;
    private List<User> data;

    public UserAdapter(Context context, int resource, List<User> data) {
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View container = inflater.inflate(resource, parent, false);
        UserAdapter.UserViewHolder userViewHolder = new UserAdapter.UserViewHolder(container);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String name = data.get(position).getName();
        holder.tvName.setText(name);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, PostActivity.class);
                User selectedUser = data.get(getAdapterPosition());
                intent.putExtra(MainActivity.KEY_USER, selectedUser);
                context.startActivity(intent);
            });
        }
    }
}
