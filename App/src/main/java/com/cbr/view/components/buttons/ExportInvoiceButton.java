package com.cbr.view.components.buttons;

import com.cbr.models.FixedInvoice;
import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import lombok.Getter;

@Getter
public class ExportInvoiceButton extends DefaultButton {
    private FixedInvoice invoice;

    public ExportInvoiceButton(FixedInvoice invoice) {
        super(Theme.getScreenWidth() * 0.06, Theme.getScreenHeight() * 0.04, "Export as PDF");
        this.invoice = invoice;
    }
}
