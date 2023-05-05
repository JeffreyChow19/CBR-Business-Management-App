package com.cbr.view;

import com.cbr.App;
import com.cbr.exception.PluginException;
import com.cbr.plugin.PluginManager;
import com.cbr.utils.AppSettings;
import com.cbr.view.components.headermenu.HeaderMenuBar;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.pages.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

public class MainView extends VBox {
    private HomePage homePage;
    @Getter
    private SettingsPage settingsPage;
    private ClientsPage clientsPage;
    @Getter
    private TransactionPage transactionPage;
    @Getter
    private HeaderMenuBar headerMenuBar;
    private TabMenuBar tabs;
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

    private MainView()  {
        super();

        /* Pages */
        homePage = new HomePage();
        clientsPage = new ClientsPage();
        transactionPage = new TransactionPage();
        settingsPage = new SettingsPage();
        PluginsPage pluginsPage = new PluginsPage();
        // chore: pages

        /* Body Setup */
        TabMenuBar tabs = new TabMenuBar();
        tabs.addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(tabs);

        AppSettings settings = AppSettings.getInstance();

        /* Header Setup */
        headerMenuBar = new HeaderMenuBar(tabs);
        headerMenuBar.addNewNavigationMenu("Clients", clientsPage);
        headerMenuBar.addNewNavigationMenu("Inventory Management", new Label("inventory"));
        headerMenuBar.addNewNavigationMenu("Export Statements", new Label("export"));
        headerMenuBar.addNewNavigationMenu("Transaction", transactionPage);
        headerMenuBar.addNewPreferencesMenu("Settings", settingsPage);
        headerMenuBar.addNewPreferencesMenu("Plugins", pluginsPage);
        // headerMenuBar.setOpenedTab(tabs,
        // clientsPage,
        // new Label("inventory"),
        // new Label("export"),
        // transactionPage,
        // settingsPage,
        // new Label("plugins"));
        // chore: pages

        /* Add Components to MainView */
        this.getChildren().add(headerMenuBar);
        this.getChildren().add(bodyContainer);
    }

    public void refresh() {
        // to do: close all opened tabs except home
        PluginManager.getInstance().loadPlugin();
        transactionPage = new TransactionPage();
        TabMenuBar tabs = new TabMenuBar();
        tabs.addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(tabs);
        // homePage = new HomePage();
        // clientsPage = new ClientsPage();
        // transactionPage = new TransactionPage();
        // settingsPage = new SettingsPage();
        PluginsPage pluginsPage = new PluginsPage();
        headerMenuBar.getNavigationMenu().getItems().clear();
        headerMenuBar.addNewNavigationMenu("Clients", clientsPage);
        headerMenuBar.addNewNavigationMenu("Inventory Management", new Label("inventory"));
        headerMenuBar.addNewNavigationMenu("Export Statements", new Label("export"));
        headerMenuBar.addNewNavigationMenu("Transaction", transactionPage);
        headerMenuBar.addNewPreferencesMenu("Settings", settingsPage);
        headerMenuBar.addNewPreferencesMenu("Plugins", pluginsPage);
        clientsPage.updateList("");
    }
}
