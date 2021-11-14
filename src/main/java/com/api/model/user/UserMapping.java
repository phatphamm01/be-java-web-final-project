package com.api.model.user;

import com.api.model.common.IMapping;
import com.google.gson.JsonObject;

import org.bson.types.ObjectId;

public class UserMapping implements IMapping<UserModel> {
  public static final UserModel map(JsonObject object) {
    String id = object.get("id").toString();
    String username = object.get("username").toString();
    String fullname = object.get("fullname").toString();
    String password = object.get("password").toString();

    return new UserBuilder().addFullName(fullname).addUsername(username).addPassword(password).addId(new ObjectId(id))
        .build();
  }
}
