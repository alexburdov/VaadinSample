package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.grid.Grid;
import org.apache.commons.lang3.StringUtils;
import ru.onkor.example01.dto.ConvertItemDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Панель истории конвертации
 */
public class HistoryLayout extends Grid<ConvertItemDto> {

    /**
     * Форматер для преобразования дат
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Источник данных
     */
    private final List<ConvertItemDto> items = new ArrayList<>();

    public HistoryLayout(SourceInputLayout inputLayout, TargetOutputLayout outputLayout) {
        super(ConvertItemDto.class, false);

        addColumn(ConvertItemDto::getSource).setHeader("Исходный");
        addColumn(ConvertItemDto::getConverted).setHeader("Результат");
        addColumn(item -> item.getDt().format(DATE_TIME_FORMATTER))
                .setHeader("Дата/Время")
                .setSortable(true)
                .setComparator(ConvertItemDto::getDt);
        addColumn(ConvertItemDto::getConvertType).setHeader("Тип конвертации")
                .setSortable(true)
                .setComparator(ConvertItemDto::getConvertType);
        addColumn(it -> it.isSuccess() ? "Успешно" : "Ошибка")
                .setHeader("Успешно?")
                .setSortable(true)
                .setComparator(ConvertItemDto::isSuccess);
        setItems(items);

        addSelectionListener(selection -> {
            Optional<ConvertItemDto> convertItem = selection.getFirstSelectedItem();
            if (convertItem.isPresent()) {
                ConvertItemDto dto = convertItem.get();
                inputLayout.setText(dto.getSource());
                outputLayout.setText(dto.getConverted());
            } else {
                inputLayout.setText(StringUtils.EMPTY);
                outputLayout.setText(StringUtils.EMPTY);
            }
        });

        setClassNameGenerator(item -> {
            if (!item.isSuccess())
                return "error-rating";
            return null;
        });
    }

    /**
     * Добавить элемент с обновлением
     *
     * @param item - элемент
     */
    public void addItem(ConvertItemDto item) {
        items.add(item);
        getDataProvider().refreshAll();
        deselectAll();
    }
}
