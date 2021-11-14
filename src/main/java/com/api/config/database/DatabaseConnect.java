package com.api.config.database;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DatabaseConnect implements IDatabase {
  private static DatabaseConnect instance;

  private MongoClient mongoClient;
  private CodecRegistry pojoCodecRegistry;
  private MongoDatabase dataBase;

  public static DatabaseConnect getInstance() {
    if (instance == null) {
      instance = new DatabaseConnect();
    }
    return instance;
  }

  public DatabaseConnect() {
    // mongoClient = MongoClients.create(
    // "mongodb+srv://phatphamm01:JHK5ahYrQeA3jxB5@cluster0.dbjqr.mongodb.net/test?retryWrites=true&w=majority&ssl=true");

    mongoClient = MongoClients.create();
    pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    dataBase = mongoClient.getDatabase("music").withCodecRegistry(pojoCodecRegistry);
  }

  public <Model> MongoCollection<Model> getCollection(String name, Class<Model> type) {
    return dataBase.getCollection(name, type);
  }
}
