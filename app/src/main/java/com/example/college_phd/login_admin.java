package com.example.college_phd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.MainActivity_teacher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_admin extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button Login, register;
    EditText mEmail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        mEmail = findViewById(R.id.mail);
        mPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        Button Login = (Button) findViewById(R.id.loginbutton);
        register = findViewById(R.id.admin);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_admin.this, admin_dashboard.class);
                startActivity(new Intent(getApplicationContext(),admin_dashboard.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty((email))) {

                    mEmail.setError("Email is required.");

                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password cant be empty.");
                }
                if(password.length()<6){
                    mPassword.setError("Password must  be more than 6 characters.");
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_admin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent (login_admin.this, MainActivity_teacher.class);
                            startActivity(intent);


                        }else {
                            Toast.makeText(login_admin.this, "ERROR !!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}