package com.fable.logroutingservice;

import com.fable.logroutingservice.handler.PostHandler;
import com.fable.logroutingservice.handler.RootHandler;
import com.fable.logroutingservice.service.LogAuditService;
import com.sun.net.httpserver.HttpServer;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@SpringBootApplication
public class LogRoutingService {

    public static void main(String[] args) throws Exception {

//        ConfigurableApplicationContext applicationContext =
//                SpringApplication.run(LogRoutingService.class, args);
//        ServiceLocatorUtilities.setApplicationContext(applicationContext);

        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/log-routing", new RootHandler());
        server.createContext("/logs/create", new PostHandler(new LogAuditService()));
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}
