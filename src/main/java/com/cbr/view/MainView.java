package com.cbr.view;

import com.cbr.view.components.headermenu.HeaderMenuBar;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.pages.ClientsPage;
import com.cbr.view.pages.HomePage;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.pages.SettingsPage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    private HomePage homePage;
    private SettingsPage settingsPage;
    private ClientsPage clientsPage;

    private TransactionPage transactionPage;

    public MainView() {
        super();

        /* Pages */
        homePage = new HomePage();
        clientsPage = new ClientsPage();
        transactionPage = new TransactionPage();
        settingsPage = new SettingsPage(this);
        // chore: pages

        /* Body Setup */
        TabMenuBar pageTabs = new TabMenuBar();
        pageTabs.addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(pageTabs);

        /* Header Setup */
        HeaderMenuBar headerMenuBar = new HeaderMenuBar();
        headerMenuBar.setOpenedTab(pageTabs,
                clientsPage,
                new Label("inventory"),
                new Label("export"),
                transactionPage,
                settingsPage,
                new Label("plugins"));
        // chore: pages

        /* Add Components to MainView */
        this.getChildren().add(headerMenuBar);
        this.getChildren().add(bodyContainer);
    }

    public void refresh(){
        // to do: close all opened tabs except home
        clientsPage.updateList("");
    }
}
