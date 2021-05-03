package com.tryop.EX_2_mathapp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class v2 extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
int d=0;
    System.out.println("v2 deployed->  ");
    vertx.eventBus().consumer("firstop",h ->{

     int resdd=Integer.parseInt(h.body().toString());
     resdd*=32;
     System.out.println("v2 -> getting "+resdd);

      vertx.eventBus().publish("thirdop",resdd);
    });


    startPromise.complete();
  }
}
