package com.github.marlonflorencio.grpcclient.grpc.builder;

import com.github.marlonflorencio.grpcclient.model.Item;
import com.github.marlonflorencio.grpcserver.proto.ItemDTO;

import java.util.ArrayList;

import static com.github.marlonflorencio.grpcproto.util.ProtoDateUtil.toOffsetDateTime;
import static com.github.marlonflorencio.grpcproto.util.ProtoDateUtil.toTimestamp;
import static com.github.marlonflorencio.grpcproto.util.ProtoDecimalUtil.toBigDecimal;
import static com.github.marlonflorencio.grpcproto.util.ProtoDecimalUtil.toDecimalValue;

public class ItemBuilder {

    public static Item toModel(ItemDTO dto) {
        return Item.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(toBigDecimal(dto.getPrice()))
                .quantity(dto.getQuantity())
                .categories(new ArrayList<>(dto.getCategoriesList()))
                .createdAt(toOffsetDateTime(dto.getCreatedAt()))
                .build();
    }

    public static ItemDTO toDTO(Item item) {
        return ItemDTO.newBuilder()
                .setId(item.getId())
                .setTitle(item.getTitle())
                .setDescription(item.getDescription())
                .setPrice(toDecimalValue(item.getPrice()))
                .setQuantity(item.getQuantity())
                .addAllCategories(item.getCategories())
                .setCreatedAt(toTimestamp(item.getCreatedAt()))
                .build();
    }

}
