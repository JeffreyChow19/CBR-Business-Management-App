package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.buttons.DeleteButton;

import com.cbr.view.components.form.FormArea;
import com.cbr.view.components.form.FormLabel;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.components.spinner.NumberSpinner;
import com.cbr.view.theme.Theme;
import com.cbr.App;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.cbr.models.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProfileEditor extends StackPane {
    private VBox container;
    private Label title;
    @Getter
    private FormArea nameForm;
    @Getter
    private FormArea phoneForm;
    @Getter
    private double formContainerWidth;

    public ProfileEditor(String _title) {
        super();
        title = new Label(_title);
        title.setFont(Theme.getHeading1Font());
        title.setTextFill(Color.WHITE);

        // Setup Container
        container = new VBox();
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the HBox to the width of the
                                                                  // ScrollPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the HBox to the height of the
                                                                    // ScrollPane
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        container.setPadding(new Insets(10));
        // Create Form Container
        VBox formContainer = new VBox();

        formContainerWidth = 0.4 * Theme.getScreenWidth();
        double formContainerHeight = 0.4 * Theme.getScreenHeight();

        formContainer.setAlignment(Pos.CENTER);
        formContainer.setMinSize(formContainerWidth, formContainerHeight);
        formContainer.setPrefSize(formContainerWidth, formContainerHeight);
        formContainer.setMaxSize(formContainerWidth, formContainerHeight);
        formContainer.setPadding(new Insets(200, 0, 0, 0));

        /* Text Area Container */
        // Row ID
        BorderPane idContainer = new BorderPane();
        double idContainerWidth = formContainerWidth;
        double idContainerHeight = 0.2 * formContainerHeight;
        idContainer.setMinSize(idContainerWidth, idContainerHeight);
        idContainer.setPrefSize(idContainerWidth, idContainerHeight);
        idContainer.setMaxSize(idContainerWidth, idContainerHeight);
        Label idLabel = new FormLabel("ID", idContainerWidth, idContainerHeight);
        Label idContent = new FormLabel("00000000", idContainerWidth, idContainerHeight);
        idContainer.setLeft(idLabel);
        idContainer.setRight(idContent);
        idContainer.setPadding(new Insets(10));

        // Row Points
        BorderPane pointsContainer = new BorderPane();
        double pointsContainerWidth = formContainerWidth;
        double pointsContainerHeight = 0.2 * formContainerHeight;
        pointsContainer.setMinSize(pointsContainerWidth, pointsContainerHeight);
        pointsContainer.setPrefSize(pointsContainerWidth, pointsContainerHeight);
        pointsContainer.setMaxSize(pointsContainerWidth, pointsContainerHeight);
        Label pointsLabel = new FormLabel("Points", pointsContainerWidth, pointsContainerHeight);
        Label pointsContent = new FormLabel("1000", pointsContainerWidth, pointsContainerHeight);
        pointsContainer.setLeft(pointsLabel);
        pointsContainer.setRight(pointsContent);
        pointsContainer.setPadding(new Insets(10));

        // Row Name
        nameForm = new FormArea("Name", formContainerWidth, 0.2 * formContainerHeight);
        // Row phone
        phoneForm = new FormArea("Phone Number", formContainerWidth, 0.2 * formContainerHeight);

        // Category Dropdown
        List<String> membershipList = new ArrayList<>();
        membershipList.add("Customer");
        membershipList.add("Member");
        membershipList.add("VIP");
        BorderPane membershipContainer = new BorderPane();
        double membershipContainerWidth = formContainerWidth;
        double membershipContainerHeight = 0.2 * formContainerHeight;

        membershipContainer.setMinSize(membershipContainerWidth, membershipContainerHeight);
        membershipContainer.setPrefSize(membershipContainerWidth, membershipContainerHeight);
        membershipContainer.setMaxSize(membershipContainerWidth, membershipContainerHeight);

        Label membership = new FormLabel("Membership", membershipContainerWidth, membershipContainerHeight);

        Dropdown membershipDropdown = new Dropdown(membershipList);
        membershipDropdown.setPrefWidth(0.5 * membershipContainerWidth);
        membershipDropdown.setPrefHeight(membershipContainerHeight);

        membershipContainer.setLeft(membership);
        membershipContainer.setRight(membershipDropdown);
        membershipContainer.setPadding(new Insets(10));

        // Save button
        BorderPane saveContainer = new BorderPane();
        double saveContainerWidth = formContainerWidth;
        double saveContainerHeight = 0.2 * formContainerHeight;
        saveContainer.setMinSize(saveContainerWidth, saveContainerHeight);
        saveContainer.setPrefSize(saveContainerWidth, saveContainerHeight);
        saveContainer.setMaxSize(saveContainerWidth, saveContainerHeight);

        Button saveItem = new DefaultButton(0.46 * saveContainerWidth, saveContainerHeight, "Save Profile");
        saveContainer.setRight(saveItem);
        saveContainer.setPadding(new Insets(10));

        // Delete Button
        BorderPane deleteContainer = new BorderPane();
        double deleteContainerWidth = formContainerWidth;
        double deleteContainerHeight = 0.2 * formContainerHeight;
        deleteContainer.setMinSize(deleteContainerWidth, deleteContainerHeight);
        deleteContainer.setPrefSize(deleteContainerWidth, deleteContainerHeight);
        deleteContainer.setMaxSize(deleteContainerWidth, deleteContainerHeight);

        Button deleteItem = new DeleteButton(0.46 * deleteContainerWidth, deleteContainerHeight,
                "Deactivate Membership");

        deleteContainer.setRight(deleteItem);
        deleteContainer.setPadding(new Insets(10));

        // add all formContainer components
        formContainer.getChildren().addAll(idContainer, membershipContainer, nameForm, phoneForm, pointsContainer,
                saveContainer, deleteContainer);

        // add all container components
        container.getChildren().addAll(title, formContainer);

        // Set StackPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
    }

}