package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridSortOrder;
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

    /**
     * Layout управления
     */
    private ControlLayout controlLayout;

    public HistoryLayout(SourceInputLayout inputLayout, TargetOutputLayout outputLayout) {
        super(ConvertItemDto.class, false);

        addColumn(ConvertItemDto::getSource).setHeader("Исходный");
        addColumn(ConvertItemDto::getConverted).setHeader("Результат");
        Column<ConvertItemDto> dateColumn
                = addColumn(item -> item.getDt().format(DATE_TIME_FORMATTER))
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

        sort(GridSortOrder.desc(dateColumn).build());
        setItems(items);

        addSelectionListener(selection -> {
            Optional<ConvertItemDto> convertItem = selection.getFirstSelectedItem();
            if (convertItem.isPresent()) {
                ConvertItemDto dto = convertItem.get();
                inputLayout.setText(dto.getSource());
                outputLayout.setText(dto.getConverted());
                if (controlLayout != null) {
                    controlLayout.setConvertTypes(dto.getConvertType());
                }
            } else {
                inputLayout.setText(StringUtils.EMPTY);
                outputLayout.setText(StringUtils.EMPTY);
                if (controlLayout != null) {
                    controlLayout.setConvertTypes(ControlLayout.DEFAULT_CONVERT_TYPE);
                }
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
        select(item);
    }

    /**
     * Установить контроллер управления
     *
     * @param controlLayout - контроллер управление
     */
    public void setControlLayout(ControlLayout controlLayout) {
        this.controlLayout = controlLayout;
    }
}
