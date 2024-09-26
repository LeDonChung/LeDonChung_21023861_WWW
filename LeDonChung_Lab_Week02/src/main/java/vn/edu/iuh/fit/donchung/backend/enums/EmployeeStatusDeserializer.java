package vn.edu.iuh.fit.donchung.backend.enums;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.stream.Stream;

public class EmployeeStatusDeserializer extends JsonDeserializer<EmployeeStatus> {
    @Override
    public EmployeeStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue();
        return Stream.of(EmployeeStatus.values())
                .filter(c -> c.getValue() == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
