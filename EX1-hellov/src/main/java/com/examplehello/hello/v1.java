package com.examplehello.hello;



import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class v1 extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    final EventBus bus = vertx.eventBus();
    
   
        bus.consumer("secondhey", msg -> {
      System.out.println("v1 -->  from v3: " + msg.body());
    });
      // sending first msg
     vertx.eventBus().request("firsthey", "Bonjourv2", h -> {
       if (h.succeeded()) {
         
         System.out.println("v1 --> from v2: " + h.result().body());
       }
     });


    /*  bus.consumer("therdhey", msg -> {
      System.out.println("v1 -->  I have received a message from v2: " + msg.body());
    });*/
       
      
       
       
      
        startPromise.complete();
  }
}
