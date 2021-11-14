package com.api.model.user;

public interface IUserBuilder {
  UserBuilder addFullName(String fullName);

  UserBuilder addUsername(String username);

  UserBuilder addPassword(String password);

  UserModel build();
}
