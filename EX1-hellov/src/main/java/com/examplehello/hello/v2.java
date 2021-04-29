package com.examplehello.hello;



import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;


public class v2  extends AbstractVerticle {



  


    public void start(Promise<Void> startPromise) throws Exception {

final EventBus bus = vertx.eventBus();



bus.consumer("secondhey", msg -> {
  System.out.println("v2 -->  from v3: " + msg.body());
});


bus.consumer("firsthey", message -> {
  System.out.println("v2 -->  from v1: " + message.body());
   // Preparing a reply
  message.reply("MerciV1");
});




startPromise.complete();
    }
}