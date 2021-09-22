package com.github.marlonflorencio.grpcserver.integration.config;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public final class RedisTemplateBuilder {

    public static RedisTemplate<String, byte[]> getProtoRedisTemplate(final LettuceConnectionFactory factory) {
        RedisTemplate<String, byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setDefaultSerializer(RedisSerializer.byteArray());
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.byteArray());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.byteArray());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}