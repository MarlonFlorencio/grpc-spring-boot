package com.github.marlonflorencio.grpcclient.grpc.config;

import io.grpc.ClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalInterceptorConfiguration {

    @GrpcGlobalClientInterceptor
    ClientInterceptor deadLineClientInterceptor() {
        return new DeadlineInterceptor();
    }

}
