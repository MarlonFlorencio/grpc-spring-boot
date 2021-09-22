package com.github.marlonflorencio.grpcbasic.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class HelloServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello HelloServer");

        // plaintext server
        Server server = ServerBuilder.forPort(50051)
                .addService(new HelloServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received Shutdown Request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));

        server.awaitTermination();
    }

}
