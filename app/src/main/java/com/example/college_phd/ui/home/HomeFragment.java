package com.example.college_phd.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.college_phd.R;

public class HomeFragment extends Fragment {

    TextView t;
    SharedPreferences sharedPreference;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = null;
        v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
        SharedPreferences sp = getApplicationContext().getSharedPreferences("newsp", MODE_PRIVATE);
        String username = sp.getString("Username", "");
        sharedPreference = getSharedPreferences("mypref",MODE_PRIVATE);
        String s1 = sharedPreference.getString("name", null);
        String name = sharedPreference.getString("name",null);
        /*Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");*/
        TextView Fullname = findViewById(R.id.welcome);
        Fullname.setText("Welcome "+username);
    }

    private Context getApplicationContext() {
    }


}