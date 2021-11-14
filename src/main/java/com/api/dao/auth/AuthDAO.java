package com.api.dao.auth;

import com.api.dao.user.IUserDAO;
import com.api.dao.user.UserDAO;
import com.google.gson.JsonObject;
import com.api.helpler.Encryption;
import com.api.model.user.UserMapping;
import com.api.model.user.UserModel;

public class AuthDAO implements IAuthDAO {
  @Override
  public Boolean register(String fullName, String username, String password) throws Exception {
    IUserDAO userDAO = new UserDAO();
    UserModel user = userDAO.getUserByUsername(username);
    boolean checkUserIsNull = user == null;

    if (!checkUserIsNull) {
      return false;
    }

    String passHash = Encryption.encrypt(password, Encryption.key());
    UserModel userNew = this.parseUser(fullName, username, passHash);

    userDAO.addUser(userNew);
    return true;
  }

  @Override
  public UserModel login(String username, String password) throws Exception {
    IUserDAO userDAO = new UserDAO();
    UserModel user = userDAO.getUserByUsername(username);
    boolean checkUserIsNull = user == null;

    if (checkUserIsNull) {
      throw new Exception("Tài khoản không tồn tại");
    }

    String passHass = Encryption.decrypt(user.getPassword(), Encryption.key());

    boolean checkPass = password.equals(passHass);
    if (checkPass) {
      throw new Exception("Mật khẩu sai");
    }

    return user;
  }

  private UserModel parseUser(String fullName, String username, String password) throws Exception {
    JsonObject userJson = new JsonObject();
    userJson.addProperty("fullname", fullName);
    userJson.addProperty("username", username);
    userJson.addProperty("password", password);

    try {
      return UserMapping.map(userJson);
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }
}
