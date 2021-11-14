package com.api.dao.auth;

import com.api.model.user.UserModel;

public interface IAuthDAO {
  UserModel login(String username, String password) throws Exception;

  Boolean register(String fullName, String username, String password) throws Exception;
}
