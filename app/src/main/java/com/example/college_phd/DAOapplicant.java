package com.example.college_phd;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOapplicant {
    private DatabaseReference databaseReference;
    public DAOapplicant()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Applicant.class.getSimpleName());
    }
    public Task<Void> add(Applicant appl)
    {
        return databaseReference.push().setValue(appl);
    }
}

