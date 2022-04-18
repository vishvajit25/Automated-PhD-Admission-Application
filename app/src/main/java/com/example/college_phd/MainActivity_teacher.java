package com.example.college_phd;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.college_phd.databinding.ActivityNavigationDrawerTBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_teacher extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private @NonNull ActivityNavigationDrawerTBinding binding;
    private View hview;
    private TextView user;
    private DatabaseReference ref;
    private FirebaseAuth fAuth;
    MenuView.ItemView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();
        binding = ActivityNavigationDrawerTBinding.inflate(getLayoutInflater());
//                ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbarT);
        binding.appBarMain.toolbarT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        DrawerLayout drawer = binding.drawerLayoutT;
        NavigationView navigationView =  binding.navViewT;
        hview = navigationView.getHeaderView(0);
        user = hview.findViewById(R.id.user_t);
        logout = hview.findViewById(R.id.logoutt);
        //==================================================================
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////              String facultyname = snapshot.child("name").getValue().toString();
//                String userstring = snapshot.child("Faculty").child(fAuth.getUid()).child("name").getValue().toString();
//                user.setText(userstring);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        //=======================================================================



        //SharedPreferences sp = getApplicationContext().getSharedPreferences("newsp", Context.MODE_PRIVATE);
        //String username = sp.getString("Username", "");
        //sharedPreference = getSharedPreferences("mypref",MODE_PRIVATE);
        //String s1 = sharedPreference.getString("name", null);
        //String name = sharedPreference.getString("name",null);
        /*Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");*/
        //user = findViewById(R.id.user);
        //user.setText(""+username);

        FirebaseAuth.getInstance().getUid();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_teacher, R.id.nav_faq_teacher, R.id.nav_inst)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_teacher);
        NavigationUI.setupActionBarWithNavController(MainActivity_teacher.this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logoutt:
                Intent intent = new Intent(MainActivity_teacher.this, login_teacher.class);
                startActivity(intent);
                FirebaseAuth.getInstance().signOut();
        }

        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_teacher);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}