package com.GestionUsers.GestionUsers;

import com.GestionUsers.GestionUsers.util.Runner;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {
  final Logger log= LoggerFactory.getLogger(MainVerticle.class);
  public static void main(String[] args) {
    Runner.runExample(MainVerticle.class);
  }
  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);

    router
      .get("/userlist")
      .respond(
        ctx -> Future.succeededFuture(new JsonObject().put("hello", "world")));

    router.post("/addusers").handler(ctx -> {
      JsonObject body=ctx.getBodyAsJson();
      log.info("Getting ---> "+body);
      log.info("Getting ---> "+ctx.getBody());

      System.out.println(ctx.request().getParam("fname")+"---> fname");
      System.out.println(ctx.request().getParam("lname")+"---> lname");
      System.out.println(ctx.request().getParam("eml")+"---> eml");
      System.out.println(ctx.request().getParam("pwd")+"---> pwd");







    });




    server.requestHandler(router).listen(9090);





  }
}
