package com.examplehello.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle extends AbstractVerticle {
  
  public static void main(String[] args) {

 
System.out.println("Hello World!");


    Vertx vertx = Vertx.vertx();
/*

    vertx.deployVerticle(new v2(), res2 -> {
      if (res2.succeeded()) {
      //  System.out.println("Deployment V2 id is: " + res2.result());

      } else {
        System.out.println("Deployment  V2 failed!");
      }
    });

    vertx.deployVerticle(new v1(), resp1 -> {
      if (resp1.succeeded()) {
        // System.out.println("Deployment V1 id is: " + resp1.result());
      } else {
        System.out.println("Deployment  V1 failed!");
      }
    });*/
   
    

CompositeFuture.all(vertx.deployVerticle(new v2()), vertx.deployVerticle(new v1())).onComplete(h-> {
  if (h.succeeded()) {
  
    vertx.deployVerticle(new v3());
  } });
    // Promise<Void> v1Promise = Promise.promise();
    // Promise<Void> v2Promise = Promise.promise();
    // Promise<Void> v3Promise = Promise.promise();
    // v1Promise.future();
    // vertx.deployVerticle(new v2(), result -> {
    //   if (result.succeeded()) {
    //     v2Promise.complete();
    //   } else {
    //     v2Promise.fail(result.cause());
    //   }
    // });
    // vertx.deployVerticle(new v1(), result -> {
    //   if (result.succeeded()) {
    //     v1Promise.complete();
    //   } else {
    //     v1Promise.fail(result.cause());
    //   }
    // });
    
    
    // CompositeFuture.all(v2Promise.future(), v1Promise.future()).onComplete(result -> {
    //   if (result.succeeded()) {
        
    //     vertx.deployVerticle(new v3(), h -> {
    //       if (h.succeeded()) {
    //         v3Promise.complete();
    //         System.out.println("v3 deployed");
    //       } });
    //   }
    // });
  }
    //  private Future<Void> deployHelper(String name){ final Future<Void> future =
    //  Future.future(h ->{
     
    //  }); vertx.deployVerticle(name, res -> { if(res.failed()){
    //  System.out.println("Failed to deploy verticle " + name);
    //  future.fail(res.cause()); } else { log.info("Deployed verticle " + name);
    //  future.complete(); } });
     
    //  return future; }
    
}
