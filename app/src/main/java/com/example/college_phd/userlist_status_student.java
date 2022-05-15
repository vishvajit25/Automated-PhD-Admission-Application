package com.example.college_phd;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userlist_status_student extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter_status myAdapter;
    private DatabaseReference ref;
    private FirebaseAuth fAuth;
    ArrayList<User_status> list_status;
    ImageView prev;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        prev = findViewById(R.id.prevbutton);
        fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.userList_result);
        database = FirebaseDatabase.getInstance().getReference("timetable");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list_status = new ArrayList<User_status>();
        myAdapter = new MyAdapter_status(this,list_status);
        recyclerView.setAdapter(myAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.child("timetable").getChildren()){

                    User_status user_status = dataSnapshot.getValue(User_status.class);
                    uid = fAuth.getUid();
                    String appId=snapshot.child("Applicant").child(uid).child("ApplicantID").getValue().toString();
                    if(appId.equals(user_status.getAppID())){
                        list_status.add(user_status);
                    }




                }
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}