package ru.onkor.example01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO истории конвертации
 */
@AllArgsConstructor
public class ConvertItemDto {

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

    /**
     * Статус конвертации (Удачно - truе, Не успешно - false)
     */
    @Getter
    @Setter
    private boolean isSuccess;

    /**
     * Тип конвертации
     */
    @Getter
    @Setter
    private String convertType;
}
