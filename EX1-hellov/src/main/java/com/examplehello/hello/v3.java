package com.examplehello.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

public class v3 extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        System.out.println("started v3");
        final EventBus bus = vertx.eventBus();
        JsonObject message=new JsonObject();
        message.put("heykey", "Hey I'm v3");
        bus.publish("secondhey", message);
        startPromise.complete();
    }
    
}
