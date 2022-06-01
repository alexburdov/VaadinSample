package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

/**
 * Панель источника
 */
public class SourceInputLayout extends VerticalLayout {
    private static final String LABEL_INPUT = "Введите текст для конвертации";

    /**
     * Панель
     */
    private final TextArea textArea;

    public SourceInputLayout() {
        textArea = new TextArea();
        textArea.setHeight("100%");
        textArea.setWidth("100%");
        textArea.setLabel(LABEL_INPUT);
        add(textArea);
    }

    /**
     * Получить текст в панели
     *
     * @return - тест в панели
     */
    public String getText() {
        return textArea.getValue();
    }

    /**
     * Установить текст в панели
     *
     * @param text - текст в панели
     */
    public void setText(String text) {
        textArea.setValue(text);
    }
}
