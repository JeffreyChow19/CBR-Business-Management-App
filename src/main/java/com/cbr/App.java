package com.cbr;

import com.cbr.datastore.DataStore;
import com.cbr.view.MainView;
import com.cbr.view.theme.Theme;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import lombok.Getter;
import lombok.Setter;


public class App extends Application{
    @Getter
    @Setter
    private static DataStore dataStore = new DataStore("JSON", "assets/data/json");
    public static void main (String[] args) {
        System.out.println("Starting App...");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Business Management Application");
//        Image appIcon = new Image("/assets/icons/capybucks.jpg");
        // chore: update icons
//        stage.getIcons().add(appIcon);

        MainView mainView = new MainView();
        Scene scene = new Scene(mainView, Theme.getScreenWidth(), Theme.getScreenHeight());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }
}

