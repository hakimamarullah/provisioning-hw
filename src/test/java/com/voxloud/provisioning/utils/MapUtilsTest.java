package com.voxloud.provisioning.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapUtilsTest {

    @Test
    void givenMapWithKeyValuePairs_whenConvertMapToPropertiesStringThenReturnFormattedString() {
        // Given: A map with multiple key-value pairs
        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        data.put("key3", 123);

        String expected = "key1=value1\nkey2=value2\nkey3=123\n";

        // Act
        String result = MapUtils.writeAsPropertiesString(data);

        // Then: The result should match the expected string
        assertEquals(expected, result);
    }

    @Test
    void givenEmptyMapWhenConvertMapToPropertiesStringThenReturnEmptyString() {
        // Given: An empty map
        Map<String, Object> data = new HashMap<>();
        String expected = "";

        // Act
        String result = MapUtils.writeAsPropertiesString(data);

        // Then: The result should be an empty string
        assertEquals(expected, result);
    }

    @Test
    void givenMapWithNullValuesWhenConvertMapToPropertiesStringThenHandleNullValues() {
        // Given: A map with a null value and a non-null value
        Map<String, Object> data = new HashMap<>();
        data.put("key1", null);
        data.put("key2", "value2");

        String expected = "key1=null\nkey2=value2\n";

        // Act
        String result = MapUtils.writeAsPropertiesString(data);

        // Then: The result should correctly handle null values and non-null values
        assertEquals(expected, result);
    }

    @Test
    void givenPropertyStringWithKeyValuesWhenFromPropertyStringThenReturnMap() {
        // Given: A property string with multiple key-value pairs
        String property = "key1=value1\nkey2=value2\nkey3=123";

        Map<String, Object> expected = Map.of(
                "key1", "value1",
                "key2", "value2",
                "key3", "123"
        );

        // When: The fromPropertyString method is called with this string
        Map<String, Object> result = MapUtils.fromPropertyString(property);

        // Then: The result should match the expected map
        assertEquals(expected, result);
    }

    @Test
    void givenEmptyPropertyStringWhenFromPropertyStringThenReturnEmptyMap() {
        // Given: An empty property string
        String property = "";

        Map<String, Object> expected = Map.of();

        // When: The fromPropertyString method is called with the empty string
        Map<String, Object> result = MapUtils.fromPropertyString(property);

        // Then: The result should be an empty map
        assertEquals(expected, result);
    }

    @Test
    void givenNullPropertyStringWhenFromPropertyStringThenReturnEmptyMap() {
        // Given: A null property string
        Map<String, Object> expected = Map.of();

        // When: The fromPropertyString method is called with the null string
        Map<String, Object> result = MapUtils.fromPropertyString(null);

        // Then: The result should be an empty map
        assertEquals(expected, result);
    }

}