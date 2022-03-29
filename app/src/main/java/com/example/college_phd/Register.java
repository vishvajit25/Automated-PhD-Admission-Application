package com.example.college_phd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.Random;

public class Register extends AppCompatActivity {

    public static final String TAG = "";
    private DD4YouConfig dd4YouConfig;
    private FirebaseAuth mAuth;
    Button button,register;
    TextView textView,login;
    EditText pswd,emailid,phoneno,name;
    FirebaseFirestore fstore;
    String userID;
    SharedPreferences sharedPreference;
    SharedPreferences sp;
    DatabaseReference reff;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reff= FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Register.class));
            finish();
        }
        setContentView(R.layout.activity_register);
        Button button = (Button) findViewById(R.id.uniqueid);
        EditText pswd = (EditText) findViewById(R.id.password);
        EditText emailid = (EditText) findViewById(R.id.email);
        EditText phoneno = (EditText) findViewById(R.id.phno);
        EditText name = (EditText) findViewById(R.id.name);
        TextView login = (TextView) findViewById(R.id.lghere);
        Button register = (Button) findViewById(R.id.rgbutton);
        sp = getSharedPreferences("newsp", Context.MODE_PRIVATE);
        sharedPreference = getSharedPreferences("mypref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreference.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.commit();
        DAOapplicant dao = new DAOapplicant();
        dd4YouConfig = new DD4YouConfig();
        TextView textView = (TextView) findViewById(R.id.eid);
        button.setOnClickListener(new OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          textView.setText(getRandomString(4).toString());
                                      }
                                  }
        );
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String password = pswd.getText().toString().trim();
                String uniqueid = textView.getText().toString();
                String Fullname = name.getText().toString();
                String phonenumber = phoneno.getText().toString();
                SharedPreferences.Editor editor = sharedPreference.edit();
                editor.putString(KEY_NAME,name.getText().toString());
                SharedPreferences.Editor editor1 = sp.edit();
                editor1.putString("Username", Fullname);
                editor1.commit();
                Applicant applicant = new Applicant(emailid.getText().toString(), name.getText().toString(),textView.getText().toString());
                /*dao.add(applicant).addOnSuccessListener(suc->
                {
                    Toast.makeText(Register.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->
                {
                        Toast.makeText(Register.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
                });*/
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
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            /*DocumentReference documentReference = fstore.collection("users").document(userID);

                            Map<String,Object> user = new HashMap<>();
                            user.put("Name", Fullname);
                            user.put("Email", email);
                            user.put("Phone No", phonenumber);
                            final Object application_id = user.put("Application ID", uniqueid);
                            documentReference.set(user);*/
                            reff.child("Applicant").child(mAuth.getUid()).child("Name").setValue(Fullname);
                            reff.child("Applicant").child(mAuth.getUid()).child("ApplicantID").setValue(uniqueid);

                            Intent intent = new Intent(Register.this, Login.class);
                            intent.putExtra("NAME", Fullname);
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }else {
                            Toast.makeText(Register.this, "ERROR !!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

}