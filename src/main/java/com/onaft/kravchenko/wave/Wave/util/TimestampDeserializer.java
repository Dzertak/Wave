package com.onaft.kravchenko.wave.Wave.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimestampDeserializer extends JsonDeserializer<Timestamp> {

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // parse to a LocalDateTime
        LocalDateTime dt = LocalDateTime.parse(jsonParser.getText(), fmt);
        // the date/time is in the default timezone
        return Timestamp.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }
}