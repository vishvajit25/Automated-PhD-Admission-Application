package com.example.college_phd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class register_teacher extends AppCompatActivity {
    Button register;
    EditText pswd,emailid,phoneno,name;
    public static final String TAG = "";
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;
    SharedPreferences sharedPreference;
    SharedPreferences sp;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Register.class));
            finish();
        }
        DAOFaculty dao = new DAOFaculty();
        register = findViewById(R.id.rgtbutton);
        emailid = findViewById(R.id.email);
        pswd = findViewById(R.id.password);
        phoneno = findViewById(R.id.phno);
        name = findViewById(R.id.name);
        sp = getSharedPreferences("newsp", Context.MODE_PRIVATE);
        sharedPreference = getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreference.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.commit();
        register.setOnClickListener(v ->
        {
            Faculty faculty = new Faculty(name.getText().toString(),emailid.getText().toString(),phoneno.getText().toString());
            dao.add(faculty).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Recorded data", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
            if (TextUtils.isEmpty((emailid))) {

                emailid.setError("Email is required.");

            }
            if(TextUtils.isEmpty(password)){
                pswd.setError("Password cant be empty.");
            }
            if(password.length()<6){
                pswd.setError("Password must  be more than 6 characters.");
        });
    }
}