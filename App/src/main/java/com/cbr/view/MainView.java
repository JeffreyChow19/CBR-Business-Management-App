package com.cbr.view;

import com.cbr.view.components.headermenu.HeaderMenuBar;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.pages.ClientsPage;
import com.cbr.view.pages.HomePage;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.pages.SettingsPage;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

public class MainView extends VBox {
    private HomePage homePage;
    @Getter
    private SettingsPage settingsPage;
    private ClientsPage clientsPage;
    private TransactionPage transactionPage;
    @Getter
    private HeaderMenuBar headerMenuBar;
    @Getter
    private static volatile MainView instance;
    public static MainView getInstance() {
        if (instance == null) {
            synchronized (MainView.class) {
                if (instance == null) {
                    instance = new MainView();
                }
            }
        }
        return instance;
    }

    private MainView() {
        super();

        /* Pages */
        homePage = new HomePage();
        clientsPage = new ClientsPage();
        transactionPage = new TransactionPage();
        settingsPage = new SettingsPage(this);
        // chore: pages

        /* Body Setup */
        TabMenuBar tabs = new TabMenuBar();
        tabs.addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(tabs);

        /* Header Setup */
        headerMenuBar = new HeaderMenuBar(tabs);
        headerMenuBar.addNewNavigationMenu("Clients", clientsPage);
        headerMenuBar.addNewNavigationMenu("Inventory Management", new Label("inventory"));
        headerMenuBar.addNewNavigationMenu("Export Statements", new Label("export"));
        headerMenuBar.addNewNavigationMenu("Transaction", transactionPage);
        headerMenuBar.addNewPreferencesMenu("Settings", settingsPage);
        headerMenuBar.addNewPreferencesMenu("Plugins", new Label("plugins"));
//        headerMenuBar.setOpenedTab(tabs,
//                clientsPage,
//                new Label("inventory"),
//                new Label("export"),
//                transactionPage,
//                settingsPage,
//                new Label("plugins"));
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
