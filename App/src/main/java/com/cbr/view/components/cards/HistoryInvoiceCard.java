package com.cbr.view.components.cards;

import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;

import java.util.List;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.cbr.utils.ExportPDF;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import com.cbr.view.components.buttons.ExportInvoiceButton;
import com.cbr.view.theme.Theme;
import javafx.scene.shape.Rectangle;

public class HistoryInvoiceCard extends VBox {
    private FixedInvoice invoice;

    public HistoryInvoiceCard(FixedInvoice invoice) {
        int currentRow = 0;
        GridPane gp = new GridPane();
        this.invoice = invoice;
        gp.setMinWidth(Theme.getScreenWidth() * 0.5);
        gp.setMaxWidth(Theme.getScreenWidth() * 0.5);
        gp.setPadding(new Insets(30));
        gp.setStyle("-fx-background-radius: 20; -fx-background-color: " + Theme.getPrimaryBase());
        gp.setHgap(15);
        gp.setVgap(7);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setHgrow(Priority.ALWAYS);
        ColumnConstraints col3 = new ColumnConstraints();
        ColumnConstraints col4 = new ColumnConstraints();
        gp.getColumnConstraints().addAll(col1, col2, col3, col4);

        // Top row: Transaction ID, timestamp
        Label fixedInvoiceId = new Label(this.invoice.getId());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Label fixedInvoiceDate = new Label(this.invoice.getCreatedAt().format(dateTimeFormatter));
        fixedInvoiceId.setFont(Theme.getHeading2Font());
        fixedInvoiceId.setTextFill(Color.WHITE);
        fixedInvoiceDate.setFont(Theme.getBodyMediumFont());
        fixedInvoiceDate.setTextFill(Color.WHITE);
        gp.add(fixedInvoiceId, 0, currentRow, 2, 1);
        gp.add(fixedInvoiceDate, 2, currentRow, 2, 1);
        GridPane.setHalignment(fixedInvoiceId, HPos.LEFT);
        GridPane.setHalignment(fixedInvoiceDate, HPos.RIGHT);

        // Followed by list of quantity, item name, price per unit, subtotal price
        List<BoughtProduct> invoiceProducts = this.invoice.getBoughtProducts();
        for (BoughtProduct p : invoiceProducts) {
            currentRow++;
            Label productQty = new Label(String.valueOf(p.getCount()));
            Label productName = new Label(p.getProductName());
            Label productPriceEach = new Label("@" + p.getSellPrice().toString());
            Label productSubtotal = new Label(p.total().toString());
            productQty.setFont(Theme.getBodyMediumFont());
            productQty.setTextFill(Color.WHITE);
            productName.setFont(Theme.getBodyMediumFont());
            productName.setTextFill(Color.WHITE);
            productPriceEach.setFont(Theme.getBodyMediumFont());
            productPriceEach.setTextFill(Color.WHITE);
            productSubtotal.setFont(Theme.getBodyMediumFont());
            productSubtotal.setTextFill(Color.WHITE);
            gp.add(productQty, 0, currentRow, 1, 1);
            gp.add(productName, 1, currentRow, 1, 1);
            gp.add(productPriceEach, 2, currentRow, 1, 1);
            gp.add(productSubtotal, 3, currentRow, 1, 1);
            GridPane.setHalignment(productQty, HPos.LEFT);
            GridPane.setHalignment(productName, HPos.LEFT);
            GridPane.setHalignment(productPriceEach, HPos.LEFT);
            GridPane.setHalignment(productSubtotal, HPos.RIGHT);
        }

        currentRow++;
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(Theme.getScreenWidth() * 0.46);
        rectangle.setHeight(1);
        rectangle.setFill(Color.WHITE);
        gp.add(rectangle, 0, currentRow, 4, 1);

        for (Map.Entry<String, String> entry : invoice.getAdditionalCosts().entrySet()) {
            currentRow++;
            Label entryLabel = new Label(entry.getKey());
            entryLabel.setFont(Theme.getBodyMediumFont());
            entryLabel.setTextFill(Color.WHITE);
            Label entryQty = new Label(entry.getValue());
            entryQty.setFont(Theme.getBodyMediumFont());
            entryQty.setTextFill(Color.WHITE);
            gp.add(entryLabel, 0, currentRow, 2, 1);
            gp.add(entryQty, 2, currentRow, 2, 1);
            GridPane.setHalignment(entryLabel, HPos.LEFT);
            GridPane.setHalignment(entryQty, HPos.RIGHT);
        }

        // "Discount" line
        currentRow++;
        Label discountLabel = new Label("Discount");
        discountLabel.setFont(Theme.getBodyMediumFont());
        discountLabel.setTextFill(Color.WHITE);
        Label discountQty = new Label(this.invoice.getDiscount().toString());
        discountQty.setFont(Theme.getBodyMediumFont());
        discountQty.setTextFill(Color.WHITE);
        gp.add(discountLabel, 0, currentRow, 2, 1);
        gp.add(discountQty, 2, currentRow, 2, 1);
        GridPane.setHalignment(discountLabel, HPos.LEFT);
        GridPane.setHalignment(discountQty, HPos.RIGHT);

        // "Points used" line
        currentRow++;
        Label pointsUsedLabel = new Label("Points used");
        pointsUsedLabel.setFont(Theme.getBodyMediumFont());
        pointsUsedLabel.setTextFill(Color.WHITE);
        Label pointsUsedQty = new Label(this.invoice.getUsedPoint().toString());
        pointsUsedQty.setFont(Theme.getBodyMediumFont());
        pointsUsedQty.setTextFill(Color.WHITE);
        gp.add(pointsUsedLabel, 0, currentRow, 2, 1);
        gp.add(pointsUsedQty, 2, currentRow, 2, 1);
        GridPane.setHalignment(pointsUsedLabel, HPos.LEFT);
        GridPane.setHalignment(pointsUsedQty, HPos.RIGHT);

        // Grand total line
        currentRow++;
        Label grandTotalLabel = new Label("Grand total");
        grandTotalLabel.setFont(Theme.getHeading2Font());
        grandTotalLabel.setTextFill(Color.WHITE);
        Label grandTotalQty = new Label(this.invoice.getGrandTotal().toString());
        grandTotalQty.setFont(Theme.getHeading2Font());
        grandTotalQty.setTextFill(Color.WHITE);
        gp.add(grandTotalLabel, 0, currentRow, 2, 1);
        gp.add(grandTotalQty, 2, currentRow, 2, 1);
        GridPane.setHalignment(grandTotalLabel, HPos.LEFT);
        GridPane.setHalignment(grandTotalQty, HPos.RIGHT);

        // Points line
        currentRow++;
        Label pointsLabel = new Label("Points");
        pointsLabel.setFont(Theme.getHeading2Font());
        pointsLabel.setTextFill(Color.WHITE);
        Label pointsQty = new Label(this.invoice.getGetPoint().toString());
        pointsQty.setFont(Theme.getHeading2Font());
        pointsQty.setTextFill(Color.WHITE);
        gp.add(pointsLabel, 0, currentRow, 2, 1);
        gp.add(pointsQty, 2, currentRow, 2, 1);
        GridPane.setHalignment(pointsLabel, HPos.LEFT);
        GridPane.setHalignment(pointsQty, HPos.RIGHT);

        // Export PDF
        ExportPDF exportInvoice = new ExportPDF();

        // Button to export to PDF
        currentRow++;
        ExportInvoiceButton exportButton = new ExportInvoiceButton(this.invoice);
        exportButton.setOnAction(event -> exportInvoice.init(exportButton, invoice));
        gp.add(exportButton, 2, currentRow, 2, 1);
        GridPane.setHalignment(exportButton, HPos.RIGHT);

        this.getChildren().add(gp);
        this.setAlignment(Pos.TOP_CENTER);
    }
}
