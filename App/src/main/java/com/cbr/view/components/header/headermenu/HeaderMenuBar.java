package com.cbr.view.components.header.headermenu;

import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.theme.Theme;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import lombok.Getter;

public class HeaderMenuBar extends MenuBar {
    private static HeaderMenuBar instance;
    @Getter
    private Menu navigationMenu;
    @Getter
    private Menu preferencesMenu;

    private class HeaderMenu extends Menu {
        public HeaderMenu(String menuName) {
            super(menuName);
            this.getStyleClass().add("header-menu");
            this.setStyle(
                    "-fx-font-size: " + 24.0 / 1920 * Theme.getScreenWidth() +
                    ";-fx-font-family: Ubuntu"
            );
        }
    }

    public static HeaderMenuBar getInstance() {
        if (instance == null) {
            instance = new HeaderMenuBar();
        }
        return instance;
    }

    private HeaderMenuBar() {
        super();
        this.setStyle("-fx-background-color: " + Theme.getSecondaryBase() + ";-fx-text-fill: white;");
        /* Set Menu Items : Navigation Menu */
        this.navigationMenu = new HeaderMenu("Menu");

        /* Set Menu Items : Preferences Menu */
        this.preferencesMenu = new HeaderMenu("Preferences");

        /* Add to Self */
        this.getMenus().addAll(navigationMenu, preferencesMenu);

        // chore: link menu items to pages
    }

    public void addNewNavigationMenu(String menuName, Node menuContent){
        MenuItem newMenu = new MenuItem(menuName);
        newMenu.setStyle(
                "-fx-font-size: " + 24.0 / 1920 * Theme.getScreenWidth() +
                ";-fx-font-family: Ubuntu"
        );
        newMenu.setOnAction(event -> TabMenuBar.getInstance().addTab(menuName, menuContent));
        this.getNavigationMenu().getItems().add(newMenu);
    }

    public void addNewPreferencesMenu(String menuName, Node menuContent){
        MenuItem newMenu = new MenuItem(menuName);
        newMenu.setStyle(
                "-fx-font-size: " + 24.0 / 1920 * Theme.getScreenWidth() +
                ";-fx-font-family: Ubuntu"
        );
        newMenu.setOnAction(event -> TabMenuBar.getInstance().addTab(menuName, menuContent));
        this.getPreferencesMenu().getItems().add(newMenu);
    }

}
