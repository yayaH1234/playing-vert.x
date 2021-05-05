package com.mongoweb.EX_6_mongoweb;

import com.mongoweb.EX_6_mongoweb.util.Runner;
import java.util.Base64;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import javax.swing.text.Document;
import java.util.Set;

public class MainVerticle extends AbstractVerticle {
  final Logger log= LoggerFactory.getLogger(MainVerticle.class);
public static void main(String[] args){
  Runner.runExample(MainVerticle.class);
}
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server=vertx.createHttpServer();
    Router router=Router.router(vertx);

    router.get("/").handler(ctx -> {


      // This handler will be called for every request
      HttpServerResponse response = ctx.response();
      response.putHeader("content-type", "text/html");

      // Write to the response and end it
      response.sendFile("mongoupload.html");

     /* HttpServerResponse response = ctx.response();
      response.putHeader("content-type", "text/html");

      // Write to the response and end it
      response.putHeader("content-type", "text/html").end(
        "<form action=\"/postfile1\" method=\"post\" enctype=\"multipart/form-data\">\n" +
          "    <div>\n" +
          "        <label for=\"name\">Select a file:</label>\n" +
          "        <input type=\"file\" name=\"file\" />\n" +
          "    </div>\n" +
          "    <div class=\"button\">\n" +
          "        <button type=\"submit\">Send</button>\n" +
          "    </div>" +
          "</form>"
      );*/
    });
    router.post("/postfile1").handler(BodyHandler.create().setMergeFormAttributes(true));
    router.post("/postfile1").handler(ctx -> {


      System.out.println("in post");


        Set<FileUpload> uploads =ctx.fileUploads() ;
      FileUpload f =uploads.iterator().next();
        //Buffer uploaded = ;
 //     Message<String> reply = awaitResult(vertx.fileSystem().readFileBlocking(f.uploadedFileName()));
      //log.info(uploaded.getBytes());
        //  log.info(uploads.iterator().next().contentTransferEncoding());
      JsonObject config=new JsonObject().put("connection_string", "mongodb://localhost:27017")
        .put("db_name", "testcsv");
      MongoClient client = MongoClient.createShared(vertx, config);
      Buffer fileUploaded = ctx.vertx().fileSystem().readFileBlocking(f.uploadedFileName());
      JsonObject document = new JsonObject().put("myfile", fileUploaded.getBytes());
      client.save("webfile",document, rs -> {
        if(rs.succeeded()){
          log.info("succes storing");
        }
      });
      ///client.insert()

     /// ctx.response().end();
    });


    server.requestHandler(router).listen(8888);
  }
}
