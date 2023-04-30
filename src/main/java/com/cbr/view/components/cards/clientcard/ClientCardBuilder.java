package com.cbr.view.components.cards.clientcard;

import com.cbr.view.theme.Theme;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientCardBuilder implements IClientCardBuilder {
    private Circle status;
    private Label customerId;
    private Label name;
    private Label phoneNumber;
    private Label points;
    @Setter
    private List<Button> buttons;

    public ClientCard getCard(){
        return new ClientCard(new ArrayList<Node>(Arrays.asList(status, customerId, name, phoneNumber, points)), buttons);
    }

    public void setStatus(Boolean status) {
        this.status = new Circle();
        this.status.setRadius(12);

        if (status) {
            this.status.setFill(Color.web(Theme.getAccentGreen()));
        } else {
            this.status.setFill(Color.web(Theme.getAccentRed()));
        }
    }

    public void setCustomerId(String customerId) {
        this.customerId = new Label(customerId);
        this.customerId.setFont(Theme.getHeading2Font());
        this.customerId.setTextFill(Color.WHITE);
    }
    public void setName(String customerName) {
        this.name = new Label(customerName);
        this.name.setFont(Theme.getHeading2Font());
        this.name.setTextFill(Color.WHITE);
        this.name.setMinWidth(Theme.getScreenWidth()*0.16);
    };
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = new Label(phoneNumber);
        this.phoneNumber.setFont(Theme.getHeading2Font());
        this.phoneNumber.setTextFill(Color.WHITE);
        this.phoneNumber.setMinWidth(Theme.getScreenWidth()*0.12);
    }
    public void setPoints(Integer points){
        this.points = new Label(points + " Pts");
        this.points.setFont(Theme.getHeading2Font());
        this.points.setTextFill(Color.WHITE);
    }

}
