package com.example.college_phd;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.college_phd.databinding.ActivityNavigationDrawerBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Navigation_drawer extends AppCompatActivity {

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
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_inst, R.id.nav_faq)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        hview = navigationView.getHeaderView(0);
        user = hview.findViewById(R.id.user);
        ref.child("Applicant").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("TAG", "onDataChange: " + snapshot);
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

                Toast.makeText(Navigation_drawer.this, menuItem.getItemId() + "", Toast.LENGTH_SHORT).show();
                if (menuItem.getItemId() == R.id.nav_home) {
                Intent intent = new Intent(Navigation_drawer.this, Navigation_drawer.class);
                startActivity(new Intent(getApplicationContext(), Navigation_drawer.class));
//                FragmentManager fm = getSupportFragmentManager();
//                fm.beginTransaction().replace(R.id.fragment_gallery, new NewFragmentToReplace()).commit();
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//                    final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                    ft.replace(androidx.navigation.fragment.R.id.nav_host_fragment_container, new GalleryFragment(), "NewFragmentTag");
//                    ft.addToBackStack(null);
//                    ft.commit();
//                startActivity(intent);

                }
                if (menuItem.getItemId() == R.id.nav_inst) {
                    Intent intent = new Intent(Navigation_drawer.this, instruction.class);
                    startActivity(new Intent(getApplicationContext(), instruction.class));
                }
                if (menuItem.getItemId() == R.id.nav_faq) {
                    Intent intent = new Intent(Navigation_drawer.this, subject.class);
                    startActivity(new Intent(getApplicationContext(), subject.class));
                }
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
                Intent intent = new Intent(Navigation_drawer.this, Login.class);
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