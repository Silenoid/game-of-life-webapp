package com.sileno.gol.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;

@Slf4j
public class GolUtils {

    public static byte[] serialize(boolean[][] booleanMatrix) {
        byte[] serializedData = SerializationUtils.serialize(booleanMatrix);
        if(serializedData == null)
            log.warn("Serialized data is null");
        else
            printByteArray(serializedData);
        return serializedData;
    }

    public static boolean[][] deserialize(byte[] byteData) {
        boolean[][] deserializedData = (boolean[][]) SerializationUtils.deserialize(byteData);
        if(deserializedData == null)
            log.warn("Deserialized data is null");
        else
            printBooleanMatrix(deserializedData);
        return deserializedData;

    }

    public static void printByteArray(byte[] bytes) {
        StringBuilder strBuilder = new StringBuilder("Byte array:\n");
        for (byte b : bytes) {
            strBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'))
                    .append(" | ").append(Integer.toHexString(b))
                    .append(" | ").append(b).append("\n");
        }
        log.trace(strBuilder.toString());
    }

    public static void printBooleanMatrix(boolean[][] booleanMatrix) {
        StringBuilder strBuilder = new StringBuilder("Boolean matrix:\n");
        for (boolean[] booleanArray : booleanMatrix) {
            for (boolean aBoolean : booleanArray) {
                strBuilder.append(aBoolean ? 1 : 0);
            }
            strBuilder.append("\n");
        }
        log.trace(strBuilder.toString());
    }

    public static int limitRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

}
