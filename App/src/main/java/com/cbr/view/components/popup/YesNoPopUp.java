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
import javafx.scene.text.TextAlignment;
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

        this.setMinSize(Theme.getScreenWidth() * 0.4, Theme.getScreenHeight() * 0.2);

        Label label = new Label(question);
        label.setMaxWidth(Theme.getScreenWidth() * 0.28);
        label.setMinWidth(Theme.getScreenWidth() * 0.28);
        label.setWrapText(true);
        label.setFont(Theme.getBodyMediumFont());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-font-size: 25; -fx-text-fill: white");

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(30);
        buttonContainer.setPadding(new Insets(10, 30, 0, 30));

        yesButton = new DefaultButton(200.0, 40.0, "Yes");
        noButton = new DefaultButton(200.0, 40.0, "No");
        noButton.setOnAction(e -> {
            closeStage();
        });

        buttonContainer.getChildren().addAll(yesButton, noButton);

        this.getChildren().addAll(label, buttonContainer);
        this.setPadding(new Insets(20, 30, 20, 30));
        this.setStyle("-fx-background-color: " + Theme.getSecondaryBase());
    }

    public void showStage() {
        scene = new Scene(this);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }

    public void closeStage() {
        stage.close();
    }
}
