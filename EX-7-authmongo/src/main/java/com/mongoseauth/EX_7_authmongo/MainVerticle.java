package com.mongoseauth.EX_7_authmongo;

import com.mongoseauth.EX_7_authmongo.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.mongo.MongoClient;

import java.util.ArrayList;


public class MainVerticle extends AbstractVerticle {
  final Logger log= LoggerFactory.getLogger(MainVerticle.class);
  private MongoAuth authProvider;
  public static void main(String[] args){
    Runner.runExample(MainVerticle.class);
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    JsonObject config=new JsonObject()
                          .put("connection_string","mongodb://localhost:27017")
                          .put("db_name","testcsv");

    MongoClient client=MongoClient.createShared(vertx,config);

    JsonObject options = new JsonObject();





    authProvider =
      MongoAuth.create(client, options);


    // pour l'operation d'insertion
    Promise<String> insertUserPromise = Promise.promise();

    String username="devox2";
    String pwd="okpwddev";
    // insert user (username, password, roles, permissions) with auth provider
    authProvider.insertUser(username, pwd, new ArrayList<>(), new ArrayList<>(), res -> {
      if (res.succeeded()) {
        String _id = res.result();
        log.info("authProvider.insertUser done successfully, _id: " + _id);
        insertUserPromise.complete(_id);
      } else {
        if (res.cause() instanceof com.mongodb.MongoWriteException) {
          log.info(res.cause().getMessage());
          insertUserPromise.fail("Exceptions.DUPLICATED_USERNAME");
        } else {
          log.error(res.cause(), res.cause());
          insertUserPromise.fail("Exceptions.TECHNICAL_ERROR");
        }
      }
    });



    // test auth
    JsonObject authInfo = new JsonObject()
      .put("username", username)
      .put("password", pwd);


    authProvider.authenticate(authInfo)
      .onSuccess(user -> System.out.println("User: " + user.principal()))
      .onFailure(err -> {
        // Failed

      });
  }
}
