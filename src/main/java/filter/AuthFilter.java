package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import helpler.TokenJwt;
import io.jsonwebtoken.Claims;

@WebFilter(urlPatterns = "/api/v1/user/*")
public class AuthFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletReponse = (HttpServletResponse) response;
    String jwt = httpServletRequest.getHeader("Authorization");
    System.out.println("Authorization: " + jwt);

    if (jwt == null) {
      httpServletReponse.sendError(401, "Requires login to authenticate user");
      return;
    }

    try {
      Claims claims = TokenJwt.checkJwt(jwt);

      JSONObject userJson = new JSONObject();
      claims.forEach((key, value) -> {
        String val = value.toString().replace("\"", "");
        userJson.put(key, val);
      });

      request.setAttribute("user", userJson);
      chain.doFilter(request, response);
      return;
    } catch (Exception e) {
      httpServletReponse.sendError(401, "Incorrect or expired token");
      return;
    }

  }
}