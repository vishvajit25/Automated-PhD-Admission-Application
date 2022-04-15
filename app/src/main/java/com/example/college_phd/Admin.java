package com.example.college_phd;

public class Admin {
    public Admin(String emailid) {
        this.emailid = emailid;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    private String emailid;
}
