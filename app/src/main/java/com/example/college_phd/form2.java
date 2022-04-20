package com.example.college_phd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class form2 extends AppCompatActivity {
    String[] board = {"CBSE", "ISCE", "SB","NIOS","CISCE"};
    String[] courseopted = {"IoT","Deep Learning", "Machine Learning","Cloud Computing"};
    EditText tenth1, tenth2,twelfth1,twelfth2,authorid,ug;
    AutoCompleteTextView tenth3,twelfth3,course;
    Button pay, submit;
    String userID;
    FirebaseFirestore fstore;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreference;
    SharedPreferences sp;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
        ref=FirebaseDatabase.getInstance().getReference();
        tenth1 = findViewById(R.id.tenth);
        tenth2 = findViewById(R.id.tenthlink);
        ug = findViewById(R.id.clg);
        twelfth1 = findViewById(R.id.twelfth);
        twelfth2 = findViewById(R.id.tenthlink);
        authorid = findViewById(R.id.author);
        tenth3 = findViewById(R.id.TextView_tenth);
        course = findViewById(R.id.TextView_Course);
        twelfth3 = findViewById(R.id.TextView_twelfth);
        pay = findViewById(R.id.pay);
        submit = findViewById(R.id.submitbutton);
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item,board);
        tenth3.setAdapter(adapter);
        tenth3.setThreshold(1);
        tenth3.setTextColor(Color.BLACK);
        tenth3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                tenth3.showDropDown();
                return false;
            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item,board);
        twelfth3.setAdapter(adapter2);
        twelfth3.setThreshold(1);
        twelfth3.setTextColor(Color.BLACK);
        twelfth3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
               twelfth3.showDropDown();
                return false;
            }
        });
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item,courseopted);
        course.setAdapter(adapter3);
        course.setThreshold(1);
        course.setTextColor(Color.BLACK);
        course.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                course.showDropDown();
                return false;
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setClickable(true);
                submit.setAlpha(1);
                submit.setEnabled(true);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenthperc = tenth1.getText().toString().trim();
                String tenthlink = tenth2.getText().toString().trim();
                String twelfthperc = twelfth1.getText().toString();
                String twelfthlink = twelfth2.getText().toString();
                String tenthboard = tenth3.getText().toString();
                String twelfthboard = twelfth3.getText().toString();
                String courseopted = course.getText().toString();
                String author = authorid.getText().toString();
                String ugperc = ug.getText().toString();
                userID = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fstore.collection("users").document(userID);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("tenthboard").setValue(tenthboard);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("tenthpercentage").setValue(tenthperc);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("tenthcertificate").setValue(tenthlink);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("twelfthboard").setValue(twelfthboard);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("twelfthpercentage").setValue(twelfthperc);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("twelfthcertificate").setValue(twelfthlink);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("ugpercentage").setValue(ugperc);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("courseopted").setValue(courseopted);
                ref.child("Applicant").child(FirebaseAuth.getInstance().getUid().toString()).child("authorid").setValue(author);
                finish();
                //Map<String,Object> user = new HashMap<>();
                //final Object Tenth_Board = user.put("Tenth Board", tenthboard);
                //final Object Tenth_percentage = user.put("Tenth %", tenth);
                //final Object Tenth_Certificate =user.put("Tenth Mark Sheet Link", tenthtwo);
                /*final Object Twelfth_Board =user.put("Twelfth Board", twelfthboard);
                final Object Twelfth_Percentage =user.put("Twelfth %", twelfth);
                final Object Twelfth_Certificate =user.put("Twelfth Mark Sheet Link", twelfthtwo);
                final Object Course =user.put("Course Opted", courseopt);
                final Object Author_ID = user.put("Author ID", author);
                final Object UG_Percentage =user.put("UG %",ugperc);
                documentReference.set(user);*/
                Toast.makeText(form2.this, "Submitted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(form2.this, MainActivity.class);
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}