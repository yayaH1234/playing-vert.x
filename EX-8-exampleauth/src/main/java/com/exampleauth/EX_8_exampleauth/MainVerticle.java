package com.exampleauth.EX_8_exampleauth;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.HashString;
import io.vertx.ext.auth.HashingAlgorithm;
import io.vertx.ext.auth.HashingStrategy;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.mongo.*;
import io.vertx.ext.auth.mongo.impl.DefaultHashStrategy;
import io.vertx.ext.mongo.MongoClient;

import java.util.ArrayList;

public class MainVerticle extends AbstractVerticle {
  final Logger log= LoggerFactory.getLogger(MainVerticle.class);
  public static void main(String[] args){
    Runner.runExample(MainVerticle.class);
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    String usr="tototest";
    String pwd="okpwddev";
    JsonObject config=new JsonObject()
      .put("connection_string","mongodb://localhost:27017")
      .put("db_name","testcsv");

    MongoClient client = MongoClient.createShared(vertx, config);


    MongoUserUtil mongoUserUtil=MongoUserUtil.create(client);

    // pour l'operation d'insertion
    Promise<String> insertUserPromise = Promise.promise();

    mongoUserUtil.createUser(usr,pwd, hr -> {
      if(hr.succeeded()){
        String _id = hr.result();
        log.info("created .....");
        insertUserPromise.complete(_id);
      }else{
        log.info("error creation !!!!!");
        insertUserPromise.fail("Exceptions ......!!!!");
      }
    });

    insertUserPromise.future().compose(v -> {
      Promise<String> promise = Promise.promise();

    MongoAuthenticationOptions options = new MongoAuthenticationOptions();
/*
   options.setUsernameField(usr);
    options.setPasswordField(pwd);*/
    log.info("f5 !!!!!");
    MongoAuthentication authenticationProvider =
      MongoAuthentication.create(client, options);


    JsonObject authInfo = new JsonObject()
      .put("username",usr )
      .put("password", pwd);


    authenticationProvider.authenticate(authInfo)
      .onSuccess(user -> System.out.println("User: " + user.principal()))
      .onFailure(err -> {
        // Failed!
        log.info("err  !!!!!"+err);
      });
    return promise.future();
    }).onComplete(h -> {
      if (h.failed()) {
        log.info("failed");
      } else {
        log.debug("replay: "  );
      }
    });
/*
    String usr="tototest";
    String pwd="okpwddev";
    JsonObject config=new JsonObject()
      .put("connection_string","mongodb://localhost:27017")
      .put("db_name","testcsv");

    MongoClient client = MongoClient.createShared(vertx, config);

    MongoAuthenticationOptions options = new MongoAuthenticationOptions();

    options.setUsernameField(usr);
    options.setPasswordField(pwd);
    log.info("f5 !!!!!");
    MongoAuthentication authenticationProvider =
      MongoAuthentication.create(client, options);


    */
/*
    MongoAuth.create(client,new JsonObject()).insertUser(usr, pwd, new ArrayList<>(), new ArrayList<>(), res -> {
      if (res.succeeded()) {
        String _id = res.result();
        log.info("authProvider.insertUser done successfully, _id: " + _id);

      }

    });*/

 /*   JsonObject authInfo = new JsonObject()
      .put("username",usr)
      .put("password", pwd);

    log.info("after auth");
    MongoAuth.create(client,new JsonObject()).authenticate(authInfo,h -> {
      log.info("in auth");
      if(h.succeeded()){
        log.info("okok "+h.result());
      }else log.info(h.cause());
    });
*/


  }
}
