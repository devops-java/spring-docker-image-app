package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class JobContoller {

    private static String STATUS = "NOT_STARTED";
    private static Thread thread = null;

    @GetMapping("/job/{status}")
    public String runJob(@PathVariable("status") String status) {
        switch (status) {
            case "RUN":
                executeJob();
                status = "RUNNING";
                return status;
            case "STOP":
                System.out.println("terminating.....");
                System.exit(0);
            default:
                return thread.getState().name();
        }
    }

    private void executeJob() {
        thread = new Thread() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    System.out.println("count value: " + count);
                    count++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }
}
