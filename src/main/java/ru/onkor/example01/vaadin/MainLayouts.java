package ru.onkor.example01.vaadin;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;

/**
 * Основная панель
 */
@Route("")
public class MainLayouts extends AppLayout {

    public MainLayouts() {
        HorizontalLayout convertLayout = new HorizontalLayout();

        SourceInputLayout inputLayout = new SourceInputLayout();
        TargetOutputLayout outputLayout = new TargetOutputLayout();
        HistoryLayout historyLayout = new HistoryLayout(inputLayout, outputLayout);
        ControlLayout controlLayout = new ControlLayout(inputLayout, outputLayout, historyLayout);
        historyLayout.setControlLayout(controlLayout);

        convertLayout.add(inputLayout);
        convertLayout.add(controlLayout);
        convertLayout.add(outputLayout);

        SplitLayout splitLayout = new SplitLayout(convertLayout, historyLayout);
        splitLayout.setHeight("100%");
        splitLayout.setWidth("100%");
        splitLayout.setSplitterPosition(70);
        splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);

        setContent(splitLayout);
    }
}
