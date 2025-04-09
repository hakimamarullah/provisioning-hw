package com.voxloud.provisioning.utils;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    private MapUtils() {

    }

    public static String writeAsPropertiesString(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sb.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("\n");
        }

        return sb.toString();
    }

    public static Map<String, Object> fromPropertyString(String property) {
        if (StringUtils.isEmpty(property)) {
            return new HashMap<>();
        }
        Map<String, Object> result = new HashMap<>();
        String[] lines = property.split("\n");

        for (String line : lines) {
            String[] keyVal = line.split("=");
            result.put(keyVal[0], keyVal[1]);
        }
        return result;

    }
}
