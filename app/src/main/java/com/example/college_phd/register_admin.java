package com.example.college_phd;

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
import com.google.firebase.firestore.FirebaseFirestore;

public class register_admin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button register;
    EditText pswd,emailid;
    FirebaseFirestore fstore;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);
        reff= FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        EditText pswd = (EditText) findViewById(R.id.password);
        EditText emailid = (EditText) findViewById(R.id.email);
        Button register = (Button) findViewById(R.id.rgadmin);
        DAOadmin dao = new DAOadmin();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String password = pswd.getText().toString().trim();

                Admin admin = new Admin(emailid.getText().toString());

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
                            Toast.makeText(register_admin.this, "User Created", Toast.LENGTH_SHORT).show();
//                            userID = mAuth.getCurrentUser().getUid();
                            /*DocumentReference documentReference = fstore.collection("users").document(userID);

                            Map<String,Object> user = new HashMap<>();
                            user.put("Name", Fullname);
                            user.put("Email", email);
                            user.put("Phone No", phonenumber);
                            final Object application_id = user.put("Application ID", uniqueid);
                            documentReference.set(user);*/
                            reff.child("Admin").child(mAuth.getUid()).child("Email").setValue(email);

//                            Intent intent = new Intent(Register.this, Login.class);
////                            intent.putExtra("NAME", Fullname);
//                            startActivity(intent);
                            finish();
                            FirebaseAuth.getInstance().signOut();
                        }else {
                            Toast.makeText(register_admin.this, "ERROR !!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}