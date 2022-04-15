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
import com.example.college_phd.subject;

public class teacher_dashboard_frag extends Fragment  {

    Button viewbutton, req;

    private View nview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_teacher_dashboard, container, false);
        req = (Button) view.findViewById(R.id.req);
        viewbutton = (Button) view.findViewById(R.id.view);
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
                Intent intent = new Intent(getActivity(), subject.class);
                startActivity(intent);
            }
        });


        return view;
    }
}