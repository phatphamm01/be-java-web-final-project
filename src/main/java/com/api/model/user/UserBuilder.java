package com.api.model.user;

import com.api.model.mongo.IMongoBuilder;

import org.bson.types.ObjectId;

public class UserBuilder implements IMongoBuilder<UserBuilder>, IUserBuilder {
  private ObjectId id;
  private String fullName;
  private String username;
  private String password;

  @Override
  public UserBuilder addFullName(String fullName) {
    this.fullName = fullName;
    return this;
  }

  @Override
  public UserBuilder addUsername(String username) {
    this.username = username;
    return this;
  }

  @Override
  public UserBuilder addPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public UserModel build() {
    return new UserModel(id, fullName, username, password);
  }

  @Override
  public UserBuilder addId(ObjectId id) {
    this.id = id;
    return this;
  }

}
