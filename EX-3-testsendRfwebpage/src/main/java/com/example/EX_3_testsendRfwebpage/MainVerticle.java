package com.example.EX_3_testsendRfwebpage;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;


public class MainVerticle extends AbstractVerticle {


  public static void main(String[] args) {

  }


  @Override
  public void start(Promise<Void> startPromise) throws Exception {
   /* vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text")
        .end("hey");
    }).listen(8888, http -> {
      if (http.succeeded()) {
       //startPromise.complete();
        System.out.println("HTTP server started on port 8888");



      } else {
        startPromise.fail(http.cause());
      }
    });*/
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
/*
    router.get("/myres").respond(ctx -> ctx
      .response()
      .putHeader("Content-Type", "text/plain")
      .end("hello world!"));
    router.get("/my").respond(ctx -> ctx
      .response()
      .putHeader("Content-Type", "text/plain")
      .end("hello world!"));
    */
   /* router.route().handler(ctx -> {

      // This handler will be called for every request
      HttpServerResponse response = ctx.response();
      response.putHeader("content-type", "text/plain");


    });*/
    router.get("/").respond(ctx -> ctx
      .response()
      .putHeader("Content-Type", "text/html")
      .sendFile("forms.html"));

    router
      .post("/myreq")
      .handler(ctx -> {
        ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/plain");


       ctx.request().bodyHandler(body -> {

         String[] bdv=body.toString().split("=");

         double i=Double.parseDouble(bdv[1]);
         try {



    //       ctx.json(new JsonObject().put("res", i * 20));
           ctx.response().putHeader("res",""+ i * 20)
             .putHeader("Content-Type", "text/html").sendFile("forms.html");

           /*
           * response().putHeader("res",""+i*20).putHeader("content-type", "text/html").sendFile("forms.html",h -> {

           });//
           *
           * */
         }catch(Exception e){
           e.getMessage();
         }
       });

      });
    server.requestHandler(router).listen(8888);
  }
}
