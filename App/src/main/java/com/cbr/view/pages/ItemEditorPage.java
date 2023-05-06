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
import com.cbr.models.Pricing.BasePrice;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ItemEditorPage extends AddItemPage {

    public ItemEditorPage(InventoryProduct product) {
        super();
        title.setText("Edit Item #" + product.getId());

        // Set Form
        nameForm.getContentTextField().setText(product.getProductName());
        stockForm.getContentTextField().setText(product.getStock().toString());
        buyPriceForm.getContentTextField().setText(product.getBuyPrice().getValue().toString());
        sellPriceForm.getContentTextField().setText(product.getSellPrice().getValue().toString());
        categoryDropdown.setPromptText(product.getCategory());

        String imagePath = product.getImagePath();
        String[] parts = imagePath.split("file:assets/images/products/");
        String name = parts[1];
        imageContent.setText(name);
        String currentDir = System.getProperty("user.dir");
        selectedFile = new File(currentDir);
        String dirName = new File(currentDir).getName();
        String image_dir;
        if (dirName.equals("App")) {
            image_dir = Paths.get(currentDir, "assets/images/products").toString();
        } else {
            image_dir = Paths.get(currentDir, "App/assets/images/products").toString();
        }
        selectedFile = new File(image_dir, name);
        // Delete Button
        BorderPane deleteContainer = new BorderPane();
        double deleteContainerWidth = formContainerWidth;
        double deleteContainerHeight = 0.2 * formContainerHeight;
        deleteContainer.setMinSize(deleteContainerWidth, deleteContainerHeight);
        deleteContainer.setPrefSize(deleteContainerWidth, deleteContainerHeight);
        deleteContainer.setMaxSize(deleteContainerWidth, deleteContainerHeight);

        Button deleteItem = new DeleteButton(0.46 * deleteContainerWidth, deleteContainerHeight, "Delete Item");
        deleteItem.setOnAction(event -> {
            App.getDataStore().deactivateProduct(product.getId());
            product.setStatus(false);
            showAlert(Alert.AlertType.ERROR, container.getScene().getWindow(), "Delete Item Successful!",
                    "Item " + nameForm.getContentTextField().getText() + " successfully deleted!");
        });
        deleteContainer.setRight(deleteItem);
        deleteContainer.setPadding(new Insets(10));

        addItem.setText("Edit Item");
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
                    InventoryProduct savedItem = new InventoryProduct(nameForm.getContentTextField().getText(),
                            productBuyPrice, productSellPrice, "file:assets/images/products/" + imageContent.getText(),
                            productStock,
                            categoryDropdown.getValue(), true);
                    savedItem.setId(product.getId());
                    App.getDataStore().updateProductInfo(savedItem);
                    showAlert(Alert.AlertType.CONFIRMATION, container.getScene().getWindow(), "Edit Item Successful!",
                            "Item " + nameForm.getContentTextField().getText() + " successfully updated!");
                } catch (NumberFormatException e) {
                    errMsg += "Price/Stock must be a number!";
                    showAlert(Alert.AlertType.CONFIRMATION, container.getScene().getWindow(), "Add Item Error!",
                            errMsg);
                }

            }
        });

        this.formContainer.getChildren().add(deleteContainer);

        // Set ScrollPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.setContent(container);
    }

}