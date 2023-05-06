package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class InvoiceButton extends DefaultButton {
    private String fixedInvoiceId;

    public InvoiceButton(String fixedInvoiceId) {
        super(Theme.getScreenWidth() * 0.06, Theme.getScreenHeight() * 0.04, "Invoice");
        this.fixedInvoiceId = fixedInvoiceId;
    }
}
