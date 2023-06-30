package com.example.railwayticketreservation.utilModels;

import java.time.LocalDate;

public class Profile {
    private Integer id;
    private String emailId;
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private String latestPage;
    private String userName;

    public Profile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getLatestPage() {
        return latestPage;
    }

    public void setLatestPage(String latestPage) {
        this.latestPage = latestPage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
