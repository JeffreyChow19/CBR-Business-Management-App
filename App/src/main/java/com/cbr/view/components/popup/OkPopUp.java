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

public class OkPopUp extends VBox {
    private Button okButton;
    private Stage stage;
    private Scene scene;
    @Getter @Setter
    private VBox box = new VBox();

    public OkPopUp(String question) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setMinSize(Theme.getScreenWidth() * 0.4, Theme.getScreenHeight() * 0.2);

        Label label = new Label(question);
        label.setMinWidth(Theme.getScreenWidth() * 0.28);
        label.setWrapText(true);
        label.setFont(Theme.getBodyMediumFont());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setStyle("-fx-font-size: 25; -fx-text-fill: white;");
        label.setAlignment(Pos.CENTER);

        okButton = new DefaultButton(200.0, 40.0, "OK");
        okButton.setOnAction(e -> {
            stage.close();
        });

        this.getChildren().addAll(label, box, okButton);
        this.setPadding(new Insets(20, 30, 20, 30));
        this.setStyle("-fx-background-color: " + Theme.getSecondaryBase());
    }

    public void show(){
        scene = new Scene(this);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
    }
    public void setBox(VBox box) {
        if (this.box != null) {
            int index = this.getChildren().indexOf(this.box);
            this.getChildren().remove(this.box);
            this.box = box;
            this.getChildren().add(index, box);
        } else {
            this.box = box;
            int index = this.getChildren().indexOf(okButton);
            this.getChildren().add(index, box);
        }
    }
}
