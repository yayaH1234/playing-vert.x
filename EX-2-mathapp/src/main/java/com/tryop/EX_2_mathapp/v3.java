package com.tryop.EX_2_mathapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class v3 extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {


    System.out.println("v3 deployed->  ");

    vertx.eventBus().consumer("thirdop",h ->{


      System.out.println("v3 -> getting "+h.body());
      int resdd=Integer.parseInt(h.body().toString());
      resdd-=4;
      System.out.println("result -> "+resdd);
    });
    startPromise.complete();
  }
}
