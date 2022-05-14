package com.example.college_phd;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.college_phd.databinding.ActivityNavigationDrawerTBinding;
import com.example.college_phd.ui.gallery.GalleryFragment;
import com.example.college_phd.ui.teacher_dashboard_frag.teacher_dashboard_frag;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity_teacher extends AppCompatActivity {

    private @NonNull
    ActivityNavigationDrawerTBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawerLayout;
    private DatabaseReference ref;
    private TextView usert;
    private View hview;

    FragmentManager fm = getSupportFragmentManager();
    FragmentTransaction ft = fm.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityNavigationDrawerTBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ref = FirebaseDatabase.getInstance().getReference();
        setSupportActionBar(binding.appBarMain.toolbarT);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        getSupportActionBar().setTitle("Dashboard");
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_t);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view_t);
        hview = navView.getHeaderView(0);
        ft.replace(R.id.nav_host_teacher, new GalleryFragment());

        usert = hview.findViewById(R.id.user_t);
        ref.child("Faculty").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usert.setText(snapshot.child("Name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                Toast.makeText(MainActivity_teacher.this, "CLICK" + menuItem, Toast.LENGTH_SHORT).show();

                ft.replace(R.id.nav_host_teacher, new GalleryFragment());
                if (menuItem.getItemId() == R.id.nav_teacherhome) {
                    //TODO: Start new activity
                    ft.replace(R.id.nav_host_teacher, new teacher_dashboard_frag());
                }
                if (menuItem.getItemId() == R.id.nav_inst) {
                    ft.replace(R.id.nav_host_teacher, new GalleryFragment());
//                    //TODO: Start new activity
//                    Intent intent = new Intent(MainActivity_teacher.this, instruction.class);
////                    startActivity(new Intent(getApplicationContext(), com.ui.gallery.GalleryFragment.class));
//                    startActivity(intent);
                }

                return true;

            }
        });

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logoutt) {
            //TODO: Logout
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(MainActivity_teacher.this, "Logged Out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), login_teacher.class));
            finish();
        } else if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_teacher);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
    }
}