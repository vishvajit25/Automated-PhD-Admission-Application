package com.example.college_phd.ui.teacher_dashboard_frag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.college_phd.R;
import com.example.college_phd.info;
import com.example.college_phd.timetable_register;
import com.example.college_phd.userlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class teacher_dashboard_frag extends Fragment  {

    Button viewbutton, req,status;
    DatabaseReference ref;
    FirebaseAuth fAuth;

    private View nview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_teacher_dashboard, container, false);
        req = (Button) view.findViewById(R.id.req);
        ref = FirebaseDatabase.getInstance().getReference();
        fAuth = FirebaseAuth.getInstance();
        viewbutton = (Button) view.findViewById(R.id.view);
        status = (Button) view.findViewById(R.id.status);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String user = snapshot.child("Faculty").child(fAuth.getUid()).child("permission").getValue().toString();
               if(user.equals("true")){
                   viewbutton.setClickable(true);
                   viewbutton.setAlpha(1);
                   viewbutton.setEnabled(true);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), info.class);
                startActivity(intent);
            }
        });

        viewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), userlist.class);
                startActivity(i);


            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), timetable_register.class);
                startActivity(i);


            }
        });


        return view;
    }
}