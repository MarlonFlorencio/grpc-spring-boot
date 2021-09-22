package com.github.marlonflorencio.grpcserver.grpc.service;

import com.github.marlonflorencio.grpcserver.domain.model.Item;
import com.github.marlonflorencio.grpcserver.domain.service.ItemService;
import com.github.marlonflorencio.grpcserver.proto.ItemDTO;
import com.github.marlonflorencio.grpcserver.proto.ItemId;
import com.github.marlonflorencio.grpcserver.proto.ItemServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import static com.github.marlonflorencio.grpcserver.grpc.builder.ItemBuilder.toDTO;
import static com.github.marlonflorencio.grpcserver.grpc.builder.ItemBuilder.toModel;

@RequiredArgsConstructor
@GrpcService
public class ItemGrpcService extends ItemServiceGrpc.ItemServiceImplBase {

    //public static final Context.Key<String> CORRELATION_ID_KEY = Context.key("correlationId");

    private final ItemService itemService;

    @Override
    public void getItem(ItemId request, StreamObserver<ItemDTO> responseObserver) {

        Item item = this.itemService.get(request.getItemId());

        //Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);

        responseObserver.onNext(toDTO(item));
        responseObserver.onCompleted();
    }

    @Override
    public void createItem(ItemDTO request, StreamObserver<ItemDTO> responseObserver) {

        Item item = this.itemService.save(toModel(request));

        responseObserver.onNext(toDTO(item));
        responseObserver.onCompleted();

    }

    @Override
    public void getItems(ItemId request, StreamObserver<ItemDTO> responseObserver) {

        Item item = this.itemService.get(request.getItemId());

        for (int i = 0; i < 5; i++) {
            responseObserver.onNext(toDTO(item));
        }

        responseObserver.onCompleted();

    }
}
