package ru.onkor.example01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * DTO истории конвертации
 */
@AllArgsConstructor
public class ConvertItemDto {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Исходный текст для конвертации
     */
    @Getter
    @Setter
    private String source;

    /**
     * Результат конвертации
     */
    @Getter
    @Setter
    private String converted;

    /**
     * Дата и время конвертации
     */
    @Getter
    @Setter
    private LocalDateTime dt;

    public String getDateTime() {
        return dt.format(formatter);
    }

    /**
     * Статус конвертации (Удачно - truе, Не успешно - false)
     */
    @Getter
    @Setter
    private boolean isSuccess;
}
