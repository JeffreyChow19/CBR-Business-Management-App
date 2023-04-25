package com.cbr.view.components.header;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class HeaderTabBar extends TabPane {
    public HeaderTabBar() {
        super();

        /* Setup Tab Menu and Home Tab */
        Tab homeTab = new Tab("Home", new Label("This is Home"));
        homeTab.setClosable(false);
        this.getTabs().add(homeTab);

        /* Example tabs */
        Tab tab2 = new Tab("Clients"  , new Label("This is Clients"));
        Tab tab3 = new Tab("History" , new Label("This is History of Client"));
        this.getTabs().add(tab2);
        this.getTabs().add(tab3);

        // chore: link tabs
    }
}
