package com.github.marlonflorencio.grpcclient.grpc.client;

import com.github.marlonflorencio.grpcclient.model.Item;
import com.github.marlonflorencio.grpcserver.proto.ItemDTO;
import com.github.marlonflorencio.grpcserver.proto.ItemId;
import com.github.marlonflorencio.grpcserver.proto.ItemServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.github.marlonflorencio.grpcclient.grpc.builder.ItemBuilder.toDTO;
import static com.github.marlonflorencio.grpcclient.grpc.builder.ItemBuilder.toModel;

@Service
public class ItemGrpcClient {

    private static final Logger logger = Logger.getLogger(ItemGrpcClient.class.getName());

    @GrpcClient("item-service")
    private ItemServiceGrpc.ItemServiceBlockingStub itemStub;

    public Item getItem(String id) {

        ItemId request = ItemId.newBuilder().setItemId(id).build();

        try {

            ItemDTO response = this.itemStub.getItem(request);
            return toModel(response);

        } catch (StatusRuntimeException e) {

            if (e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
                logger.log(Level.SEVERE, "Deadline exceeded!", e);

            } else if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                logger.log(Level.SEVERE, "Not found!", e);

            } else {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }

        //only for demo
        return null;
    }

    public Item createItem(Item item) {

        ItemDTO request = toDTO(item);

        ItemDTO respose = this.itemStub.createItem(request);

        return toModel(respose);

    }

    public List<Item> getItems(String id) {

        ItemId request = ItemId.newBuilder().setItemId(id).build();

        List<Item> items = new ArrayList<>();

        // we stream the responses (in a blocking manner)
        this.itemStub.getItems(request).forEachRemaining(response -> items.add(toModel(response)));

        return items;

    }

}
