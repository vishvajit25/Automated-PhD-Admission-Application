package com.example.college_phd;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOFaculty {
    private DatabaseReference databaseReference;
    public DAOFaculty()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Faculty.class.getSimpleName());
    }
    public Task<Void> add(Faculty fac)
    {
        return databaseReference.push().setValue(fac);
    }
}

