package com.example.college_phd;

public class Faculty {

    private String name;
    private String emailid;
    private String phoneno;

    public Faculty(String name, String emailid, String phoneno) {
        this.name = name;
        this.emailid = emailid;
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
