package com.capturis.tenkeyassessment.login.model;

import java.sql.Timestamp;

public class UserLogin {

  private int userLoginId;
  private String username;
  private int userId;
  private String passwordHash;
  private boolean accountLockFl;
  private Timestamp lastLoginDate;


  public UserLogin() { }

  public UserLogin(int userLoginId, String username, int userId, String passwordHash, boolean accountLockFl, Timestamp lastLoginDate) {
    this.userLoginId = userLoginId;
    this.username = username;
    this.userId = userId;
    this.passwordHash = passwordHash;
    this.accountLockFl = accountLockFl;
    this.lastLoginDate = lastLoginDate;
  }

  public int getUserLoginId() {
    return userLoginId;
  }

  public void setUserLoginId(int userLoginId) {
    this.userLoginId = userLoginId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public boolean isAccountLockFl() {
    return accountLockFl;
  }

  public void setAccountLockFl(boolean accountLockFl) {
    this.accountLockFl = accountLockFl;
  }

  public Timestamp getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(Timestamp lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }
}
