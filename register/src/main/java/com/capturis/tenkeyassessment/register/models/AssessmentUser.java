package com.capturis.tenkeyassessment.register.models;

import java.sql.Timestamp;

public class AssessmentUser {

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserLoginId() {
    return userLoginId;
  }

    public void setUserLoginId(int userLoginId) {
    this.userLoginId = userLoginId;
  }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setJobCode(String jobCode){
        this.jobCode = jobCode;
    }

    public String getJobCode(){
        return jobCode;
    }

    public String getUsername() {
    return username;
  }

    public void setUsername(String username) {
    this.username = username;
  }

    public String getPasswordHash() { return  passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }



private int userId;
    private int userLoginId;
private String firstName;
private String lastName;
private String emailAddress;
private String phoneNumber;
private int roleId;
private Timestamp createdDate;
private String street;
private String city;
private String state;
private String zipCode;
private String country;
private String jobCode;
private String username;
private String passwordHash;
}
