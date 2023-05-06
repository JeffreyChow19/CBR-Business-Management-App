package com.cbr.view;

import com.cbr.exception.PluginException;
import com.cbr.plugin.PluginManager;
import com.cbr.utils.AppSettings;
import com.cbr.view.components.header.headermenu.HeaderMenuBar;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.net.MalformedURLException;

public class MainView extends VBox {
    private HomePage homePage;
    @Getter
    private SettingsPage settingsPage;
    private ClientsPage clientsPage;
    @Getter
    private TransactionPage transactionPage;
    private InventoryPage inventoryPage;
    @Getter
    private ItemEditor editItem;
    @Getter
    private ProfileEditor editProfile;
    @Getter
    private ItemEditor addItem;
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

    public void init(){
        /* Pages */
        homePage = new HomePage();
        editItem = new ItemEditor("Edit Item");
        addItem = new ItemEditor("Add Item");
        editProfile = new ProfileEditor("Edit Profile");
        clientsPage = new ClientsPage();
        transactionPage = new TransactionPage();
        settingsPage = new SettingsPage();
        PluginsPage pluginsPage = new PluginsPage();
        inventoryPage = new InventoryPage();
        // chore: pages
        try {
            PluginManager.getInstance().loadNewPlugin();
        } catch (MalformedURLException e) {
        } catch (PluginException e) {
        }
        /* Body Setup */
        TabMenuBar.getInstance().addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(TabMenuBar.getInstance());

        homePage.setButtonActions(clientsPage, transactionPage, inventoryPage, settingsPage, pluginsPage);

        AppSettings settings = AppSettings.getInstance();

        /* Header Setup */
        HeaderMenuBar.getInstance().addNewNavigationMenu("Clients", clientsPage);
        HeaderMenuBar.getInstance().addNewNavigationMenu("Inventory Management", new Label("inventory"));
        HeaderMenuBar.getInstance().addNewNavigationMenu("Export Statements", new Label("export"));
        HeaderMenuBar.getInstance().addNewNavigationMenu("Transaction", transactionPage);
        HeaderMenuBar.getInstance().addNewPreferencesMenu("Settings", settingsPage);
        HeaderMenuBar.getInstance().addNewPreferencesMenu("Plugins", pluginsPage);
        // HeaderMenuBar.getInstance().setOpenedTab(tabs,
        // clientsPage,
        // new Label("inventory"),
        // new Label("export"),
        // transactionPage,
        // settingsPage,
        // new Label("plugins"));
        // chore: pages

        /* Add Components to MainView */
        this.getChildren().add(HeaderMenuBar.getInstance());
        this.getChildren().add(bodyContainer);
    }

    private MainView()  {
        super();
    }

    public void refresh() {
        // to do: close all opened tabs except home
        System.out.println("refreseh");
        PluginManager.getInstance().loadPlugin();
        System.out.println("after loading plugin");
        transactionPage = new TransactionPage();
        TabMenuBar.getInstance().addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(TabMenuBar.getInstance());
        // homePage = new HomePage();
        // clientsPage = new ClientsPage();
        // transactionPage = new TransactionPage();
        // settingsPage = new SettingsPage();
        PluginsPage pluginsPage = new PluginsPage();

        HeaderMenuBar.getInstance().getNavigationMenu().getItems().clear();
        HeaderMenuBar.getInstance().addNewNavigationMenu("Clients", clientsPage);
        HeaderMenuBar.getInstance().addNewNavigationMenu("Inventory Management", new Label("inventory"));
        HeaderMenuBar.getInstance().addNewNavigationMenu("Export Statements", new Label("export"));
        HeaderMenuBar.getInstance().addNewNavigationMenu("Transaction", transactionPage);
        HeaderMenuBar.getInstance().addNewPreferencesMenu("Settings", settingsPage);
        HeaderMenuBar.getInstance().addNewPreferencesMenu("Plugins", pluginsPage);
        clientsPage.updateList("");
    }
}
