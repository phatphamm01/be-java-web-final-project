package com.api.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.api.helpler.HandleData;
import com.api.service.auth.AuthService;
import com.api.service.auth.IAuthService;
import com.google.gson.JsonObject;

@WebServlet(urlPatterns = "/api/v1/signup")
public class Signup extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    JsonObject data = HandleData.dataToJson(req);

    try {
      this.handleSignup(data);
    } catch (Exception e) {
      throw new ServletException(e.getMessage());
    }
  }

  private void handleSignup(JsonObject data) throws Exception {
    IAuthService userService = new AuthService();

    String fullname = data.get("fullname").toString();
    String username = data.get("username").toString();
    String password = data.get("password").toString();

    try {
      boolean isCheck = userService.register(fullname, username, password);
      System.out.println(isCheck);
    } catch (Exception err) {
      throw new Exception(err.getMessage());
    }
  }
}
