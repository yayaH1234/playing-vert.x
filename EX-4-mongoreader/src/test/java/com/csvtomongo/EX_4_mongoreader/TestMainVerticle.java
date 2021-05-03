package com.csvtomongo.EX_4_mongoreader;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(VertxExtension.class)
public class TestMainVerticle {
  public static void main( String[] args ) {
    Vertx vertx=Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());
  }
  }
