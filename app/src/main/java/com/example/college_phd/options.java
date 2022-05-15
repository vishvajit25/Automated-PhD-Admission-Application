package com.example.college_phd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class options extends AppCompatActivity {

    ImageView prev;
    TextView t1,t2,t3;
    RadioGroup rg;
    RadioButton r1,r2,r3;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        prev = findViewById(R.id.prevbutton);
        r1 = findViewById(R.id.rb1);
        r2 = findViewById(R.id.rb2);
        r3 = findViewById(R.id.rb3);
        rg = findViewById(R.id.rg);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (options.this, Login.class);
                startActivity(intent);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (options.this,login_teacher.class);
                startActivity(intent);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (options.this,login_admin.class);
                startActivity(intent);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (options.this,intro.class);
                startActivity(intent);
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId)
                {
                    case R.id.rb1:
                        Intent intent = new Intent (options.this,Login.class);
                        startActivity(intent);
                        break;

                    case R.id.rb2:
                        Intent intent2 = new Intent (options.this,login_teacher.class);
                        startActivity(intent2);
                        break;
                    case R.id.rb3:
                        Intent intent3 = new Intent (options.this,login_admin.class);
                        startActivity(intent3);
                        break;
                }
            }
        });


    }
}