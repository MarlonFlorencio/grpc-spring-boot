package com.github.marlonflorencio.grpcserver.integration.repository;

import com.github.marlonflorencio.grpcserver.domain.model.Item;
import com.github.marlonflorencio.grpcserver.grpc.builder.ItemBuilder;
import com.github.marlonflorencio.grpcserver.proto.ItemDTO;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static com.github.marlonflorencio.grpcserver.grpc.builder.ItemBuilder.toModel;
import static com.github.marlonflorencio.grpcserver.integration.config.RedisTemplateBuilder.getProtoRedisTemplate;

@Component
public class ItemRepository {

    private final ValueOperations<String, byte[]> operations;

    public ItemRepository(LettuceConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, byte[]> template = getProtoRedisTemplate(redisConnectionFactory);
        this.operations = template.opsForValue();
    }

    public void save(Item item) {

        ItemDTO itemDTO = ItemBuilder.toDTO(item);

        operations.set(itemDTO.getId(), itemDTO.toByteArray());
    }

    public Optional<Item> get(String id) {

        try {
            byte[] result = operations.get(id);

            System.out.println(result.length);
            System.out.println(Arrays.toString(result));

            if (result != null) {
                ItemDTO itemDTO = ItemDTO.parseFrom(result);
                return Optional.of(toModel(itemDTO));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
