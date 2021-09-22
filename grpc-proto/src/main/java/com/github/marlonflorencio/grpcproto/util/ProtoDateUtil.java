package com.github.marlonflorencio.grpcproto.util;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.TimeZone;

public class ProtoDateUtil {

    public static OffsetDateTime toOffsetDateTime(Timestamp timestamp) {

        if (timestamp == null) {
            return null;
        }

        Instant instant = Instant.ofEpochSecond(
                timestamp.getSeconds(),
                timestamp.getNanos()
        );

        return OffsetDateTime.ofInstant(
                instant,
                TimeZone.getDefault().toZoneId()
        );
    }

    public static Timestamp toTimestamp(OffsetDateTime offsetDateTime) {

        Instant instant = offsetDateTime != null
                ? offsetDateTime.toInstant()
                : Instant.ofEpochSecond(0,0);

        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }

}
