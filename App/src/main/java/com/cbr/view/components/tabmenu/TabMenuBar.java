package com.cbr.view.components.tabmenu;

import com.cbr.view.theme.Theme;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabMenuBar extends TabPane {
    public TabMenuBar() {
        super();

        /* Setup Tab Menu and Home Tab */
        Tab homeTab = new Tab("Home", new Label("This is Home"));
        homeTab.setClosable(false);
        this.getTabs().add(homeTab);
        this.setStyle("-fx-background-color: " + Theme.getSecondaryBase() + ";-fx-font-family: Ubuntu");
    }

    public void switchToTab(String tabName) {
        Tab selectedTab = this.getTab(tabName);
        if (selectedTab != null) {
            this.getSelectionModel().select(selectedTab);
        }
    }

    public void addTab(String tabName, Node targetNode) {
        Tab foundTab = this.getTab(tabName);
        if (foundTab != null) {
            foundTab.setContent(targetNode);
        } else {
            Tab newTab = new Tab(tabName, targetNode);
            newTab.setStyle("-fx-font-family: Ubuntu;" );
            this.getTabs().add(newTab);
        }
        this.switchToTab(tabName);
    }

    public Tab getTab(String tabName) {
        Tab foundTab = null;
        for (Tab tab : this.getTabs()) {
            if (tab.getText().equals(tabName)) {
                foundTab = tab;
                break;
            }
        }
        return foundTab;
    }
}
