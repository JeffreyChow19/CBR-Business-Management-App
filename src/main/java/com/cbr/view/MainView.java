package com.cbr.view;

import com.cbr.view.components.headermenu.HeaderMenuBar;
import com.cbr.view.components.tabmenu.TabMenuBar;
import com.cbr.view.pages.ClientsPage;
import com.cbr.view.pages.HomePage;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends VBox {
    public MainView() {
        super();

        /* Pages */
        HomePage homepage = new HomePage();
        ClientsPage clientsPage = new ClientsPage();
        // chore: pages

        /* Body Setup */
        TabMenuBar pageTabs = new TabMenuBar();
        pageTabs.addTab("Home", homepage);
        BorderPane bodyContainer = new BorderPane();
        bodyContainer.setTop(pageTabs);

        /* Header Setup */
        HeaderMenuBar headerMenuBar = new HeaderMenuBar();
        headerMenuBar.setOpenedTab(pageTabs,
                clientsPage,
                new Label("inventory"),
                new Label("export"),
                new Label("transaction"),
                new Label("settings"),
                new Label("plugins"));
        // chore: pages

        /* Add Components to MainView */
        this.getChildren().add(headerMenuBar);
        this.getChildren().add(bodyContainer);
    }
}
