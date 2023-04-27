package com.cbr.view.components.cardslist;

import com.cbr.models.Member;
import com.cbr.view.components.cards.MemberCard;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class MemberCardList<T extends Member> extends VBox {
    private List<T> memberList;
    public MemberCardList(List<T> members){
        this.memberList = members;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.updateChildren();
    }

    public void update (List<T> members) {
        this.memberList = members;
        this.updateChildren();
    }

    public void updateChildren(){
        this.getChildren().clear();
        for (T c : this.memberList) {
            this.getChildren().add(new MemberCard(c));
        }
        if (this.memberList.isEmpty()){
            Label notFound = new Label("No match");
            notFound.setFont(Theme.getBodyMediumFont());
            notFound.setTextFill(Color.WHITE);
            this.getChildren().add(notFound);
        }
    }
}
