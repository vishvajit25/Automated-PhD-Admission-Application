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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class register_teacher extends AppCompatActivity {
    Button register;
    EditText pswd, emailid, phoneno, name;
    public static final String TAG = "";
    private FirebaseAuth mAuth;
    FirebaseFirestore fstore;
    String userID;
    DatabaseReference ref;
    Boolean permission_faculty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        }
        permission_faculty = false;
        DAOFaculty dao = new DAOFaculty();
        register = findViewById(R.id.rgtbutton);
        emailid = findViewById(R.id.email);
        pswd = findViewById(R.id.password);
        phoneno = findViewById(R.id.phno);
        name = findViewById(R.id.name);
        ref = FirebaseDatabase.getInstance().getReference();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String password = pswd.getText().toString().trim();
                String Fullname = name.getText().toString();
                String phonenumber = phoneno.getText().toString();
                String permission = permission_faculty.toString();
                Faculty faculty = new Faculty(emailid.getText().toString(), name.getText().toString(),phoneno.getText().toString(), permission_faculty);

//                dao.add(faculty).addOnSuccessListener(suc->
//                {
//                    Toast.makeText(register_teacher.this, "Registering", Toast.LENGTH_SHORT).show();
//                }).addOnFailureListener(er->
//                {
//                    Toast.makeText(register_teacher.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
//                });
                if (TextUtils.isEmpty((email))) {

                    emailid.setError("Email is required.");

                }
                if(TextUtils.isEmpty(password)){
                    pswd.setError("Password cant be empty.");
                }
                if(password.length()<6){
                    pswd.setError("Password must  be more than 6 characters.");
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(register_teacher.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fstore.collection("users").document(userID);

//                            Map<String,Object> user = new HashMap<>();
//                            user.put("Name", Fullname);
//                            user.put("Email", email);
//                            user.put("Phone No", phonenumber);
//                            documentReference.set(user);
                            ref.child("Faculty").child(mAuth.getUid()).child("Name").setValue(Fullname);
                            ref.child("Faculty").child(mAuth.getUid()).child("EmailID").setValue(email);
                            ref.child("Faculty").child(mAuth.getUid()).child("Phone No").setValue(phonenumber);
                            ref.child("Faculty").child(mAuth.getUid()).child("permission").setValue(permission);
                            Intent intent = new Intent(register_teacher.this, login_teacher.class);
                            startActivity(new Intent(getApplicationContext(),login_teacher.class));
                            FirebaseAuth.getInstance().signOut();
                        }else {
                            Toast.makeText(register_teacher.this, "ERROR !!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}