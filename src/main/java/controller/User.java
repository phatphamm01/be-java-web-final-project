package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(urlPatterns = "/api/v1/user")
public class User extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    PrintWriter pw = resp.getWriter();

    if (req.getQueryString() == null) {
      JSONObject user = (JSONObject) req.getAttribute("user");

      pw.print(user);
      pw.close();
      return;
    }

    pw.close();
    return;
  }
}
