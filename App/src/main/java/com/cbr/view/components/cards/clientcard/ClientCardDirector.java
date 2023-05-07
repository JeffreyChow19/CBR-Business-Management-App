package com.cbr.view.components.cards.clientcard;

import com.cbr.models.Customer;
import com.cbr.models.Member;
import com.cbr.view.components.buttons.HistoryButton;
import com.cbr.view.components.buttons.UpgradeButton;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ClientCardDirector {
    public void createCustomersCard(ClientCardBuilder builder, Customer customer) {
        builder.setCustomerId(customer.getId());
        builder.setName("CUSTOMER #" + customer.getId());

        List<Button> buttons = new ArrayList<>();
        buttons.add(new HistoryButton(customer.getId()));
        buttons.add(new UpgradeButton(customer.getId()));
        builder.setButtons(buttons);
    }

    public void createMembersCard(ClientCardBuilder builder, Member member) {
        builder.setCustomerId(member.getId());
        builder.setName(member.getName());
        builder.setPoints(member.getPoint().toString());
        builder.setStatus(member.getStatus());
        builder.setPhoneNumber(member.getPhoneNumber());
        List<Button> buttons = new ArrayList<>();
        buttons.add(new HistoryButton(member.getId()));
        buttons.add(new UpgradeButton(member.getId()));
        buttons.get(1).setText("Edit");
        builder.setButtons(buttons);
    }
}
