package com.example.college_phd;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOadmin {
    private DatabaseReference databaseReference;
    public DAOadmin()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Applicant.class.getSimpleName());
    }
    public Task<Void> add(Admin admin)
    {
        return databaseReference.push().setValue(admin);
    }
}

