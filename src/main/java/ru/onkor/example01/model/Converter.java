package ru.onkor.example01.model;

import org.apache.commons.lang3.StringUtils;

public interface Converter {
    default String convert(String input) {
        if (StringUtils.isNotEmpty(input)) {
            String[] part = input.split(" ");
            return StringUtils.join(part, "_");
        }
        return StringUtils.EMPTY;
    }
}
