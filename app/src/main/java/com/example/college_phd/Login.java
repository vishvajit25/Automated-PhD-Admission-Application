package com.example.college_phd;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button Login, teacher, student;
    Button register;
    DatabaseReference reference;
    TextView mcreatebutton;
    TextView forgetpass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgetpass = findViewById(R.id.forgetpass);
        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });

        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student_register);
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
//                    finish();
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
        //mcreatebutton.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),Register.class)));
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty((email))) {

                    mEmail.setError("Email is required.");

                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password cant be empty.");
                }
                if (password.length() < 6) {
                    mPassword.setError("Password must  be more than 6 characters.");
                }
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, Navigation_drawer.class);
//                            intent.putExtra("NAME", Fullname);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "ERROR !!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void showRecoverPasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        LinearLayout linearLayout = new LinearLayout(this);
        final EditText emailet = new EditText(this);

        // write the email using which you registered
        emailet.setText("Email");
        emailet.setMinEms(16);
        emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailet);
        linearLayout.setPadding(10, 10, 10, 10);
        builder.setView(linearLayout);

        // Click on Recover and a email will be sent to your registered email id
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email = emailet.getText().toString().trim();
                beginRecovery(email);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void beginRecovery(String email) {
        ProgressDialog loadingBar = new ProgressDialog(this);
        loadingBar.setMessage("Sending Email....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        // calling sendPasswordResetEmail
        // open your email and write the new
        // password and then you can login
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loadingBar.dismiss();
                if (task.isSuccessful()) {
                    // if isSuccessful then done message will be shown
                    // and you can change the password
                    Toast.makeText(Login.this, "Done sent", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Login.this, "Error Occured", Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadingBar.dismiss();
                Toast.makeText(Login.this, "Error Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}