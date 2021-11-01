package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.UserDao;
import helpler.HandleData;

@WebServlet(urlPatterns = "/api/v1/signup")
public class Signup extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    JsonObject data = HandleData.dataToJson(req);

    try {
      this.handleSignup(data);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private void handleSignup(JsonObject data) throws Exception {
    UserDao userDao = new UserDao();

    String fullname = data.get("fullname").toString();
    String username = data.get("username").toString();
    String password = data.get("password").toString();

    try {
      userDao.register(fullname, username, password);
    } catch (Exception err) {
      throw new Exception(err.getMessage());
    }
  }
}
