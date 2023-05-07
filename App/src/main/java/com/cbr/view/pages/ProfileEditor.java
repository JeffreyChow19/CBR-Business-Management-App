package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.models.Pricing.BasePrice;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.components.buttons.DeleteButton;
import com.cbr.view.components.buttons.ActivateButton;

import com.cbr.view.components.form.FormArea;
import com.cbr.view.components.form.FormLabel;
import com.cbr.view.components.dropdown.Dropdown;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import com.cbr.App;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.*;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import com.cbr.models.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ProfileEditor<T extends Customer> extends StackPane {
    private VBox container;
    private PageTitle title;
    @Getter
    private FormArea nameForm;
    @Getter
    private FormArea phoneForm;
    @Getter
    private double formContainerWidth;

    public ProfileEditor(T customer) {
        super();
        title = new PageTitle("Edit Profile #" + customer.getId());

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
        Label idContent = new FormLabel(customer.getId(), idContainerWidth, idContainerHeight);
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
        Label pointsContent = new FormLabel("0", pointsContainerWidth, pointsContainerHeight);
        pointsContainer.setLeft(pointsLabel);
        pointsContainer.setRight(pointsContent);
        pointsContainer.setPadding(new Insets(10));

        // Row Name
        nameForm = new FormArea("Name", formContainerWidth, 0.2 * formContainerHeight);
        // Row phone
        phoneForm = new FormArea("Phone Number", formContainerWidth, 0.2 * formContainerHeight);

        // Category Dropdown
        List<String> membershipList = new ArrayList<>();
        
        BorderPane membershipContainer = new BorderPane();
        double membershipContainerWidth = formContainerWidth;
        double membershipContainerHeight = 0.2 * formContainerHeight;

        membershipContainer.setMinSize(membershipContainerWidth, membershipContainerHeight);
        membershipContainer.setPrefSize(membershipContainerWidth, membershipContainerHeight);
        membershipContainer.setMaxSize(membershipContainerWidth, membershipContainerHeight);

        Label membership = new FormLabel("Membership", membershipContainerWidth, membershipContainerHeight);
        Label membershipContent;
        membershipList.add("member");
        if(customer instanceof Member){
            membershipContent = new FormLabel("VIP", pointsContainerWidth, pointsContainerHeight);
            membershipList.add("VIP"); 
        } else if(customer instanceof VIP) {
            membershipContent = new FormLabel("member", pointsContainerWidth, pointsContainerHeight);
            membershipList.add("VIP");
        }

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

        Button saveProfile = new DefaultButton(0.46 * saveContainerWidth, saveContainerHeight, "Save Profile");
        saveContainer.setRight(saveProfile);
        saveContainer.setPadding(new Insets(10));
        saveProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean error = false;
                String errMsg = new String("");
                if (nameForm.getContentTextField().getText().isEmpty()) {
                    errMsg += "Name can\'t be empty!\n";
                    error = true;
                }
                if (phoneForm.getContentTextField().getText().isEmpty()) {
                    errMsg += "Phone can\'t be empty!\n";
                    error = true;
                }

                try {
                    Long phoneNumber = Long.parseLong(phoneForm.getContentTextField().getText());
                    if (error) {
                        showAlert(Alert.AlertType.ERROR, container.getScene().getWindow(), "Upgrade Profile Error!",
                                errMsg);
                        return;
                    }
                    Customer newCustomer;
                    if (membershipDropdown.getValue().equals("member")) {
                        newCustomer = new Member(customer.getId(), customer.getInvoiceList(),
                                nameForm.getContentTextField().getText(), phoneForm.getContentTextField().getText(), new HashMap<>());
                    } else {
                        newCustomer = new VIP(customer.getId(), customer.getInvoiceList(),
                                nameForm.getContentTextField().getText(), phoneForm.getContentTextField().getText(),
                                true, new BasePrice(0.0), 0.0, new HashMap<>());
                    }
                    App.getDataStore().updateCustomerInfo(newCustomer);
                    showAlert(Alert.AlertType.CONFIRMATION, container.getScene().getWindow(),
                            "Edit Profile Successful!",
                            "User " + nameForm.getContentTextField().getText() + "  profile successfully upgraded!");

                } catch (NumberFormatException e) {
                    errMsg += "Phone must be a number!";
                    showAlert(Alert.AlertType.ERROR, container.getScene().getWindow(), "Edit Profile Error!",
                            errMsg);
                }

            }
        });

        // Delete Button
        BorderPane buttonMembershipContainer = new BorderPane();
        double buttonMembershipContainerWidth = formContainerWidth;
        double buttonMembershipContainerHeight = 0.2 * formContainerHeight;
        buttonMembershipContainer.setMinSize(buttonMembershipContainerWidth, buttonMembershipContainerHeight);
        buttonMembershipContainer.setPrefSize(buttonMembershipContainerWidth, buttonMembershipContainerHeight);
        buttonMembershipContainer.setMaxSize(buttonMembershipContainerWidth, buttonMembershipContainerHeight);

        if (customer instanceof Member || customer instanceof VIP) {
            Member temp = (Member) customer;
            nameForm.getContentTextField().setText(temp.getName());
            phoneForm.getContentTextField().setText(temp.getPhoneNumber());
            pointsContent.setText(temp.getPoint().toString());
            membershipDropdown.setPromptText(temp.getType());
            membershipDropdown.setValue(temp.getType());
            if (temp.getStatus()) {
                Button deactivate = new DeleteButton(0.46 * buttonMembershipContainerWidth,
                        buttonMembershipContainerHeight,
                        "Deactivate Membership");
                buttonMembershipContainer.setRight(deactivate);
                deactivate.setOnAction(event -> {
                    App.getDataStore().deactivateMember(customer.getId());
                    showAlert(Alert.AlertType.CONFIRMATION, container.getScene().getWindow(),
                            "Deactivate Membership Successful!",
                            "User " + nameForm.getContentTextField().getText() + " successfully deactivated!");
                });
            } else {
                Button activate = new ActivateButton(0.46 * buttonMembershipContainerWidth,
                        buttonMembershipContainerHeight,
                        "Activate Membership");
                buttonMembershipContainer.setRight(activate);
                activate.setOnAction(event -> {
                    App.getDataStore().activateMember(customer.getId());
                    showAlert(Alert.AlertType.CONFIRMATION, container.getScene().getWindow(),
                            "Activate Membership Successful!",
                            "User " + nameForm.getContentTextField().getText() + " successfully activated!");
                });
            }
        }
        buttonMembershipContainer.setPadding(new Insets(10));

        // add all formContainer components
        formContainer.getChildren().addAll(idContainer, membershipContainer, nameForm, phoneForm, pointsContainer,
                saveContainer, buttonMembershipContainer);

        // add all container components
        container.getChildren().addAll(title, formContainer);

        // Set StackPane properties
        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.getChildren().add(container);
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

}