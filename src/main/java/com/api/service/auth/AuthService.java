package com.api.service.auth;

import com.api.dao.auth.AuthDAO;
import com.api.dao.auth.IAuthDAO;
import com.api.model.user.UserModel;


public class AuthService implements IAuthService {
  @Override
  public UserModel login(String username, String password) throws Exception {
    IAuthDAO authDAO = new AuthDAO();
    return authDAO.login(username, password);
  }

  @Override
  public Boolean register(String fullName, String username, String password) throws Exception {
    IAuthDAO authDAO = new AuthDAO();
    return authDAO.register(fullName, username, password);
  }
}
