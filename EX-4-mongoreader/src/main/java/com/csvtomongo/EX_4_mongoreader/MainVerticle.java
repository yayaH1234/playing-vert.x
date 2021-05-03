package com.csvtomongo.EX_4_mongoreader;




import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

public class MainVerticle extends AbstractVerticle {

  private static final Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    //MongoClient client = MongoClient.createShared(vertx, config, "MyPoolName");
    logger.info("hola");
 /*   FileSystem fs = vertx.fileSystem();

    Future<FileProps> future = fs.props("testcsv.csv");

    future.onComplete((AsyncResult<FileProps> ar) -> {
      if(ar.succeeded()){
        FileProps props= ar.result();
        logger.info("file -> "+future);
      }
    });*/
    vertx.fileSystem()
      .readFile(
        "testcsv.csv",
        ar -> {
          if (ar.succeeded()) {
            Buffer result = ar.result();
            String[] Srmvc = result.toString().split(",",result.length());
/*
            for (String o: Srmvc) {
              System.out.println("file -> "+o);

            }*/
            JsonObject config = new JsonObject()
              .put("connection_string", "mongodb://localhost:27018")
              .put("db_name", "testcsv");


            MongoClient client = MongoClient.create(vertx,config);
            JsonObject document = new JsonObject()
              .put("title", "The Hobbit");
            client.save("books", document, res -> {
              if (res.succeeded()) {
                String id = res.result();
                System.out.println("Saved book with id " + id);
              } else {
                res.cause().printStackTrace();
                logger.info("danger ");
              }
            });

          } else {

          }
        });
  }
}
