package com.github.marlonflorencio.grpcserver.grpc.handler;

import com.github.marlonflorencio.grpcserver.domain.exception.ResourceNotFoundException;
import io.grpc.Status;
import io.grpc.StatusException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class GrpcExceptionAdvice {

    @GrpcExceptionHandler
    public Status handleInvalidArgument(IllegalArgumentException e) {
        return Status.INVALID_ARGUMENT.withDescription("Your description").withCause(e);
    }

    @GrpcExceptionHandler(ResourceNotFoundException.class)
    public StatusException handleResourceNotFoundException(ResourceNotFoundException e) {
        Status status = Status.NOT_FOUND.withDescription(e.getMessage());

//        Metadata metadata = new Metadata();
//        return status.asException(metadata);

        return status.asException();
    }

}
