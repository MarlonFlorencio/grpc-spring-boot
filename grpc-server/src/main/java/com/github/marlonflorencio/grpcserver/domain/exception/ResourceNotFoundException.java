package com.github.marlonflorencio.grpcserver.domain.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String id) {
        super(String.format("Resource not found. id : %s", id));
    }
}
