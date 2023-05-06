package com.cbr.view.components.popup;

import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class OkPopUp extends VBox {
    private Button okButton;
    private Stage stage;
    private Scene scene;
    public OkPopUp(String question) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        Label label = new Label(question);
        label.setFont(Theme.getBodyMediumFont());
        label.setStyle("-fx-font-size: 25;");

        okButton = new DefaultButton(200.0, 40.0, "OK");
        okButton.setOnAction(e -> {
            stage.close();
        });

        this.getChildren().addAll(label, okButton);

        scene = new Scene(this);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Custom Ok Window");
        stage.showAndWait();
    }
}
