package com.cbr.view.components.cards;

import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;

import java.util.List;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import com.cbr.view.components.buttons.ExportInvoiceButton;
import com.cbr.view.theme.Theme;

public class HistoryInvoiceCard extends GridPane {
    private FixedInvoice invoice;

    public HistoryInvoiceCard(FixedInvoice invoice) {
        int currentRow = 0;
        this.invoice = invoice;
        this.setMaxWidth(Theme.getScreenWidth() * 0.65);
        this.setHeight(Theme.getScreenWidth() * 0.3);
        this.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        this.getColumnConstraints().addAll(col1, col2, col3, col4);

        // Top row: Transaction ID, timestamp
        Label fixedInvoiceId = new Label(this.invoice.getId());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Label fixedInvoiceDate = new Label(this.invoice.getCreatedAt().format(dateTimeFormatter));
        fixedInvoiceId.setFont(Theme.getHeading2Font());
        fixedInvoiceId.setTextFill(Color.WHITE);
        fixedInvoiceDate.setFont(Theme.getBodyMediumFont());
        fixedInvoiceDate.setTextFill(Color.WHITE);
        this.add(fixedInvoiceId, 0, currentRow, 2, 1);
        this.add(fixedInvoiceDate, 2, currentRow, 2, 1);
        GridPane.setHalignment(fixedInvoiceId, HPos.LEFT);
        GridPane.setHalignment(fixedInvoiceDate, HPos.RIGHT);

        // Followed by list of quantity, item name, price per unit, subtotal price
        List<BoughtProduct> invoiceProducts = this.invoice.getBoughtProducts();
        for (BoughtProduct p : invoiceProducts) {
            currentRow++;
            Label productQty = new Label(String.valueOf(p.getCount()));
            Label productName = new Label(p.getProductName());
            Label productPriceEach = new Label("@" + String.format("%.2f", p.getSellPrice().getValue()));
            Label productSubtotal = new Label(String.format("%.2f", p.getSellPrice().getValue() * p.getCount()));
            productQty.setFont(Theme.getBodyMediumFont());
            productQty.setTextFill(Color.WHITE);
            productName.setFont(Theme.getBodyMediumFont());
            productName.setTextFill(Color.WHITE);
            productPriceEach.setFont(Theme.getBodyMediumFont());
            productPriceEach.setTextFill(Color.WHITE);
            productSubtotal.setFont(Theme.getBodyMediumFont());
            productSubtotal.setTextFill(Color.WHITE);
            this.add(productQty, 0, currentRow, 1, 1);
            this.add(productName, 1, currentRow, 1, 1);
            this.add(productPriceEach, 2, currentRow, 1, 1);
            this.add(productSubtotal, 3, currentRow, 1, 1);
            GridPane.setHalignment(productQty, HPos.LEFT);
            GridPane.setHalignment(productName, HPos.LEFT);
            GridPane.setHalignment(productPriceEach, HPos.LEFT);
            GridPane.setHalignment(productSubtotal, HPos.RIGHT);
        }

        // "Discount" line
        currentRow++;
        Label discountLabel = new Label("Discount");
        discountLabel.setFont(Theme.getBodyMediumFont());
        discountLabel.setTextFill(Color.WHITE);
        Label discountQty = new Label(String.format("%.2f", this.invoice.getDiscount()));
        discountQty.setFont(Theme.getBodyMediumFont());
        discountQty.setTextFill(Color.WHITE);
        this.add(discountLabel, 0, currentRow, 2, 1);
        this.add(discountQty, 3, currentRow, 1, 1);
        GridPane.setHalignment(discountLabel, HPos.LEFT);
        GridPane.setHalignment(discountQty, HPos.RIGHT);

        // "Points used" line
        currentRow++;
        Label pointsUsedLabel = new Label("Points used");
        pointsUsedLabel.setFont(Theme.getBodyMediumFont());
        pointsUsedLabel.setTextFill(Color.WHITE);
        this.add(pointsUsedLabel, 0, currentRow, 2, 1);
        GridPane.setHalignment(pointsUsedLabel, HPos.LEFT);
        // Where to get points used in the transaction? TBD

        // Grand total line
        currentRow++;
        Label grandTotalLabel = new Label("Grand total");
        grandTotalLabel.setFont(Theme.getHeading2Font());
        grandTotalLabel.setTextFill(Color.WHITE);
        this.add(grandTotalLabel, 0, currentRow, 2, 1);
        GridPane.setHalignment(grandTotalLabel, HPos.LEFT);
        // Where to get grand total value? TBD

        // Points line
        currentRow++;
        Label pointsLabel = new Label("Points");
        pointsLabel.setFont(Theme.getHeading2Font());
        pointsLabel.setTextFill(Color.WHITE);
        this.add(pointsLabel, 0, currentRow, 2, 1);
        GridPane.setHalignment(pointsLabel, HPos.LEFT);
        // Where to get points? TBD

        // Button to export to PDF
        currentRow++;
        ExportInvoiceButton exportButton = new ExportInvoiceButton(this.invoice);
        this.add(exportButton, 2, currentRow, 2, 1);
        GridPane.setHalignment(exportButton, HPos.RIGHT);
    }
}
