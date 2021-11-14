package com.api.model.user;

import com.api.model.mongo.MongoModel;

import org.bson.types.ObjectId;

public class UserModel extends MongoModel {
  private String fullName;
  private String username;
  private String password;

  public UserModel(ObjectId id, String fullName, String username, String password) {
    super(id);
    this.fullName = fullName;
    this.username = username;
    this.password = password;
  }

  public String getFullName() {
    return this.fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
