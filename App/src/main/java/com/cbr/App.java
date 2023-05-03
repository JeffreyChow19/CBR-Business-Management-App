package com.cbr;

import com.cbr.datastore.DataStore;
import com.cbr.plugin.Plugin;
import com.cbr.plugin.PluginManager;
import com.cbr.view.MainView;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.theme.Theme;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class App extends Application{
    @Getter
    @Setter
    private static DataStore dataStore = new DataStore("JSON", "assets/data/json");
    private List<Plugin> plugins;
    private MainView mainView;

    public static void main (String[] args) {
        System.out.println("Starting App...");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Business Management Application");
//        Image appIcon = new Image("/assets/icons/capybucks.jpg");
//         chore: update icons
//        stage.getIcons().add(appIcon);
        PluginManager pluginManager = new PluginManager();
        pluginManager.loadPlugin("Plugin-Base/target/Plugin-Base-1.0.jar", "com.cbr.BasePlugin.class");
////        for (Plugin p : pluginManager.getPlugins()){
////            p.load(this);
////        }
        this.mainView = MainView.getInstance();
        Scene scene = new Scene(this.mainView, Theme.getScreenWidth(), Theme.getScreenHeight() * 0.98);

        stage.setScene(scene);
        stage.setMinWidth(Theme.getScreenWidth());
        stage.setMinHeight(Theme.getScreenHeight());
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }
}

