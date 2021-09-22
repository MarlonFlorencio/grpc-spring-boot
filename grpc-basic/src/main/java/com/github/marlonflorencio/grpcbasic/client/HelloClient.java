package com.github.marlonflorencio.grpcbasic.client;


import com.github.marlonflorencio.grpcserver.proto.HelloRequest;
import com.github.marlonflorencio.grpcserver.proto.HelloResponse;
import com.github.marlonflorencio.grpcserver.proto.HelloServiceGrpc;
import com.github.marlonflorencio.grpcserver.proto.HelloServiceGrpc.HelloServiceBlockingStub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloClient {

    public static void main(String[] args) {
        System.out.println("Hello I'm a gRPC client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        HelloServiceBlockingStub client = HelloServiceGrpc.newBlockingStub(channel);

        sayHello(client);

        System.out.println("Shutting down channel");
        channel.shutdown();
    }

    private static void sayHello(HelloServiceBlockingStub client) {

        HelloRequest requestPayload = HelloRequest.newBuilder()
                .setName("John")
                .build();

        HelloResponse response = client.sayHello(requestPayload);

        System.out.println("*********************");
        System.out.println(response.getResult());
        System.out.println("*********************");

    }

}
