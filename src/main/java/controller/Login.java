package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import dao.UserDao;
import helpler.HandleData;
import helpler.TokenJwt;
import model.UserModel;

@WebServlet(urlPatterns = "/api/v1/login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    if (req.getQueryString() == null) {
      resp.sendError(400, "Not found");
      return;
    }

    System.out.println("login");
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
    } catch (Exception e) {
    }
  }

  private UserModel handleLogin(JsonObject data) throws Exception {
    UserDao userDao = new UserDao();

    String username = data.get("username").toString();
    String password = data.get("password").toString();

    try {
      UserModel user = userDao.login(username, password);
      return user;
    } catch (Exception err) {
      throw new Exception(err.getMessage());
    }
  }
}
