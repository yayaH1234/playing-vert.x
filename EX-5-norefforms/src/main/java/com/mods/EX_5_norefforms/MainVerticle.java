package com.mods.EX_5_norefforms;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
  final static Logger log = LoggerFactory.getLogger(MainVerticle.class);
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    /*vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });*/

    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    log.info("start");
    router.get("/").respond(ctx -> ctx
      .response()
      .putHeader("Content-Type", "text/html")
      .sendFile("form.html"));



    router.post("/postsend").handler(ctx -> {
      System.out.println(ctx.request().getParam("fname"));
      ctx.response().setChunked(true);
      ctx.request().bodyHandler(body -> {

log.info(body.toString());

        String[] bdv0=body.toString().split("\"");



log.info(bdv0[3]);
        double i=Double.parseDouble(bdv0[3]);
        try {

          JsonObject data = new JsonObject().put("hello",""+i*20);
          log.info(" ok");
          ctx.response().putHeader("Content-Type", "application/json")
            .end(data.encode());//.sendFile("form.html");
        //  Future.succeededFuture(new JsonObject().put("hello", ""+i*20));

        }catch(Exception e){
          e.getMessage();
        }
      });

    });

    server.requestHandler(router).listen(8888);
  }
}
