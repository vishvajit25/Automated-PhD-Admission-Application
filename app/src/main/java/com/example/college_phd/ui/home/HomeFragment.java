package com.example.college_phd.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.college_phd.R;
import com.example.college_phd.form1;
import com.example.college_phd.form2;

public class HomeFragment extends Fragment  {

    TextView fa2, va, da;
    TextView fa1;
    private View nview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_home, container, false);

        fa1 = (TextView) view.findViewById(R.id.fillapplication);
        fa2 = (TextView) view.findViewById(R.id.appli_link2);
        va = (TextView) view.findViewById(R.id.appli_link3);
        da = (TextView) view.findViewById(R.id.appli_link4);


        fa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), form1.class);
                startActivity(intent);
            }
        });
        fa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), form2.class);
                startActivity(intent);
            }
        });



        return view;
    }
}