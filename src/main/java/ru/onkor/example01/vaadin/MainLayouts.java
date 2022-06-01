package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import ru.onkor.example01.Exceptions.ConverterException;
import ru.onkor.example01.dto.ConvertItemDto;
import ru.onkor.example01.model.Converter;
import ru.onkor.example01.model.JsonToXmlConverterImpl;
import ru.onkor.example01.model.SimpleConvertorImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.Arrays.asList;

@Route("")
@StyleSheet("frontend:://styles/vaadin-grid.css")
public class MainLayouts extends AppLayout {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static final String CONVERT_TEXT = "Конвертировать";

    private final List<ConvertItemDto> items = new ArrayList<>();

    private final Grid<ConvertItemDto> grid = new Grid<>(ConvertItemDto.class, false);

    private static final List<String> CONVERT_TYPES;
    private static final Map<String, Converter> CONVERTERS;

    static {
        CONVERTERS = Map.of(
                "Подчеркивание", new SimpleConvertorImpl(),
                "JSON->XML", new JsonToXmlConverterImpl()
        );
        CONVERT_TYPES = new ArrayList<>(CONVERTERS.keySet());
    }

    private Converter currentConverter;

    public MainLayouts() {
        HorizontalLayout convertLayout = new HorizontalLayout();
        JsonInputLayout inputLayout = new JsonInputLayout();
        XmlOutputLayout outputLayout = new XmlOutputLayout();

        Button button = new Button(CONVERT_TEXT, event -> {
            String text = inputLayout.getText();
            String result = StringUtils.EMPTY;
            boolean isSuccess = true;
            try {
                result = currentConverter.convert(text);
            } catch (ConverterException e) {
                isSuccess = false;
            }
            items.add(new ConvertItemDto(text, result, LocalDateTime.now(), isSuccess));
            grid.getDataProvider().refreshAll();
            outputLayout.setText(result);
        });
        button.setWidth("100%");

        ComboBox<String> convertChoose = new ComboBox<>("Тип конвертации");
        convertChoose.setItems(CONVERT_TYPES);
        String defaultConverterType = CONVERT_TYPES.get(0);
        currentConverter = CONVERTERS.get(defaultConverterType);
        convertChoose.setValue(defaultConverterType);
        convertChoose.addValueChangeListener(e -> {
            String val = e.getValue();
            if (CONVERTERS.containsKey(val)) {
                currentConverter = CONVERTERS.get(val);
            }
        });

        convertChoose.setWidth("100%");

        VerticalLayout controlLayout = new VerticalLayout();
        controlLayout.setWidth("10%");
        controlLayout.setHeight("100%");

        controlLayout.add(button, convertChoose);

        controlLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        convertLayout.add(inputLayout);
        convertLayout.add(controlLayout);
        convertLayout.add(outputLayout);

        grid.addColumn(ConvertItemDto::getSource).setHeader("Исходный");
        grid.addColumn(ConvertItemDto::getConverted).setHeader("Результат");
        grid.addColumn(item -> item.getDt().format(formatter))
                .setHeader("Дата/Время")
                .setSortable(true)
                .setComparator(ConvertItemDto::getDt);
        grid.addColumn(it -> it.isSuccess() ? "Успешно" : "Ошибка")
                .setHeader("Успешно?")
                .setSortable(true)
                .setComparator(ConvertItemDto::isSuccess);
        grid.setItems(items);

        grid.addSelectionListener(selection -> {
            Optional<ConvertItemDto> convertItem = selection.getFirstSelectedItem();
            if (convertItem.isPresent()) {
                ConvertItemDto dto = convertItem.get();
                inputLayout.setText(dto.getSource());
                if (dto.isSuccess()) {
                    outputLayout.setText(dto.getConverted());
                }
            } else {
                inputLayout.setText(StringUtils.EMPTY);
                outputLayout.setText(StringUtils.EMPTY);
            }
        });

        grid.setClassNameGenerator(item -> {
            if (!item.isSuccess())
                return "error-rating";
            return null;
        });

        SplitLayout splitLayout = new SplitLayout(convertLayout, grid);
        splitLayout.setHeight("100%");
        splitLayout.setWidth("100%");
        splitLayout.setSplitterPosition(70);
        splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        setContent(splitLayout);
    }
}
