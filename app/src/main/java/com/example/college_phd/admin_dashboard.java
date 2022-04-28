package com.example.college_phd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class admin_dashboard extends AppCompatActivity {
    Button check;
    ImageView prev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        check = findViewById(R.id.req);
        prev = findViewById(R.id.prevbutton);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://console.firebase.google.com/u/0/project/phd-app-58d1d/overview"));
                startActivity(intent);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(admin_dashboard.this, options.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);
                finish();
            }
        });
    }
}