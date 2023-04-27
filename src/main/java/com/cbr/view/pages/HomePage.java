package com.cbr.view.pages;

import com.cbr.view.components.clockwidget.ClockWidget;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HomePage extends StackPane {
    public HomePage() {
        super();

        VBox firstLayer = new VBox();
        firstLayer.getChildren().add(new ClockWidget());
        Label nim = new Label("13516055 - Nathaniel Evan Gunawan\n" +
                "13521044 - Rachel Gabriela Chen\n" +
                "13521046 - Jeffrey Chow\n" +
                "13521074 - Eugene Yap Jin Quan\n" +
                "13521094 - Angela Livia Arumsari\n" +
                "13521100 - Alexander Jason");
        nim.setWrapText(true);
        firstLayer.getChildren().add(nim);

        this.getChildren().add(firstLayer);
    }
}
