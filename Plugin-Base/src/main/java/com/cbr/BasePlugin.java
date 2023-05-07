package com.cbr;

import com.cbr.plugin.Plugin;
import com.cbr.view.components.header.headermenu.HeaderMenuBar;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

public class BasePlugin implements Plugin {
    @Getter @Setter private Boolean status;
    @Getter @Setter
    private VBox pageContent;
    @Getter @Setter
    private String pageName;

    public String getName(){
        return "com.cbr.BasePlugin";
    }
    public BasePlugin(){
        pageContent = new VBox();
        pageContent.setStyle("-fx-background-color:" + Theme.getPrimaryDark());
        pageContent.setMinSize(Theme.getScreenWidth(), Theme.getScreenHeight());
        pageContent.setAlignment(Pos.TOP_CENTER);
        pageContent.setSpacing(50);
        pageName = "Empty Page";
        this.status = false;
    }
    public void load(){
        if (!this.status){
            System.out.println(pageName);
            HeaderMenuBar.getInstance().addNewNavigationMenu(pageName, pageContent);
            this.status = true;
        }
    }
}
