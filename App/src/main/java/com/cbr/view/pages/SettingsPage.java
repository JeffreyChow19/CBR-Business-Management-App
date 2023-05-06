package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.datastore.DataStore;
import com.cbr.plugin.Plugin;
import com.cbr.plugin.PluginManager;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;
import com.cbr.view.MainView;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


public class SettingsPage extends VBox {
    private Label selectedFolderLabel;
    private String selectedFolder;
    @Getter
    private Set<SettingsUpdate> onSaves;
    private ToggleGroup dataFormatToggle;
    @Getter
    private List<String> errorList;
    @Getter @Setter
    private Map<String, String> additionalValues;
    @Getter
    private HBox formContainer;
    public SettingsPage(){
        this.onSaves = new HashSet<SettingsUpdate>(){};
        this.additionalValues = new HashMap<>(AppSettings.getInstance().getAdditionalSettings());
        this.errorList = new ArrayList<>();
        this.selectedFolder = App.getDataStore().getFolder();
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(50);
        this.setPadding(new Insets( 50, Theme.getScreenWidth()*0.12, Theme.getScreenWidth()*0.12, Theme.getScreenWidth()*0.12));

        PageTitle pageTitle = new PageTitle("Settings");

        Label dataFormatLabel = new Label("Data format");
        dataFormatLabel.setFont(Theme.getHeading2Font());
        dataFormatLabel.setTextFill(Color.WHITE);
        formContainer = new HBox();
        formContainer.setSpacing(60);

        VBox leftFormContainer = new VBox();
        leftFormContainer.setSpacing(30);
        leftFormContainer.setAlignment(Pos.TOP_LEFT);

        dataFormatToggle = new ToggleGroup();
        RadioButton jsonButton = new RadioButton("JSON");
        RadioButton xmlButton = new RadioButton("XML");
        RadioButton objButton = new RadioButton("OBJ");
        dataFormatToggle.getToggles().addAll(jsonButton, xmlButton, objButton);

        VBox dataFormatContainer = new VBox();
        dataFormatContainer.setSpacing(15);

        for (Toggle tb : dataFormatToggle.getToggles()){
            RadioButton temp = (RadioButton) tb;
            temp.setFont(Theme.getBodyMediumFont());
            temp.setTextFill(Color.WHITE);
            dataFormatContainer.getChildren().add(temp);
        }
        switch (App.getDataStore().getMode()){
            case "JSON":
                jsonButton.setSelected(true);
                break;
            case "XML":
                xmlButton.setSelected(true);
            case "OBJ":
                objButton.setSelected(true);
        }
        Label pathLabel = new Label("Path");
        pathLabel.setFont(Theme.getHeading2Font());
        pathLabel.setTextFill(Color.WHITE);

        VBox directoryContainer = new VBox();

        DefaultButton chooseFolderButton = new DefaultButton (Theme.getScreenWidth()*0.2, Theme.getScreenHeight() * 0.06, "Choose Folder");
        chooseFolderButton.setOnMouseClicked(event -> {
            // Create a FolderChooser
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder");

            // Show the folder picker dialog
            java.io.File selectedFolder = directoryChooser.showDialog(new Stage());

            // Handle the selected folder (null if no folder was selected)
            if (selectedFolder != null) {
                this.selectedFolder = selectedFolder.getAbsolutePath();
                errorList.remove("Data store folder path must be chosen");
            } else {
                this.selectedFolder = "No folder chosen";
                errorList.add("Data store folder path must be chosen");
            }
            selectedFolderLabel.setText(this.selectedFolder);
        });

        selectedFolderLabel = new Label(this.selectedFolder);
        selectedFolderLabel.setFont(Theme.getBodyFont());
        selectedFolderLabel.setTextFill(Color.WHITE);

        directoryContainer.setSpacing(20);
        directoryContainer.setAlignment(Pos.CENTER_LEFT);
        directoryContainer.getChildren().addAll(chooseFolderButton, selectedFolderLabel);

        leftFormContainer.getChildren().addAll(dataFormatLabel, dataFormatContainer, pathLabel, directoryContainer);
        Region formRegion = new Region();

        formContainer.getChildren().addAll(leftFormContainer);

        DefaultButton saveSettingsButton = new DefaultButton(Theme.getScreenWidth()*0.2, Theme.getScreenHeight() * 0.06, "Save Settings");

        saveSettingsButton.setOnMouseClicked(event -> {
            // Create a FolderChooser
            if (errorList.isEmpty()) {
                AppSettings.getInstance().setDataStoreMode(((RadioButton)dataFormatToggle.getSelectedToggle()).getText());
                AppSettings.getInstance().setDataStorePath(selectedFolder);
                AppSettings.getInstance().setAdditionalSettings(additionalValues);
                App.setDataStore(new DataStore(((RadioButton)dataFormatToggle.getSelectedToggle()).getText(), selectedFolder));
                MainView.getInstance().refresh();
//                for (SettingsUpdate u : onSaves){
//                    u.onSave();
//                }
                AppSettings.getInstance().updateSettings();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Successfully saved settings!");
                alert.setOnCloseRequest(e -> {
                    alert.close();
                });
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(String.join("\n", this.errorList));
                alert.setOnCloseRequest(e -> {
                    alert.close();
                });
                alert.show();
            }
        });
        Region region = new Region();
        VBox.setVgrow(region, Priority.ALWAYS);
        this.getChildren().addAll(pageTitle, formContainer, region, saveSettingsButton);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
    }

}
