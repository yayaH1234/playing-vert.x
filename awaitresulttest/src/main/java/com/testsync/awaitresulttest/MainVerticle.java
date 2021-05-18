package com.testsync.awaitresulttest;

import co.paralleluniverse.fibers.Suspendable;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.file.FileSystem;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.sync.Sync;

public class MainVerticle extends AbstractVerticle {
  final Logger logger= LoggerFactory.getLogger(MainVerticle.class);
  MongoClient client;
  public static void main (String[] args){
    Runner.runExample(MainVerticle.class);
  }

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
  @Suspendable
  public String readData() {
    boolean exists = Sync.awaitResult(h -> vertx.fileSystem().exists("testcsv.csv", h));
    if (exists) {
      Buffer buf = Sync.awaitResult(h -> vertx.fileSystem().readFile("testcsv.csv", h));
      return buf.toString();
    }
    return "";
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    JsonObject config = new JsonObject()
      .put("connection_string", "mongodb://localhost:27017")
      .put("db_name", "testcsv");
    client = MongoClient.create(vertx, config);

    Message<String> reply = Sync.awaitResult(h ->{
      FileSystem fs = vertx.fileSystem();
      fs.readFile("testcsv.csv",ar ->{
        if(ar.failed()){
          ar.cause().getMessage();
        }else {
          logger.info(ar.result());

          String[] csvarray = ar.result().toString().split(",");
    //      vertx.eventBus().consumer("read", Sync.fiberHandler(m -> m.reply(readData())));//  syncinsert(csvarray,3);

          logger.info("hey ------------------------------------>");

        }
      });
    });
    reply.reply("operation done");

  }
}
