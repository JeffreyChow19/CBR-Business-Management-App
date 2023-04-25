package com.cbr;

import com.cbr.view.MainView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class App extends Application{
    public static void main (String[] args) {
        System.out.println("Starting App...");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Business Management Application");
        Image appIcon = new Image("/icons/capybucks.jpg");
        // chore: update icons
        stage.getIcons().add(appIcon);

        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, 1280, 720);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

