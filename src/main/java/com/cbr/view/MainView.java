package com.cbr.view;

import com.cbr.view.components.headermenu.HeaderMenuBar;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.pages.ClientsPage;
import com.cbr.view.pages.HomePage;
import com.cbr.view.pages.TransactionPage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    public MainView() {
        super();

        /* Pages */
        HomePage homepage = new HomePage();
        ClientsPage clientsPage = new ClientsPage();
        TransactionPage transactionPage = new TransactionPage();
        // chore: pages

        /* Body Setup */
        TabMenuBar pageTabs = new TabMenuBar();
        pageTabs.addTab("Home", homepage);

        // For Transaction Page debugging purpose
        pageTabs.addTab("Transaction Page", transactionPage);
        pageTabs.switchToTab("Transaction Page");

        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(pageTabs);

        /* Header Setup */
        HeaderMenuBar headerMenuBar = new HeaderMenuBar();
        headerMenuBar.setOpenedTab(pageTabs,
                clientsPage,
                new Label("inventory"),
                new Label("export"),
                transactionPage,
                new Label("settings"),
                new Label("plugins"));
        // chore: pages

        /* Add Components to MainView */
        this.getChildren().add(headerMenuBar);
        this.getChildren().add(bodyContainer);
    }
}
