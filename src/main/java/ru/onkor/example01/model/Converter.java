package ru.onkor.example01.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Интерфейс конвертации
 */
public interface Converter {
    /**
     * Конвертация
     *
     * @param input - Строка с исходным текстом
     * @return - сконвертированная строка
     */
    default String convert(String input) {
        if (StringUtils.isNotEmpty(input)) {
            String[] part = input.split(" ");
            return StringUtils.join(part, "_");
        }
        return StringUtils.EMPTY;
    }
}
