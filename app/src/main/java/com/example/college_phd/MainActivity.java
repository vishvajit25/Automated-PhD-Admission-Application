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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.college_phd.databinding.ActivityNavigationDrawerBinding;
import com.example.college_phd.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private @NonNull ActivityNavigationDrawerBinding binding;
    private View hview;
    private TextView user;
    private DatabaseReference ref;
    private FirebaseAuth fAuth;
    MenuView.ItemView logout;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference();
        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

//        binding.appBarNavigationDrawer.toolbar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });
        NavigationView navigationView =  binding.navView;
        hview = navigationView.getHeaderView(0);
        user = hview.findViewById(R.id.user);
        ref.child("Applicant").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user.setText(snapshot.child("Name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        logout = hview.findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), login_teacher.class));
//            }
//        });
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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        getSupportActionBar().setTitle("Dashboard");

        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

//                Toast.makeText(MainActivity.this, menuItem.getItemId() + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, GalleryFragment.class);
//                    startActivity(new Intent(getApplicationContext(), com.ui.gallery.GalleryFragment.class));
                startActivity(intent);


                return true;

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                Intent intent = new Intent(MainActivity.this, Login.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                finish();
                break;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

        }

        return true;
    }

}