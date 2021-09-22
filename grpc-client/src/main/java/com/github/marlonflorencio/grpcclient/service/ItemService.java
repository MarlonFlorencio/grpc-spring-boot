package com.github.marlonflorencio.grpcclient.service;

import com.github.marlonflorencio.grpcclient.model.Item;
import com.github.marlonflorencio.grpcclient.grpc.client.ItemGrpcClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemGrpcClient itemGrpcClient;

    public Item get(String id) {
        return this.itemGrpcClient.getItem(id);
    }

    public List<Item> getItems(String id) {
        return this.itemGrpcClient.getItems(id);
    }

    public Item create(Item item) {

        Item newItem = item.toBuilder()
                .id(UUID.randomUUID().toString())
                .build();

        return this.itemGrpcClient.createItem(newItem);
    }

}
