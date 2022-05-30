package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class JsonInputLayout extends VerticalLayout {
    private static final String LABEL_INPUT = "Введите текст для конвертации";

    private final TextArea textArea;
    public JsonInputLayout() {
        textArea = new TextArea();
        textArea.setHeight("100%");
        textArea.setWidth("100%");
        textArea.setLabel(LABEL_INPUT);
        add(textArea);

    }

    public String getText() {
        return textArea.getValue();
    }

    public void setText(String text) {
        textArea.setValue(text);
    }
}
