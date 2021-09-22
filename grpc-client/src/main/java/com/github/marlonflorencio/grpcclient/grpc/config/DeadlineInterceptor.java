package com.github.marlonflorencio.grpcclient.grpc.config;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeadlineInterceptor implements ClientInterceptor {

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        Deadline deadline = callOptions.getDeadline();
        if(Objects.isNull(deadline)){
            callOptions = callOptions.withDeadline(Deadline.after(2, TimeUnit.SECONDS));
        }
        return channel.newCall(methodDescriptor, callOptions);
    }

}
