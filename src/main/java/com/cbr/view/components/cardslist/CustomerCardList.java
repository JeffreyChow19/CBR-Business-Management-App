package com.cbr.view.components.cardslist;

import com.cbr.models.Customer;
import com.cbr.models.DataList;
import com.cbr.view.components.cards.CustomerCard;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;

import java.util.List;
public class CustomerCardList extends VBox {
    private List<Customer> customerList;
    public CustomerCardList(List<Customer> customers){
        this.customerList = customers;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.updateChildren();
    }

    public void update (List<Customer> customers) {
        this.customerList = customers;
        this.updateChildren();
    }

    public void updateChildren(){
        this.getChildren().clear();
        for (Customer c : this.customerList) {
            this.getChildren().add(new CustomerCard(c));
        }
        if (this.customerList.isEmpty()){
            Label notFound = new Label("No match");
            notFound.setFont(Theme.getBodyMediumFont());
            notFound.setTextFill(Color.WHITE);
            this.getChildren().add(notFound);
        }
    }
}
