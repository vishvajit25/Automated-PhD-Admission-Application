package com.example.college_phd;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.college_phd.databinding.ActivityNavigationDrawerBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class instruction extends AppCompatActivity {
    MenuView.ItemView logout;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration mAppBarConfiguration;
    private @NonNull
    ActivityNavigationDrawerBinding binding;
    private View hview;
    private DatabaseReference ref;
    private FirebaseAuth fAuth;
    ImageView prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inst_fragment);
        prev = findViewById(R.id.prevbutton);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}