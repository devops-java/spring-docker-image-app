package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("health")
    public String health() {
        return getErrorCount();
        //return "ok " + new Date();
    }

    @GetMapping("host")
    public String hostName() {
        try {
            return "hostname: "+InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "no-host";
        }
    }

    private static  Integer count = 0;

    // this is only for v: 1.4 liveness check
    private String getErrorCount() {
        if (count >= 5) {
            throw new RuntimeException("error in health check");
        } else {
            count++;
        }
        return "ok";
    }
}
