package com.example.blood_doner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ABNegative extends AppCompatActivity {
    public RecyclerView user_list;
    public FirebaseFirestore db;
    public FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_b_negative);

        user_list = findViewById(R.id.user_list);
        db = FirebaseFirestore.getInstance();

        //query
        Query query = db.collection("users").whereEqualTo("blood_group", "AB-");
        //recyclerOption
        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>().setQuery(query, UserModel.class).build();

        adapter = new FirestoreRecyclerAdapter<UserModel, APositive.UserViewHolder>(options){
            @NonNull
            @Override
            public APositive.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_single, parent, false);
                return new APositive.UserViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(@NonNull APositive.UserViewHolder holder, int position, @NonNull UserModel model){
                holder.list_username.setText(model.getUsername());
                holder.list_email.setText(model.getEmail());
                holder.list_phone.setText(model.getPhone());
                holder.list_gender.setText(model.getGender());
                holder.list_blood_group.setText(model.getBlood_group());
            }

        };
        //viewholder
        user_list.setHasFixedSize(true);
        user_list.setLayoutManager(new LinearLayoutManager(this));
        user_list.setAdapter(adapter);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView list_username,list_email,list_phone,list_gender,list_blood_group;

        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            list_username = itemView.findViewById(R.id.list_username);
            list_email = itemView.findViewById(R.id.list_email);
            list_phone = itemView.findViewById(R.id.list_phone);
            list_gender = itemView.findViewById(R.id.list_gender);
            list_blood_group = itemView.findViewById(R.id.list_blood_group);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}