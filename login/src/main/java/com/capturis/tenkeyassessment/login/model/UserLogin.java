package com.capturis.tenkeyassessment.login.model;

public class UserLogin {

  private String plainText;
  private String hashed;
  private boolean matched;

  public UserLogin(String plainText, String hashed, boolean matched) {
    this.plainText = plainText;
    this.hashed = hashed;
    this.matched = matched;
  }

  public String getPlainText() {
    return plainText;
  }

  public void setPlainText(String plainText) {
    this.plainText = plainText;
  }

  public String getHashed() {
    return hashed;
  }

  public void setHashed(String hashed) {
    this.hashed = hashed;
  }

  public boolean isMatched() {
    return matched;
  }

  public void setMatched(boolean matched) {
    this.matched = matched;
  }
}
