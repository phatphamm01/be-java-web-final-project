package com.api.service.user;

import java.util.ArrayList;
import com.api.dao.user.UserDAO;
import com.api.model.user.UserModel;

import org.bson.types.ObjectId;

public class UserService implements IUserService {
  @Override
  public UserModel addUser(UserModel user) {
    UserDAO userDAO = new UserDAO();
    return userDAO.addUser(user);
  }

  @Override
  public ArrayList<UserModel> getAllUser() {
    UserDAO userDAO = new UserDAO();
    return userDAO.getAllUser();
  }

  @Override
  public UserModel getUserByID(ObjectId id) {
    UserDAO userDAO = new UserDAO();
    return userDAO.getUserByID(id);
  }

  @Override
  public UserModel getUserByUsername(String userName) {
    UserDAO userDAO = new UserDAO();
    return userDAO.getUserByUsername(userName);
  }
}
