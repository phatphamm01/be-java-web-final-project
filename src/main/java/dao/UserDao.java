package dao;

import java.util.ArrayList;
import java.util.function.Consumer;

import com.mongodb.client.MongoCollection;

import org.bson.types.ObjectId;

import config.DatabaseConnect;
import helpler.Encryption;
import model.UserModel;

import static com.mongodb.client.model.Filters.eq;

public class UserDao {
  private MongoCollection<UserModel> userCollection = new DatabaseConnect("user").getCollection("user",
      UserModel.class);

  public UserModel addUser(UserModel user) {
    userCollection.insertOne(user);
    return user;
  }

  public UserModel getUserByID(ObjectId id) {
    UserModel user = userCollection.find(eq(id)).first();
    return user;
  }

  public UserModel getUserByUserName(String userName) {
    UserModel user = userCollection.find(eq("username", userName)).first();
    return user;
  }

  public ArrayList<UserModel> getAllUser() {
    ArrayList<UserModel> userList = new ArrayList<UserModel>();

    Consumer<UserModel> addUser = new Consumer<UserModel>() {
      @Override
      public void accept(final UserModel user) {
        userList.add(user);
      }
    };

    userCollection.find().forEach(addUser);
    return userList;
  }

  public Boolean register(String fullName, String username, String password) throws Exception {
    if (this.getUserByUserName(username) != null) {
      throw new Exception("Tài khoản đã tồn tại");
    }

    String passHash = Encryption.encrypt(password, Encryption.key());
    this.addUser(new UserModel(fullName, username, passHash));

    return true;
  }

  public UserModel login(String username, String password) throws Exception {
    UserModel user = this.getUserByUserName(username);
    if (user == null) {
      throw new Exception("Tài khoản không tồn tại");
    }

    String passHass = Encryption.decrypt(user.getPassword(), Encryption.key());

    if (!password.equals(passHass)) {
      throw new Exception("Mật khẩu sai");
    }

    return user;
  }
}
