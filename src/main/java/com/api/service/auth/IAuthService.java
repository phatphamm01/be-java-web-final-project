package com.api.service.auth;

import com.api.model.user.UserModel;

public interface IAuthService {
  UserModel login(String username, String password) throws Exception;

  Boolean register(String fullName, String username, String password) throws Exception;
}
