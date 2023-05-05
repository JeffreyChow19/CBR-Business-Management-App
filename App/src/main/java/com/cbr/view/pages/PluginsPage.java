package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.exception.PluginException;
import com.cbr.plugin.Plugin;
import com.cbr.plugin.PluginManager;
import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;


public class PluginsPage extends VBox {
    private HBox pluginsList;

    public PluginsPage(){
        PageTitle pageTitle = new PageTitle("Plugins");
        pluginsList = new HBox();
        pluginsList.setSpacing(10.0);
        pluginsList.setAlignment(Pos.CENTER);
        DefaultButton addPluginButton = new DefaultButton(Theme.getScreenWidth()*0.2, Theme.getScreenHeight() * 0.06,"+ Plugin");
        // add an event handler to the button
        addPluginButton.setOnAction(event -> {
            // create a new file chooser
            FileChooser fileChooser = new FileChooser();

            // set the file extension filter to only show JAR files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JAR files (*.jar)", "*.jar");
            fileChooser.getExtensionFilters().add(extFilter);

            // show the file chooser dialog
            File selectedFile = fileChooser.showOpenDialog(addPluginButton.getScene().getWindow());

            // if a file was selected, do something with it
            if (selectedFile != null) {
                // handle the selected file (e.g. load the plugin)
                AppSettings.getInstance().addPlugin(selectedFile.getAbsolutePath());
                try {
                    PluginManager.getInstance().loadNewPlugin();
                } catch (MalformedURLException | PluginException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText(e.getMessage());
                    alert.setOnCloseRequest(alertEvent -> {
                        alert.close();
                    });
                    alert.show();
                    AppSettings.getInstance().getPlugins().remove(selectedFile.getAbsolutePath());
                }
                this.setPluginList();
//                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        });
        this.setPluginList();
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(50);
        this.setPadding(new Insets( 50, Theme.getScreenWidth()*0.12, Theme.getScreenWidth()*0.12, Theme.getScreenWidth()*0.12));
        this.setStyle("-fx-background-color:" +Theme.getPrimaryDark());
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().addAll(pageTitle, pluginsList, addPluginButton);
    }

    public void setPluginList(){
        pluginsList.getChildren().clear();
        for (String p : AppSettings.getInstance().getPlugins()){
            VBox nameContainer = new VBox();
            nameContainer.setStyle("-fx-background-color:" +Theme.getPrimaryLight()+";-fx-background-radius: 12;");
            Label pluginPath = new Label(p);
            pluginPath.setWrapText(true);
            pluginPath.setPrefWidth(Theme.getScreenWidth() * 0.1);
            pluginPath.setFont(Theme.getBodyFont());
            pluginPath.setTextFill(Color.WHITE);
            nameContainer.getChildren().add(pluginPath);
            nameContainer.setAlignment(Pos.CENTER);
            nameContainer.setMinHeight(Theme.getScreenWidth() * 0.15);
            nameContainer.setMaxHeight(Theme.getScreenWidth() * 0.15);
            nameContainer.setMinWidth(Theme.getScreenWidth() * 0.15);
            nameContainer.setMaxWidth(Theme.getScreenWidth() * 0.15);
            pluginsList.getChildren().add(nameContainer);
        }
    }
}
