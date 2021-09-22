package com.github.marlonflorencio.grpcproto.util;

import com.github.marlonflorencio.grpcserver.proto.DecimalValue;
import com.google.protobuf.ByteString;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class ProtoDecimalUtil {

    public static DecimalValue toDecimalValue(BigDecimal bigDecimal) {

        if (bigDecimal == null) {
            return null;
        }

        return  DecimalValue.newBuilder()
                .setScale(bigDecimal.scale())
                .setPrecision(bigDecimal.precision())
                .setValue(ByteString.copyFrom(bigDecimal.unscaledValue().toByteArray()))
                .build();
    }

    public static BigDecimal toBigDecimal(DecimalValue decimalValue) {

        if (decimalValue == null) {
            return null;
        }

        final MathContext mc = new MathContext(decimalValue.getPrecision());
        return new BigDecimal(
                new BigInteger(decimalValue.getValue().toByteArray()),
                decimalValue.getScale(),
                mc
        );
    }

}
