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
final Logger logger= LoggerFactory.getLogger(MainVerticle.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    JsonObject config = new JsonObject()
      .put("connection_string", "mongodb://localhost:27017")
      .put("db_name", "testcsv");
    MongoClient client = MongoClient.create(vertx, config);

    FileSystem fs = vertx.fileSystem();
    fs.readFile("testcsv.csv",ar ->{
      if(ar.failed()){
        ar.cause().getMessage();
      }else {
        logger.info(ar.result());
        String[] csvarray = ar.result().toString().split(",");


        logger.info("splite 2");
        for(int i2=3;i2<csvarray.length;i2++){






        //logger.info("39 --> "+client);
          int finalI = i2;
          vertx.executeBlocking(future -> {
      //    logger.info("41");
          try {
      //      logger.info("43");

            String name=csvarray[finalI].toString();
            JsonObject document = new JsonObject()
              .put("name", name)
              .put("Email", csvarray[finalI +1])
              .put("PhoneNumber", csvarray[finalI +2])
              .put("Adress", csvarray[finalI +3]);
            client.insert("csvfile", document, res -> {
           //   logger.info("45");
              if (res.succeeded()) {
          //      logger.info("cool 47");
                String id = res.result();
                System.out.println("Saved book  " + id);
                 future.complete();
              } else {
                logger.info("53");
                res.cause().printStackTrace();
                   future.complete();
              }
            });



          }catch(Exception e){
            logger.error("Failed  {} ", e);
            future.complete();
          }

          //future.complete();
        },res ->{
          System.out.println("okok");
        });
        }

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
