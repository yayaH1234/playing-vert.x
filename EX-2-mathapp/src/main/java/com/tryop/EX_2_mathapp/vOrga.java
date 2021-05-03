package com.tryop.EX_2_mathapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Promise;

public class vOrga extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Promise<Void> v1=Promise.promise();
    Promise<Void> v2=Promise.promise();
    Promise<Void> v3=Promise.promise();


    vertx.deployVerticle(new v2(), result -> {
      if (result.succeeded()) {
        System.out.println(" v2 complete");
        v2.complete();
      } else {
        System.out.println(" v2 failed");
        v2.fail(result.cause());
      }});

    vertx.deployVerticle(new v1(), result -> {
      if (result.succeeded()) {

        System.out.println(" v1 complete");
        v1.complete();
      } else {
        System.out.println(" v1 failed");
        v1.fail(result.cause());
      }});
    vertx.deployVerticle(new v3(), h3 -> {
      if (h3.succeeded()) {
        v3.complete();
        System.out.println("v3 deployed");
      } });
    CompositeFuture.all(v2.future(),v1.future(),v3.future());
    startPromise.complete();
  }
}
