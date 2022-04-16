package com.example.college_phd;

public class Faculty {

    private String name;
    private String emailid;
    private String phoneno;
    private boolean permission;



    public Faculty(String emailid, String name, String phoneno, Boolean permission) {
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
    public boolean getPermission() {
        return permission;
    }
    public void setPermission(boolean permission) {
        this.permission = permission;
    }

}
