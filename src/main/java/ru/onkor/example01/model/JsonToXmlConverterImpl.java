package ru.onkor.example01.model;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import ru.onkor.example01.Exceptions.ConverterException;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Конвертирование JSON в XML
 */

public class JsonToXmlConverterImpl implements Converter {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final ObjectReader reader = jsonMapper.reader();

    public String convert(String input) {
        String result;
        try {
            result = objectToString(jsonMapper.convertValue(toObject(input), Map.class));
        } catch (IOException e) {
            throw new ConverterException("Ошибка преобразования:" + e.getMessage(), e);
        }
        return result;
    }

    private static <T> T toObject(String jsonString) throws JsonMappingException {
        try {
            return reader.forType(Object.class).readValue(jsonString);
        } catch (Exception ex) {
            throw new JsonMappingException(ex.getMessage());
        }
    }

    private static String objectToString(Object hashMap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(hashMap);
        xmlEncoder.close();
        return bos.toString();
    }
}
