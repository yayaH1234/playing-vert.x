package com.example.EX_3_testsendRfwebpage;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;


public class MainVerticle extends AbstractVerticle {

  final Logger log= LoggerFactory.getLogger(MainVerticle.class);

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

    router.get("/result").handler(ctx -> { ctx
      .response()
      .putHeader("Content-Type", "text/html")
      .sendFile("result.html");
        String myvl=ctx.pathParam("keyname");
        log.info("okok rs--> "+myvl);

      }
    );

    router
      .post("/myreq")
      .handler(ctx -> {
       // ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "text/plain");
     //   ctx.request().setExpectMultipart(true);

       ctx.request().bodyHandler(body -> {


         String[] bdv=body.toString().split("=");
          log.info("---> "+body);
         double i=Double.parseDouble(bdv[1]);
         try {

          log.info("posted --> "+ i);
          ctx.redirect("/result?keyname="+i*20);
    //       ctx.json(new JsonObject().put("res", i * 20));


         }catch(Exception e){
           e.getMessage();
         }
       });

      });
    server.requestHandler(router).listen(8880);
  }
}
