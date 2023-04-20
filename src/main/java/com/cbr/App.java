package com.cbr;

import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class App extends Application{
    Label label;
    public static void main (String[] args) {
        System.out.println("hello");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Business Management Application");

        StackPane layout = new StackPane();
        label = new Label("Hello, World!");
        layout.getChildren().add(label);

        Scene scene = new Scene(layout, 300, 250);

        stage.setScene(scene);
        stage.show();
    }
}

