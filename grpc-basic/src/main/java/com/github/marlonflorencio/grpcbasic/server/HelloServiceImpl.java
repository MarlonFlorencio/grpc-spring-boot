package com.github.marlonflorencio.grpcbasic.server;

import com.github.marlonflorencio.grpcserver.proto.HelloRequest;
import com.github.marlonflorencio.grpcserver.proto.HelloResponse;
import com.github.marlonflorencio.grpcserver.proto.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        final String text = "Hello ".concat(request.getName());
        final HelloResponse response = HelloResponse.newBuilder().setResult(text).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
