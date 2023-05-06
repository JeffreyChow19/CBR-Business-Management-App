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

public class YesNoPopUp extends VBox {
    @Getter private Button yesButton;
    @Getter private Button noButton;
    private Stage stage;
    private Scene scene;
    @Setter @Getter
    private Boolean value;
    public YesNoPopUp(String question) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);

        Label label = new Label(question);
        label.setFont(Theme.getBodyMediumFont());
        label.setStyle("-fx-font-size: 25;");

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(30);
        buttonContainer.setPadding(new Insets(50, 30, 30, 30));

        yesButton = new DefaultButton(200.0, 40.0, "Yes");
        noButton = new DefaultButton(200.0, 40.0, "No");
        noButton.setOnAction(e -> {
            closeStage();
        });

        buttonContainer.getChildren().addAll(yesButton, noButton);

        this.getChildren().addAll(label, buttonContainer);
    }

    public void showStage() {
        scene = new Scene(this);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Custom Pop-up Window");
        stage.showAndWait();
    }

    public void closeStage() {
        stage.close();
    }
}
