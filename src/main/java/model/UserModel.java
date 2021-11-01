package model;

import org.bson.types.ObjectId;

public class UserModel {
  private ObjectId id;
  private String fullName;
  private String username;
  private String password;

  public UserModel() {
  }

  public UserModel(String id, String username, String fullname) {
    this.id = new ObjectId(id);
    this.username = username;
    this.fullName = fullname;
  }

  public UserModel(ObjectId id, String fullName, String username, String password) {
    this.id = id;
    this.fullName = fullName;
    this.username = username;
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
