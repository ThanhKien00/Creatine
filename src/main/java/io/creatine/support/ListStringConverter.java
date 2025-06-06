package io.creatine.support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Converter
@RequiredArgsConstructor
public class ListStringConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper;
    private final TypeReference<List<String>> typeRef = new TypeReference<>() {};

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception ex) {
            log.error("Could not convert data-map to JSON String.", ex);
            return "";
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(dbData, typeRef);
        } catch (IOException ex) {
            log.error("Couldn't convert JSON String to data-map.", ex);
            return Collections.emptyList();
        }
    }
}
