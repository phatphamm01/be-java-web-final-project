package com.api.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.api.helpler.HandleData;
import com.api.helpler.TokenJwt;
import com.api.model.user.UserModel;
import com.api.service.auth.AuthService;
import com.api.service.auth.IAuthService;
import com.google.gson.JsonObject;

@WebServlet(urlPatterns = "/api/v1/login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");

    if (req.getQueryString() == null) {
      resp.sendError(400, "Not found");
      return;
    }

    JsonObject data = HandleData.queryStringToJson(req);

    try {
      UserModel user = this.handleLogin(data);
      String token = TokenJwt.generateJwt(user);
      Cookie cookie = new Cookie("auth", token);

      resp.addCookie(cookie);
    } catch (Exception e) {
    }

    return;

  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");

    JsonObject data = HandleData.dataToJson(req);

    try {
      UserModel user = this.handleLogin(data);
      String token = TokenJwt.generateJwt(user);

      Cookie cookie = new Cookie("auth", token);
      cookie.setMaxAge(10);

      resp.addCookie(cookie);
    } catch (Exception err) {
      throw new ServletException(err.getMessage());
    }
  }

  private UserModel handleLogin(JsonObject data) throws Exception {
    String username = data.get("username").toString();
    String password = data.get("password").toString();

    try {
      IAuthService userService = new AuthService();
      UserModel user = userService.login(username, password);
      return user;
    } catch (Exception err) {
      throw new Exception(err.getMessage());
    }
  }
}
