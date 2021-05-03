package com.example.EX_3_testsendRfwebpage;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;


public class Main extends AbstractVerticle {

  public static void main(String[] args){
   /* Vertx vertx=Vertx.vertx();
    vertx.deployVerticle(new MainVerticle());*/
    Runner.runExample(MainVerticle.class);
  }



}
