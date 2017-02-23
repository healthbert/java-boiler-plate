package com.healthcare.product.service.web.controllers;

import com.healthcare.product.service.domain.HelloWorld;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public HelloWorld index() {
        return new HelloWorld("Hola", "mundo") ;
    }

    @RequestMapping(path = "/exception", method = RequestMethod.GET)
    public String exception() {
        throw new IndexOutOfBoundsException();
    }
}