package com.cbr;

import com.cbr.plugin.Plugin;
import com.cbr.view.MainView;
import com.cbr.view.theme.Theme;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

public class BasePlugin implements Plugin {
    @Getter @Setter
    private VBox pageContent;
    @Getter @Setter
    private String pageName;

    public String getName(){
        return "com.cbr.BasePlugin";
    }
    public BasePlugin(){
        System.out.println("aaaaaaaaaa");
    }
    public void load(){
        System.out.println("hello world!");
        pageContent = new VBox();
        pageContent.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        pageContent.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        pageName = "Empty Page";
        MainView.getInstance().getHeaderMenuBar().addNewNavigationMenu(pageName, pageContent);
    }
}
