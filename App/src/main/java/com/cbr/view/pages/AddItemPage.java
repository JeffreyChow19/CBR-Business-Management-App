package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.view.components.buttons.DefaultButton;

import com.cbr.view.components.form.FormArea;
import com.cbr.view.components.form.FormLabel;
import com.cbr.view.components.form.dropdown.Dropdown;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.components.labels.ToolTipLabel;
import com.cbr.view.theme.Theme;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.cbr.models.*;
import com.cbr.models.Pricing.BasePrice;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddItemPage extends ScrollPane {
    protected VBox container;
    protected VBox formContainer;
    protected PageTitle title;
    @Getter
    protected FormArea nameForm;
    @Getter
    protected FormArea stockForm;
    @Getter
    protected FormArea buyPriceForm;
    @Getter
    protected FormArea sellPriceForm;
    @Getter
    protected double formContainerWidth;
    protected double formContainerHeight;
    protected ToolTipLabel imageContent;
    protected File selectedFile;
    protected Dropdown categoryDropdown;
    protected Button addItem;

    public AddItemPage() {
        super();
        title = new PageTitle("Add Item");

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
        formContainer = new VBox();

        formContainerWidth = 0.4 * Theme.getScreenWidth();
        double formContainerHeight = 0.4 * Theme.getScreenHeight();

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
        double uploadImageContainerWidth = 0.48 * imageContainerWidth;
        double uploadImageContainerHeight = 0.2 * imageContainerHeight;
        uploadImageContainer.setMinSize(uploadImageContainerWidth, uploadImageContainerHeight);
        uploadImageContainer.setPrefSize(uploadImageContainerWidth, uploadImageContainerHeight);
        uploadImageContainer.setMaxSize(uploadImageContainerWidth, uploadImageContainerHeight);

        imageContent = new ToolTipLabel("No File Selected");
        imageContent.setFont(Theme.getBodyMediumFont());
        imageContent.setTextFill(Color.WHITE);
        // imageContent.setAlignment(Pos.TOP_LEFT);
        imageContent.setPrefWidth(0.5 * uploadImageContainerWidth);
        imageContent.setPrefHeight(uploadImageContainerHeight);

        Button uploadButton = new DefaultButton(0.5 * uploadImageContainerWidth, uploadImageContainerHeight, "Upload");

        uploadButton.setOnAction(event -> {
            uploadItemImage();
        });
        uploadImageContainer.getChildren().addAll(uploadButton, imageContent);
        uploadImageContainer.setSpacing(10);
        uploadImageContainer.setPadding(new Insets(0, 0, 10, 10));

        imageContainer.setLeft(imageLabel);
        imageContainer.setRight(uploadImageContainer);
        imageContainer.setPadding(new Insets(10));

        // Category Dropdown
        List<InventoryProduct> productList = App.getDataStore().getInventory().getDataList();
        Set<String> categorySet = new HashSet<>();
        for (InventoryProduct product : productList) {
            categorySet.add(product.getCategory());
        }
        List<String> categoryList = new ArrayList<>(categorySet);

        BorderPane categoryContainer = new BorderPane();
        double categoryContainerWidth = formContainerWidth;
        double categoryContainerHeight = 0.2 * formContainerHeight;

        categoryContainer.setMinSize(categoryContainerWidth, categoryContainerHeight);
        categoryContainer.setPrefSize(categoryContainerWidth, categoryContainerHeight);
        categoryContainer.setMaxSize(categoryContainerWidth, categoryContainerHeight);

        Label category = new FormLabel("Category", categoryContainerWidth, categoryContainerHeight);

        categoryDropdown = new Dropdown(categoryList);
        categoryDropdown.setPrefWidth(0.5 * categoryContainerWidth);
        categoryDropdown.setPrefHeight(categoryContainerHeight);
        categoryDropdown.setPromptText("Select Category");

        categoryContainer.setLeft(category);
        categoryContainer.setRight(categoryDropdown);
        categoryContainer.setPadding(new Insets(10));

        // add button
        BorderPane addContainer = new BorderPane();
        double addContainerWidth = formContainerWidth;
        double addContainerHeight = 0.2 * formContainerHeight;
        addContainer.setMinSize(addContainerWidth, addContainerHeight);
        addContainer.setPrefSize(addContainerWidth, addContainerHeight);
        addContainer.setMaxSize(addContainerWidth, addContainerHeight);

        addItem = new DefaultButton(0.46 * addContainerWidth, addContainerHeight, "Add Item");
        addContainer.setRight(addItem);
        addContainer.setPadding(new Insets(10));

        addItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean error = false;
                String errMsg = new String("");
                if (nameForm.getContentTextField().getText().isEmpty()) {
                    errMsg += "Name can\'t be empty!\n";
                    error = true;
                }
                if (stockForm.getContentTextField().getText().isEmpty()) {
                    errMsg += "Stock can\'t be empty!\n";
                    error = true;
                }
                if (buyPriceForm.getContentTextField().getText().isEmpty()) {
                    errMsg += "Buy Price can\'t be empty!\n";
                    error = true;
                }
                if (sellPriceForm.getContentTextField().getText().isEmpty()) {
                    errMsg += "Sell Price can\'t be empty!\n";
                    error = true;
                }
                if (categoryDropdown.getValue() == null) {
                    errMsg += "Category can\'t be empty!\n";
                    error = true;
                }

                if (imageContent.getText().equals("No File Selected")) {
                    errMsg += "Image can\'t be empty!\n";
                    error = true;
                }
                try {
                    BasePrice productBuyPrice = new BasePrice(
                            Double.parseDouble(buyPriceForm.getContentTextField().getText()));
                    BasePrice productSellPrice = new BasePrice(
                            Double.parseDouble(sellPriceForm.getContentTextField().getText()));
                    Integer productStock = Integer.parseInt(stockForm.getContentTextField().getText());

                    if (error) {
                        showAlert(Alert.AlertType.ERROR, container.getScene().getWindow(), "Add Item Error!", errMsg);
                        return;
                    }

                    // Get the current working directory
                    String currentDir = System.getProperty("user.dir");

                    // Define the path to the assets directory
                    String dirName = new File(currentDir).getName();
                    String assetsDir;
                    if (dirName.equals("App")) {
                        assetsDir = Paths.get(currentDir, "assets/images/products").toString();
                    } else {
                        assetsDir = Paths.get(currentDir, "App/assets/images/products").toString();
                    }

                    // Get the name of the uploaded file
                    String fileName = selectedFile.getName();

                    // Define the path to the uploaded file
                    String uploadedFilePath = Paths.get(selectedFile.getAbsolutePath()).toString();

                    // Define the path to the destination file in the photos directory
                    String destinationFilePath = Paths.get(assetsDir, fileName).toString();

                    // Move the uploaded file to the photos directory
                    Path sourcePath = Paths.get(uploadedFilePath);
                    Path destinationPath = Paths.get(destinationFilePath);
                    try {
                        Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    InventoryProduct newItem = new InventoryProduct(nameForm.getContentTextField().getText(),
                            productBuyPrice, productSellPrice, "file:assets/images/products/" + imageContent.getText(),
                            productStock,
                            categoryDropdown.getValue(), true);
                    newItem.generateInventoryProductId();
                    App.getDataStore().addProduct(newItem);
                    showAlert(Alert.AlertType.CONFIRMATION, container.getScene().getWindow(), "Add Item Successful!",
                            "Item " + nameForm.getContentTextField().getText() + " successfully added!");
                    TabMenuBar.getInstance().closeTab(title.getText(), "Inventory Management");
                } catch (NumberFormatException e) {
                    errMsg += "Price/Stock must be a number!";
                    showAlert(Alert.AlertType.ERROR, container.getScene().getWindow(), "Add Item Error!", errMsg);
                }

            }
        });

        // add all formContainer components
        formContainer.getChildren().addAll(nameForm, stockForm,
                buyPriceForm, sellPriceForm, categoryContainer,
                imageContainer, addContainer);
        // add all container components
        container.getChildren().addAll(title, formContainer);

        // Set ScrollPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.setContent(container);
    }

    protected void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().setStyle("-fx-font: " + Theme.getBodyMediumFont().getSize() + "px "
                + Theme.getBodyMediumFont().getFamily()
                + "; -fx-border-color: white; -fx-background-color: transparent; -fx-border-radius: 10; -fx-prompt-text-fill: white;");
        alert.initOwner(owner);
        alert.setOnCloseRequest(e -> {
            alert.close();
        });
        alert.show();
    }

    protected void uploadItemImage() {
        FileChooser fileChooser = new FileChooser();
        String currentDir = System.getProperty("user.dir");
        File initialDirectory = new File(currentDir);
        fileChooser.setInitialDirectory(initialDirectory);
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg"));
        selectedFile = fileChooser.showOpenDialog(container.getScene().getWindow());
        if (selectedFile != null) {
            imageContent.setText(selectedFile.getName());
        }
    }
}