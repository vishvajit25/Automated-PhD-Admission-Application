package com.example.college_phd;

public class Applicant {
    private String name;
    private String applicantid;
    private String tenthpercentage;
    private String twelfthpercentage;
    private String tenthlink;
    private String twelfthlink;
    private String tenthboard;
    private String twelfthboard;
    private String course;
    private String authorid;
    private String ugperc;
    public Applicant(String s, String name, String applicantid){
        this.name = name;
        this.applicantid=applicantid;
        this.tenthboard="";
        this.twelfthboard="";
        this.tenthlink = "";
        this.twelfthlink = "";
        this.tenthpercentage = "";
        this.twelfthpercentage = "";
        this.authorid ="";
        this.course = "";
        this.ugperc = "";

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicantid() {
        return applicantid;
    }
    public String getTenthpercentage() {
        return tenthpercentage;
    }
    public String getTwelfthpercentage() {
        return twelfthpercentage;
    }
    public String getTenthlink() {
        return tenthlink;
    }
    public String getTenthboard() {
        return tenthboard;
    }
    public String getTwelfthlink() {
        return twelfthlink;
    }
    public String getTwelfthboard() {
        return twelfthboard;
    }
    public String getCourse() {
        return course;
    }
    public String getAuthorid() {
        return authorid;
    }
    public String getUgperc() {
        return ugperc;
    }


    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }
    public void setTenthpercentage(String tenthpercentage) {
        this.tenthpercentage = tenthpercentage;
    }
    public void setTwelfthpercentage(String twelfthpercentage) {
        this.twelfthpercentage = twelfthpercentage;
    }
    public void setTenthlink(String tenthlink) {
        this.tenthlink = tenthlink;
    }
    public void setTwelfthlink(String twelfthlink) {
        this.twelfthlink = twelfthlink;
    }
    public void setTenthboard(String tenthboard) {
        this.tenthboard = tenthboard;
    }
    public void setTwelfthboard(String twelfthboard) {
        this.twelfthboard = twelfthboard;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }
    public void setUgperc(String ugperc) {
        this.ugperc = ugperc;
    }
}
