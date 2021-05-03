package com.tryop.EX_2_mathapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class v1 extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    System.out.println("v1 deployed->  ");


    vertx.eventBus().publish("firstop",7+15);

    startPromise.complete();
  }
}
