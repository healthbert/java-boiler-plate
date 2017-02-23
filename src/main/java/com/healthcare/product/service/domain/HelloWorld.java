package com.healthcare.product.service.domain;

/**
 * Created by wichon on 2/15/17.
 */
public class HelloWorld {
    private String hello;
    private String world;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public HelloWorld(String hello, String world) {
        this.hello = hello;
        this.world = world;
    }
}
