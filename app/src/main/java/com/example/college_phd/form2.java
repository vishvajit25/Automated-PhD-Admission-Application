package com.example.college_phd;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class form2 extends AppCompatActivity {
    EditText tenth1, tenth2,tenth3,twelfth1,twelfth2,twelfth3,authorid,ug;
    AutoCompleteTextView tenthboard,twelfthboard,course;
    Button pay, submit;
    String userID;
    FirebaseFirestore fstore;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreference;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        tenth1 = findViewById(R.id.tenth);
        tenth2 = findViewById(R.id.tenthlink);
        ug = findViewById(R.id.clg);
        twelfth1 = findViewById(R.id.twelfth);
        twelfth2 = findViewById(R.id.tenthlink);
        authorid = findViewById(R.id.author);
        tenthboard = findViewById(R.id.autoCompleteTextView_tenth);
        course = findViewById(R.id.autoCompleteTextView_Course);
        twelfthboard = findViewById(R.id.autoCompleteTextView_twelfth);
        pay = findViewById(R.id.pay);
        submit = findViewById(R.id.submitbutton);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        DAOapplicant dao = new DAOapplicant();
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setClickable(true);
                submit.setAlpha(1);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenth = tenth1.getText().toString().trim();
                String tenthtwo = tenth2.getText().toString().trim();
                String twelfth = twelfth1.getText().toString();
                String twelfthtwo = twelfth2.getText().toString();
                String tenthboard = tenth3.getText().toString();
                String twelfthboard = twelfth3.getText().toString();
                String courseopt = course.getText().toString();
                String author = authorid.getText().toString();
                String ugperc = ug.getText().toString();
                userID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("users").document(userID);

                Map<String,Object> user = new HashMap<>();
                user.put("Tenth Board", tenthboard);
                user.put("Tenth %", tenth);
                user.put("Tenth Mark Sheet Link", tenthtwo);
                user.put("Twelfth Board", twelfthboard);
                user.put("Twelfth %", twelfth);
                user.put("Twelfth Mark Sheet Link", twelfthtwo);
                user.put("Course Opted", courseopt);
                user.put("Author ID", author);
                user.put("UG %",ugperc);
                documentReference.set(user);
            }
        });
    }
}