package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

public class XmlOutputLayout extends VerticalLayout {

    private static final String LABEL_OUTPUT_TEXT = "Результат конвертации";
    private final TextArea textArea;

    public XmlOutputLayout() {
        textArea = new TextArea();
        textArea.setLabel(LABEL_OUTPUT_TEXT);
        textArea.setHeight("100%");
        textArea.setWidth("100%");
        textArea.setReadOnly(true);
        add(textArea);
    }

    public void setText(String text) {
        textArea.setValue(text);
    }
}
