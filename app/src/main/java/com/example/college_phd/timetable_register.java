package com.example.college_phd;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class timetable_register extends AppCompatActivity {
    Button set;
    EditText name, appid, date2, time2;
    AutoCompleteTextView status;
    final Calendar myCalendar = Calendar.getInstance();
    String[] statusarray = {"Selected", "Waiting List", "Rejected"};
    public static final String TAG = "";
    private FirebaseAuth mAuth;
    DatabaseReference reff;
    FirebaseFirestore fstore;
    String userID;
    int hour, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_register);
        reff = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        DAOtimetable dao = new DAOtimetable();
        set = findViewById(R.id.set);
        name = findViewById(R.id.name);
        appid = findViewById(R.id.appid);
        date2 = findViewById(R.id.date);
        time2 = findViewById(R.id.time);
        status = (AutoCompleteTextView) findViewById(R.id.statusstudent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item, statusarray);
        status.setAdapter(adapter);
        status.setThreshold(1);
        status.setTextColor(Color.BLACK);

        status.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                status.showDropDown();
                status.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        String Statustext = s.toString();
                        if (Statustext.equals("Waiting List")) {
                            date2.setVisibility(View.GONE);
                            time2.setVisibility(View.GONE);
                        }
                        if (Statustext.equals("Rejected")) {
                            date2.setVisibility(View.GONE);
                            time2.setVisibility(View.GONE);
                        }
                        if(Statustext.equals("Waiting List")){
                            date2.setVisibility(View.GONE);
                            time2.setVisibility(View.GONE);
                        }
                        if(Statustext.equals("Selected")){
                            date2.setVisibility(View.VISIBLE);
                            time2.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                return false;
            }
        });


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();

            }

            private void updateLabel() {
                String myFormat = "dd/MM/yy";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                date2.setText(dateFormat.format(myCalendar.getTime()));
            }
        };
        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(timetable_register.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);

        time2.setInputType(InputType.TYPE_NULL);
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTime(hour, min);
            }
        });
        String namedb = name.getText().toString().trim();
        String applicationiddb = appid.getText().toString();
        String datedb = date2.getText().toString();
        String timedb = time2.getText().toString();
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timetable timetable = new timetable(name.getText().toString(), appid.getText().toString(),status.getText().toString(), date2.getText().toString(), time2.getText().toString());
                reff.child("timetable").push().setValue(timetable);
                Toast.makeText(timetable_register.this, "Status has been Set", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(timetable_register.this, teacher_dashboard_frag.class);
//                startActivity(intent);
                finish();

            }
        });
    }

    void showTime(int hours, int minte) {
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(timetable_register.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                time2.setText(selectedHour + ":" + selectedMinute);
                hour = selectedHour;
                min = selectedMinute;
            }
        }, hours, minte, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();

    }
}