package com.csvreaderm.EX_4_mongoreader;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;





public class MainVerticle extends AbstractVerticle {
  final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  MongoClient client;


  private void syncinsert(String[] csvarray, int i2) {
    logger.info("splite 2");

    if (i2 < csvarray.length-4) {
      try {
        JsonObject document = new JsonObject().put("name", csvarray[i2])
          .put("Email", csvarray[i2 + 1])
          .put("PhoneNumber", csvarray[i2 + 2])
          .put("Adress", csvarray[i2 + 3]);
        client.save("csvfile", document, res -> {
          if (res.succeeded()) {
            //      logger.info("cool 47");
            String id = res.result();
            System.out.println("Saved book  " + id);
            int lmd = i2 + 1;
            syncinsert(csvarray, lmd);

          }else {
            logger.info("53");
            res.cause().printStackTrace();

          }

        });
      } catch (Exception e) {
        logger.error("Failed  {} ", e);

      }

    }
  }

/*

    private boolean syncinsert(String[] csvarray, int iter) {


    logger.info("splite 2");
    int i2 = 3;
    if (i2 < csvarray.length) {

      try {
        //String name=csvarray[finalI].toString();
        JsonObject document = new JsonObject().put("name", csvarray[i2])
          .put("Email", csvarray[i2 + 1])
          .put("PhoneNumber", csvarray[i2 + 2])
          .put("Adress", csvarray[i2 + 3]);
        client.insert("csvfile", document, res -> {
          if (res.succeeded()) {
            //      logger.info("cool 47");
            String id = res.result();
            System.out.println("Saved book  " + id);
            int lmd = i2 + 1;
            syncinsert(csvarray, lmd);

          } else {
            logger.info("53");
            res.cause().printStackTrace();

          }
        });
      } catch (Exception e) {
        logger.error("Failed  {} ", e);

      }
    }
  }*/


  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    JsonObject config = new JsonObject()
      .put("connection_string", "mongodb://localhost:27017")
      .put("db_name", "testcsv");
     client = MongoClient.create(vertx, config);

    FileSystem fs = vertx.fileSystem();
    fs.readFile("testcsv.csv",ar ->{
      if(ar.failed()){
        ar.cause().getMessage();
      }else {
        logger.info(ar.result());

        String[] csvarray = ar.result().toString().split(",");
        syncinsert(csvarray,3);
        Promise v1=Promise.promise();

          logger.info("hey ------------------------------------>");

      }
    });

    /*Future<FileProps> future = fs.props("testcsv.csv");

    future.onComplete((AsyncResult<FileProps> ar) -> {
      if (ar.succeeded()) {
      logger.info(ar.result().toString());
      }
    }
      );*/

  }
}
