package com.cbr.view.components.cards.clientcard;

import javafx.scene.control.Button;

import java.util.List;

public interface IClientCardBuilder {
    void setStatus(Boolean status);
    void setCustomerId(String customerId);
    void setName(String customerName);
    void setPhoneNumber(String phoneNumber);
    void setPoints(Double points);
    void setButtons(List<Button> buttons);
}
