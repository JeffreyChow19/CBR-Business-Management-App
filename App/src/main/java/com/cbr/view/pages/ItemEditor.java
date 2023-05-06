package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.buttons.DeleteButton;

import com.cbr.view.components.form.FormArea;
import com.cbr.view.components.form.FormLabel;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.components.labels.ToolTipLabel;
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

public class ItemEditor extends ScrollPane {
    private VBox container;
    private PageTitle title;
    @Getter
    private FormArea nameForm;
    @Getter
    private FormArea stockForm;
    @Getter
    private FormArea buyPriceForm;
    @Getter
    private FormArea sellPriceForm;
    @Getter
    private double formContainerWidth;

    public ItemEditor(String _title) {
        super();
        title = new PageTitle(_title);

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

        formContainerWidth = 0.4*Theme.getScreenWidth();
        double formContainerHeight =0.4* Theme.getScreenHeight();

        formContainer.setAlignment(Pos.CENTER);
        formContainer.setMinSize(formContainerWidth, formContainerHeight);
        formContainer.setPrefSize(formContainerWidth, formContainerHeight);
        formContainer.setMaxSize(formContainerWidth, formContainerHeight);
        formContainer.setPadding(new Insets(200, 0, 0, 0));
        

        // Text Area Container
        nameForm = new FormArea("Name", formContainerWidth, 0.2 * formContainerHeight);

        stockForm = new FormArea("Stock", formContainerWidth, 0.2 * formContainerHeight);
        
        buyPriceForm = new FormArea("Buy Price", formContainerWidth, 0.2 * formContainerHeight);
        
        sellPriceForm = new FormArea("Sell Price", formContainerWidth, 0.2 * formContainerHeight);
        
        // Upload Image
        // Row Points
        BorderPane imageContainer = new BorderPane();
        double imageContainerWidth = formContainerWidth;
        double imageContainerHeight = 0.2 * formContainerHeight;
        imageContainer.setMinSize(imageContainerWidth, imageContainerHeight);
        imageContainer.setPrefSize(imageContainerWidth, imageContainerHeight);
        imageContainer.setMaxSize(imageContainerWidth, imageContainerHeight);
        Label imageLabel = new FormLabel("Image", imageContainerWidth, imageContainerHeight);

        HBox uploadImageContainer = new HBox();
        double uploadImageContainerWidth = 0.48*imageContainerWidth;
        double uploadImageContainerHeight = 0.2 * imageContainerHeight;
        uploadImageContainer.setMinSize(uploadImageContainerWidth, uploadImageContainerHeight);
        uploadImageContainer.setPrefSize(uploadImageContainerWidth, uploadImageContainerHeight);
        uploadImageContainer.setMaxSize(uploadImageContainerWidth, uploadImageContainerHeight);

        ToolTipLabel imageContent = new ToolTipLabel("No File Selected");
        imageContent.setFont(Theme.getBodyMediumFont());
        imageContent.setTextFill(Color.WHITE);
        // imageContent.setAlignment(Pos.TOP_LEFT);
        imageContent.setPrefWidth(0.5 * uploadImageContainerWidth);
        imageContent.setPrefHeight(uploadImageContainerHeight);

        Button uploadButton = new DefaultButton(0.5 * uploadImageContainerWidth, uploadImageContainerHeight,
                "Upload");
        // uploadImageContainer.setLeft(uploadButton);
        // uploadImageContainer.setRight(imageContent);
        uploadImageContainer.getChildren().addAll(uploadButton, imageContent);
        uploadImageContainer.setSpacing(10);
        uploadImageContainer.setPadding(new Insets(0,0,10,10));

        imageContainer.setLeft(imageLabel);
        imageContainer.setRight(uploadImageContainer);
        imageContainer.setPadding(new Insets(10));

        // Category Dropdown
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Pakaian");
        categoryList.add("Makanan");
        BorderPane categoryContainer = new BorderPane();
        double categoryContainerWidth = formContainerWidth;
        double categoryContainerHeight = 0.2*formContainerHeight;
        
        categoryContainer.setMinSize(categoryContainerWidth, categoryContainerHeight);
        categoryContainer.setPrefSize(categoryContainerWidth, categoryContainerHeight);
        categoryContainer.setMaxSize(categoryContainerWidth, categoryContainerHeight);

        Label category = new FormLabel("Category", categoryContainerWidth,categoryContainerHeight);

        Dropdown categoryDropdown = new Dropdown(categoryList);
        categoryDropdown.setPrefWidth(0.5 * categoryContainerWidth);
        categoryDropdown.setPrefHeight(categoryContainerHeight);

        categoryContainer.setLeft(category);
        categoryContainer.setRight(categoryDropdown);
        categoryContainer.setPadding(new Insets(10));

        // Save button
        BorderPane saveContainer = new BorderPane();
        double saveContainerWidth = formContainerWidth;
        double saveContainerHeight = 0.2*formContainerHeight;
        saveContainer.setMinSize(saveContainerWidth, saveContainerHeight);
        saveContainer.setPrefSize(saveContainerWidth, saveContainerHeight);
        saveContainer.setMaxSize(saveContainerWidth, saveContainerHeight);

        Button saveItem = new DefaultButton(0.46*saveContainerWidth, saveContainerHeight, "Save Item");
        saveContainer.setRight(saveItem);
        saveContainer.setPadding(new Insets(10));

        // Delete Button
        BorderPane deleteContainer = new BorderPane();
        double deleteContainerWidth = formContainerWidth;
        double deleteContainerHeight = 0.2*formContainerHeight;
        deleteContainer.setMinSize(deleteContainerWidth, deleteContainerHeight);
        deleteContainer.setPrefSize(deleteContainerWidth, deleteContainerHeight);
        deleteContainer.setMaxSize(deleteContainerWidth, deleteContainerHeight);

        if (_title == "Edit Item") {
            Button deleteItem = new DeleteButton(0.46 * deleteContainerWidth, deleteContainerHeight, "Delete Item");

            deleteContainer.setRight(deleteItem);
            deleteContainer.setPadding(new Insets(10));

        }
        // add all formContainer components
        formContainer.getChildren().addAll(nameForm, stockForm,
                buyPriceForm, sellPriceForm, categoryContainer,
                imageContainer,saveContainer, deleteContainer);
                // add all container components
        container.getChildren().addAll(title, formContainer);
        

        // Set ScrollPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.setContent(container);
    }

}