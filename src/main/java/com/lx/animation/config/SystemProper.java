package com.lx.animation.config;

import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SystemProper {
    @Autowired
    @Qualifier("environment")
    Map<String, Object> environment;
    @Autowired
    @Qualifier("systemProperties")
    Map<String, Object> systemProperties;
    @Autowired
    @Qualifier("systemEnvironment")
    Map<String, Object> systemEnvironment;


    public void print() {
//        environment.forEach((a,b)-> System.out.println(a+" values is "+b) );
       // ((Map<String, String>) systemProperties.get("systemProperties")).forEach((a, b) -> System.out.println(a + " = " + b));
//        systemEnvironment.forEach((a, b) ->);
    }
}
