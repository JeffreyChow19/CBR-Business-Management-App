package com.cbr.view.components.cards;

import com.cbr.models.Customer;
import com.cbr.models.Member;
import com.cbr.view.components.buttons.HistoryButton;
import com.cbr.view.components.buttons.UpgradeButton;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MemberCard extends HBox{
    public MemberCard(Member member){
        HBox left = new HBox();

        Circle status = new Circle();
        status.setRadius(12);

        if (member.getStatus()){
            status.setFill(Color.web(Theme.getAccentGreen()));
        }
        else{
            status.setFill(Color.web(Theme.getAccentRed()));
        }

        Label memberId = new Label(member.getId());
        memberId.setFont(Theme.getHeading2Font());
        memberId.setTextFill(Color.WHITE);

        Label memberName = new Label(member.getName());
        memberName.setFont(Theme.getHeading2Font());
        memberName.setTextFill(Color.WHITE);
        memberName.setMinWidth(Theme.getScreenWidth()*0.16);

        Label phoneNumber = new Label(member.getPhoneNumber());
        phoneNumber.setFont(Theme.getHeading2Font());
        phoneNumber.setTextFill(Color.WHITE);
        phoneNumber.setMinWidth(Theme.getScreenWidth()*0.12);

        Label memberPoints = new Label(member.getPoint().toString() + " Pts");
        memberPoints.setFont(Theme.getHeading2Font());
        memberPoints.setTextFill(Color.WHITE);

        Region region = new Region();
        this.setHgrow(region, Priority.ALWAYS);
        Button history = new HistoryButton(member.getId());
        Button upgrade = new UpgradeButton(member.getId());

        this.getChildren().addAll(status, memberId, memberName, phoneNumber, memberPoints, region, history, upgrade);

        Insets margin = new Insets(10); // set the margin
        for (Node child : this.getChildren()) {
            HBox.setMargin(child, margin); // set the margin for each child node
        }

        this.setPadding(new Insets(20));
        this.setAlignment(Pos.CENTER);
        this.setMaxWidth(Theme.getScreenWidth()*0.7);
        this.setHeight(Theme.getScreenHeight()*0.35);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryBase() + ";  -fx-background-radius: 10;");
    }
}
