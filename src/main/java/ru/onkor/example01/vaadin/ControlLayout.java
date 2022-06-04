package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.apache.commons.lang3.StringUtils;
import ru.onkor.example01.Exceptions.ConverterException;
import ru.onkor.example01.dto.ConvertItemDto;
import ru.onkor.example01.model.Converter;
import ru.onkor.example01.model.JsonToXmlConverterImpl;
import ru.onkor.example01.model.SimpleConvertorImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Панель управления
 */
public class ControlLayout extends VerticalLayout {
    private static final String CONVERT_TEXT = "Конвертировать";

    private static final String CB_CONVERT_TITLE = "Тип конвертации";

    private static final String CONVERTER_NOT_FOUND = "Не определен конвертер";

    public static final String DEFAULT_CONVERT_TYPE = "Подчеркивание";

    /**
     * Типы конвертации
     */
    private static final List<String> CONVERT_TYPES;
    /**
     * Набор предустановленных конверторов
     */
    private static final Map<String, Converter> CONVERTERS;

    static {
        CONVERTERS = Map.of(
                DEFAULT_CONVERT_TYPE, new SimpleConvertorImpl(),
                "JSON->XML", new JsonToXmlConverterImpl()
        );
        CONVERT_TYPES = new ArrayList<>(CONVERTERS.keySet());
    }

    private final ComboBox<String> convertChoose = new ComboBox<>(CB_CONVERT_TITLE);

    public ControlLayout(SourceInputLayout inputLayout, TargetOutputLayout outputLayout, HistoryLayout historyLayout) {
        Button button = new Button(CONVERT_TEXT, event -> {
            String text = inputLayout.getText();
            String result;
            boolean isSuccess = true;
            String currentConverter =  convertChoose.getValue();
            if (CONVERTERS.containsKey(currentConverter)) {
                try {
                    result = CONVERTERS.get(currentConverter).convert(text);
                } catch (ConverterException e) {
                    isSuccess = false;
                    result = e.getMessage();
                }
            } else {
                isSuccess = false;
                result = CONVERTER_NOT_FOUND;
            }

            historyLayout.addItem(new ConvertItemDto(text, result, LocalDateTime.now(), isSuccess, currentConverter));
            outputLayout.setText(result);
        });

        button.setWidth("100%");

        convertChoose.setItems(CONVERT_TYPES);
        convertChoose.setValue(DEFAULT_CONVERT_TYPE);

        convertChoose.setWidth("100%");

        setWidth("10%");
        setHeight("100%");

        add(button, convertChoose);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
    }

    public void setConvertTypes(String convertType) {
        convertChoose.setValue(convertType);
    }
}
