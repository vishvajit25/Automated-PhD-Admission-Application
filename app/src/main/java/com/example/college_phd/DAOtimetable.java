package com.example.college_phd;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOtimetable {

    private DatabaseReference databaseReference;
    public DAOtimetable()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(timetable.class.getSimpleName());
    }
    public Task<Void> add(timetable tt)
    {
        return databaseReference.push().setValue(tt);
    }
}

