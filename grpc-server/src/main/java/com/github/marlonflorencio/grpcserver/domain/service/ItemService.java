package com.github.marlonflorencio.grpcserver.domain.service;

import com.github.marlonflorencio.grpcserver.domain.exception.ResourceNotFoundException;
import com.github.marlonflorencio.grpcserver.domain.model.Item;
import com.github.marlonflorencio.grpcserver.integration.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository repository;

    public Item get(String id) {
        return repository.get(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Item save(Item item) {

        Item newItem = item.toBuilder()
                .id(UUID.randomUUID().toString())
                .createdAt(OffsetDateTime.now())
                .build();

        repository.save(newItem);

        return newItem;
    }

}
