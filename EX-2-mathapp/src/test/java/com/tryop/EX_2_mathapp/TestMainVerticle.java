package com.tryop.EX_2_mathapp;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.logging.Logger;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {



  public static void main(String[] args) {
    Vertx vertx=Vertx.vertx();


   vertx.deployVerticle(new vOrga());
  }

}
