package com.mongoweb.EX_6_mongoweb;

import com.mongoweb.EX_6_mongoweb.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

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
    });

    router.post("/postfile1").handler(ctx -> {

      ctx.response().putHeader("Content-Type", "text/plain");

      ctx.response().setChunked(true);
      System.out.println("in post");
      Set<FileUpload> uploads = ctx.fileUploads();
      log.info("large size --> "+uploads.isEmpty());
      for (FileUpload f : ctx.fileUploads()) {
        // do whatever you need to do with the file (it is already saved
        // on the directory you wanted...
        System.out.println("Filename: " + f.fileName());
        System.out.println("Size: " + f.size());
      }
     /// ctx.response().end();
    });


    server.requestHandler(router).listen(8880);
  }
}
