package com.cbr.view;

import com.cbr.App;
import com.cbr.exception.PluginException;
import com.cbr.models.FixedInvoice;
import com.cbr.plugin.PluginManager;
import com.cbr.utils.ExportPDF;
import com.cbr.view.components.header.headermenu.HeaderMenuBar;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.net.MalformedURLException;
import java.util.List;

public class MainView extends VBox {
    private HomePage homePage;
    @Getter
    private SettingsPage settingsPage;
    private ClientsPage clientsPage;
    @Getter
    private TransactionPage transactionPage;
    private InventoryPage inventoryPage;
//    @Getter
//    private ProfileEditor editProfile;
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

    public void init() {
        /* Pages */
        homePage = new HomePage();
//        editProfile = new ProfileEditor("Edit Profile");
        clientsPage = new ClientsPage();
        transactionPage = new TransactionPage();
        settingsPage = new SettingsPage();
        PluginsPage pluginsPage = new PluginsPage();
        inventoryPage = new InventoryPage();
        // chore: pages
        /* Export Statement */
        ExportPDF exportStatement = new ExportPDF();

        /* Body Setup */
        TabMenuBar.getInstance().addTab("Home", homePage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(TabMenuBar.getInstance());

        homePage.setButtonActions(clientsPage, transactionPage, inventoryPage, settingsPage, pluginsPage);

        /* Header Setup */
        HeaderMenuBar.getInstance().addNewNavigationMenu("Clients", clientsPage);
        HeaderMenuBar.getInstance().addNewNavigationMenu("Inventory Management", inventoryPage);
        HeaderMenuBar.getInstance().addNewNavigationMenu("Export Statements", exportStatement);
        HeaderMenuBar.getInstance().addNewNavigationMenu("Transaction", transactionPage);
        HeaderMenuBar.getInstance().addNewPreferencesMenu("Settings", settingsPage);
        HeaderMenuBar.getInstance().addNewPreferencesMenu("Plugins", pluginsPage);

        try {
            PluginManager.getInstance().loadNewPlugin();
        } catch (MalformedURLException e) {
        } catch (PluginException e) {
        }
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

    private MainView() {
        super();
    }

}
