package ru.onkor.example01.Exceptions;

/**
 * Ошибка конвертации
 */
public class ConverterException extends RuntimeException {
    public ConverterException(String message, Throwable cause) {
        super(message, cause);
    }
}
