package com.cbr.view.components.cardslist;

import com.cbr.models.Customer;
import com.cbr.models.Member;
import com.cbr.view.components.cards.clientcard.ClientCardBuilder;
import com.cbr.view.components.cards.clientcard.ClientCardDirector;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;
public class ClientCardList<T extends Customer> extends VBox {
    private List<T> customerList;
    public ClientCardList(List<T> customers){
        this.customerList = customers;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.updateChildren();
    }

    public void update (List<T> customers) {
        this.customerList = customers;
        this.updateChildren();
    }

    public void updateChildren(){
        this.getChildren().clear();
        ClientCardDirector dir = new ClientCardDirector();
        ClientCardBuilder builder = new ClientCardBuilder();
        for (Customer c : this.customerList) {
            if (c.getType() == "customer"){
                dir.createCustomersCard(builder, c);
            }
            else {
                dir.createMembersCard(builder, (Member) c);
            }
            this.getChildren().add(builder.getCard());
        }
        if (this.customerList.isEmpty()){
            Label notFound = new Label("No match");
            notFound.setFont(Theme.getBodyMediumFont());
            notFound.setTextFill(Color.WHITE);
            this.getChildren().add(notFound);
        }
    }
}
