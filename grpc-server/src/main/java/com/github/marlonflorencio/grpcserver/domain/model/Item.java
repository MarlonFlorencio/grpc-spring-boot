package com.github.marlonflorencio.grpcserver.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder(toBuilder=true)
public class Item {

    private String id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private List<String> categories;
    private OffsetDateTime createdAt;

}
