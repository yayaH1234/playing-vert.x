package com.testsafeauth.EX_8_mongoauth2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

public class MainVerticle extends AbstractVerticle {
  final Logger log= LoggerFactory.getLogger(MainVerticle.class);
  public static void main(String[] args){
    Runner.runExample(MainVerticle.class);
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {


/*
    JsonObject config=new JsonObject()
      .put("connection_string","mongodb://localhost:27017")
      .put("db_name","testcsv");

    MongoClient client = MongoClient.createShared(vertx, config);
    MongoAuthenticationOptions options = new MongoAuthenticationOptions();
    MongoAuthentication authenticationProvider =
      MongoAuthentication.create(client, options);


    JsonObject authInfo = new JsonObject()
      .put("username", "devox")
      .put("password", "devox");

    authenticationProvider.authenticate(authInfo)
      .onSuccess(user -> System.out.println("User: " + user.principal()))
      .onFailure(err -> {
        // Failed!
      });
*/

  }
}
