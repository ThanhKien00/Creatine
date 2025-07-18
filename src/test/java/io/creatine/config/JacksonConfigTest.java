package io.creatine.config;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@JsonTest
@TestPropertySource(properties = {
        "spring.jackson.default-property-inclusion=non_null",
        "spring.jackson.serialization.indent-output=true",
        "spring.jackson.serialization.close-closeable=true",
        "spring.jackson.serialization.fail-on-empty-beans=false",
        "spring.jackson.serialization.flush-after-write-value=true",
        "spring.jackson.serialization.write-dates-as-timestamps=false",
        "spring.jackson.serialization.write-date-keys-as-timestamps=false",
        "spring.jackson.deserialization.fail-on-unknown-properties=false",
        "spring.jackson.deserialization.accept-single-value-as-array=true"
})
public class JacksonConfigTest {

    @Autowired
    private ObjectMapper mapper;
    private String json;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        var sample = new SampleBean(1L, "John", "Doe", "john@example.com",
                LocalDateTime.of(2025, 5, 31, 13, 30, 0));
        json = mapper.writeValueAsString(sample);
    }

    @Test
    void testDateFormat() {
        assertNotNull(json);
        assertTrue(json.contains("2025-05-31 13:30:00"));
        assertFalse(json.contains("1735132200000"));
    }

    @Test
    void testCamelCasePropertyNaming() {
        log.info("JSON Output: {}", json);
        assertNotNull(json);
        assertTrue(json.contains("userId"));
        assertTrue(json.contains("firstName"));
        assertTrue(json.contains("lastName"));
        assertTrue(json.contains("createdDate"));
        assertFalse(json.contains("user_id"));
        assertFalse(json.contains("first_Name"));
        assertFalse(json.contains("lastname"));
    }

    @Test
    void testIndentOutput() {
        assertNotNull(json);
        assertTrue(json.contains("\n"));
        assertTrue(json.contains("  "));
    }

    @Test
    void testFailOnEmptyBeans() {
        assertDoesNotThrow(() -> {
            String emptyJson = mapper.writeValueAsString(new Object());
            assertEquals("{ }", emptyJson.trim());
        });
    }

    @Data
    @RequiredArgsConstructor
    private static class SampleBean {
        private final Long userId;
        private final String firstName;
        private final String lastName;
        private final String email;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime createdDate;
        private String nullField;
        private List<String> tags;
    }

}
