package helpler;

import java.time.Instant;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.UserModel;

public class TokenJwt {
  static final String SECRET = "PhamMinhPhat";
  static final String ISSUER = "WebDEV";
  static final String SUBJECT = "JWT Auth";
  static final String TOKEN_PREFIX = "Bearer";
  static final String HEADER_STRING = "Authorization";

  public static String generateJwt(UserModel user) {
    String tokenServer = null;

    try {
      Date date = Common.time(1000);

      String id = user.getId().toString();
      String username = user.getUsername();
      String fullname = user.getFullName();

      tokenServer = Jwts.builder().setIssuer(ISSUER).setSubject(SUBJECT).setId(id).claim("username", username)
          .claim("fullname", fullname).setIssuedAt(Date.from(Instant.now())).setExpiration(date)
          .signWith(SignatureAlgorithm.HS256, SECRET.getBytes("UTF-8")).compact();
    } catch (Exception e) {
      System.out.println(e);
    }

    return tokenServer;
  }

  public static Claims checkJwt(String token) throws Exception {
    Claims claims = null;

    try {
      claims = Jwts.parser().setSigningKey(SECRET.getBytes("UTF-8")).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
          .getBody();
    } catch (Exception e) {
      throw e;
    }

    return claims;
  }
}
