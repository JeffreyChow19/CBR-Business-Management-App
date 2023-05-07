package com.cbr.view.pages;

import com.cbr.App;
import com.cbr.models.*;
import com.cbr.view.components.cardslist.ClientCardList;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientsPage extends ScrollPane {
    private String searchQuery;
    private ClientCardList<Customer> customerCardList;
    private ClientCardList<Member> memberCardList;
    private ClientCardList<VIP> vipCardList;
    private VBox container;
    private ToggleGroup customerTypeSelector;
    public ClientsPage() {
        super();
        container = new VBox();

        this.memberCardList = new ClientCardList(new ArrayList<>());
        this.customerCardList = new ClientCardList(new ArrayList<>());
        this.vipCardList = new ClientCardList(new ArrayList<>());

        searchQuery = "";

        PageTitle pageTitle = new PageTitle("Clients");

        TextField searchBar = new TextField();
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            this.searchQuery = newValue.toLowerCase();
        });
        searchBar.setStyle("-fx-background-radius: 20;");
        searchBar.setPromptText("Search client by name or ID");
        searchBar.setPadding(new Insets(12));

        // set fixed size
        searchBar.setMaxSize(Theme.getScreenWidth()*0.3, Theme.getScreenWidth()*0.025);
        searchBar.setMinSize(Theme.getScreenWidth()*0.3, Theme.getScreenWidth()*0.025);


        this.customerTypeSelector = new ToggleGroup();
        ToggleButton customersButton = new ToggleButton("Customers");
        ToggleButton membersButton = new ToggleButton("Members");
        ToggleButton vipButton = new ToggleButton("VIP");

        customerTypeSelector.getToggles().addAll(customersButton, membersButton, vipButton);

        for (Toggle tb : customerTypeSelector.getToggles()){
            ToggleButton temp = (ToggleButton) tb;
            temp.setFont(Theme.getHeading2Font());
            temp.setMinWidth(Theme.getScreenWidth()*0.16);
            temp.setCursor(Cursor.HAND);

            // default
            temp.setStyle("-fx-background-color: transparent; -fx-text-fill: white;-fx-border-style: solid; -fx-border-width: 0 0 1 0; -fx-border-color: white;");

            // listener for when active
            temp.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    temp.setFont(Theme.getHeading2Font());
                    temp.setStyle("-fx-background-color: transparent; -fx-text-fill: white;-fx-border-style: solid; -fx-border-width: 0 0 5 0; -fx-border-color: white;");
                } else {
                    temp.setFont(Theme.getBodyMediumFont());
                    temp.setStyle("-fx-background-color: transparent; -fx-text-fill: white;-fx-border-style: solid; -fx-border-width: 0 0 1 0; -fx-border-color: white;");
                }
            });
        }

        customerTypeSelector.selectToggle(customersButton);

        customersButton.setOnAction(e -> {
            this.updateContent("customer");
        });

        membersButton.setOnAction(e -> {
           this.updateContent("member");
        });

        vipButton.setOnAction(e -> {
           this.updateContent("vip");
        });


        HBox toggleContainer = new HBox();
        toggleContainer.getChildren().addAll(customersButton, membersButton, vipButton);
        toggleContainer.setMaxWidth(Theme.getScreenWidth()*0.5);
        toggleContainer.setAlignment(Pos.CENTER); // set alignment to center

        container.getChildren().addAll(pageTitle, searchBar, toggleContainer, this.customerCardList);
        container.setAlignment(Pos.TOP_CENTER);
        container.prefWidthProperty().bind(this.widthProperty()); // bind the width of the VBox to the width of the ScrollPane
        container.prefHeightProperty().bind(this.heightProperty()); // bind the height of the VBox to the height of the ScrollPane
        container.setSpacing(50);
        container.setPadding(new Insets(50));
        container.setStyle("-fx-background-color:" + Theme.getPrimaryDark());

        this.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        this.setContent(container);

        Thread task = new Thread(() -> {
            while (true) {
                List<Member> memberList = App.getDataStore().getMembers().stream()
                        .filter(c -> c.getName().toLowerCase().contains(searchQuery) || c.getId().toLowerCase().contains(searchQuery))
                        .collect(Collectors.toList());
                List<Customer> customerList = App.getDataStore().getCustomers().stream()
                        .filter(c -> c.getId().toLowerCase().contains(searchQuery))
                        .collect(Collectors.toList());
                List<VIP> vipList = App.getDataStore().getVips().stream()
                        .filter(c -> c.getName().toLowerCase().contains(searchQuery) || c.getId().toLowerCase().contains(searchQuery))
                        .collect(Collectors.toList());
                Platform.runLater(() -> {
                    this.customerCardList.update(customerList);
                    this.vipCardList.update(vipList);
                    this.memberCardList.update(memberList);
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
    }

    public void updateContent(String choice){
        container.getChildren().remove(3);
        switch (choice){
            case "customer":
                container.getChildren().add(this.customerCardList);
                break;
            case "member"  :
                container.getChildren().add(this.memberCardList);
                break;
            case "vip":
                container.getChildren().add(this.vipCardList);
                break;
        }
    }

}
