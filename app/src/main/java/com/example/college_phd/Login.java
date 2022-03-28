package com.example.college_phd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.college_phd.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button Login, teacher,student;
    Button register;
    TextView mcreatebutton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        mEmail = findViewById(R.id.mail);
        mPassword = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        mcreatebutton = findViewById(R.id.createacc);
        //register = findViewById(R.id.regbutton);
        Button Login = (Button) findViewById(R.id.loginbutton);
        Intent intent = getIntent();
        String Fullname = intent.getStringExtra("NAME");


        /*register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
            }
        });*/
        mcreatebutton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Register.class)));
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
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, HomeFragment.class);
                            intent.putExtra("NAME", Fullname);
                            startActivity(new Intent(getApplicationContext(),HomeFragment.class));
                        }else {
                            Toast.makeText(Login.this, "ERROR !!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}