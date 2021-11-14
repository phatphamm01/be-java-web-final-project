package com.api.dao.user;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.mongodb.client.MongoCollection;

import org.bson.types.ObjectId;

import com.api.config.database.DatabaseConnect;
import com.api.model.user.UserModel;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO implements IUserDAO {
  private MongoCollection<UserModel> userCollection = DatabaseConnect.getInstance().getCollection("user",
      UserModel.class);

  @Override
  public UserModel addUser(UserModel user) {
    userCollection.insertOne(user);
    return user;
  }

  @Override
  public UserModel getUserByID(ObjectId id) {
    UserModel user = userCollection.find(eq(id)).first();
    return user;
  }

  @Override
  public UserModel getUserByUsername(String userName) {
    UserModel user = userCollection.find(eq("username", userName)).first();
    return user;
  }

  @Override
  public ArrayList<UserModel> getAllUser() {
    ArrayList<UserModel> userList = new ArrayList<UserModel>();

    Consumer<UserModel> addUser = new Consumer<UserModel>() {
      @Override
      public void accept(final UserModel user) {
        userList.add(user);
      }
    };

    userCollection.find().forEach(addUser);
    return userList;
  }

}
