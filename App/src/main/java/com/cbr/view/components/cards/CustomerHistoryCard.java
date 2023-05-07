package com.cbr.view.components.cards;

import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;
import com.cbr.view.components.buttons.DefaultButton;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CustomerHistoryCard extends VBox {
    public CustomerHistoryCard(FixedInvoice invoice) {
        this.setMinWidth(0.6 * Theme.getScreenWidth());
        this.setPrefWidth(0.6 * Theme.getScreenWidth());
        this.setMaxWidth(0.6 * Theme.getScreenWidth());
        this.setStyle("-fx-background-radius: 20; -fx-background-color: " + Theme.getPrimaryBase());
        this.setAlignment(Pos.TOP_CENTER);
        this.setPadding(new Insets(30,0,30,0));

        Double contentWidth = 0.55 * Theme.getScreenWidth();

        // TITLE
        HBox historyCardTitle = new HBox();
        historyCardTitle.setMinWidth(contentWidth);
        historyCardTitle.setPrefWidth(contentWidth);
        historyCardTitle.setMaxWidth(contentWidth);
        historyCardTitle.setPadding(new Insets(5,15,5,15));

        Label transactionId = new Label("Transaction " + invoice.getId());
        transactionId.setFont(Theme.getHeading2Font());
        transactionId.setTextFill(Color.WHITE);

        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");
        Label transactionDate = new Label(invoice.getCreatedAt().format(dateTimeFormatter));
        transactionDate.setFont(Theme.getBodyMediumFont());
        transactionDate.setTextFill(Color.WHITE);

        historyCardTitle.getChildren().addAll(transactionId, titleSpacer, transactionDate);

        this.getChildren().add(historyCardTitle);

        // LIST OF PRODUCTS
        for (BoughtProduct bp : invoice.getBoughtProducts()){
            HBox itemInfos = new HBox();
            itemInfos.setMinWidth(contentWidth);
            itemInfos.setPrefWidth(contentWidth);
            itemInfos.setMaxWidth(contentWidth);
            itemInfos.setPadding(new Insets(5,15,5,15));
            itemInfos.setSpacing(10);

            Label itemCount = new Label(bp.getCount().toString());
            itemCount.setFont(Theme.getBodyMediumFont());
            itemCount.setTextFill(Color.WHITE);
            itemCount.setMinWidth(30);

            Label itemName = new Label(bp.getProductName());
            itemName.setFont(Theme.getBodyMediumFont());
            itemName.setTextFill(Color.WHITE);

            Region itemInfosSpacer = new Region();
            HBox.setHgrow(itemInfosSpacer, Priority.ALWAYS);

            Label sellPrice = new Label(String.format("@%.2f", bp.getSellPrice().getValue()));
            sellPrice.setFont(Theme.getBodyMediumFont());
            sellPrice.setTextFill(Color.WHITE);
            sellPrice.setMinWidth(0.15 * contentWidth);

            Label total = new Label(String.format("%.2f", bp.total()));
            total.setFont(Theme.getBodyMediumFont());
            total.setTextFill(Color.WHITE);
            total.setMinWidth(0.15 * contentWidth);
            total.setAlignment(Pos.CENTER_RIGHT);

            itemInfos.getChildren().addAll(itemCount, itemName, itemInfosSpacer, sellPrice, total);

            this.getChildren().add(itemInfos);
        }

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(contentWidth);
        rectangle.setHeight(1);
        rectangle.setFill(Color.WHITE);

        this.getChildren().add(rectangle);

        for (Map.Entry<String, String> entry : invoice.getAdditionalCosts().entrySet()) {
            AdditionalCostCard additionalEntry = new AdditionalCostCard(contentWidth, entry.getKey());
            additionalEntry.getCardNumber().setText(entry.getValue());
            this.getChildren().add(additionalEntry);
        }

        AdditionalCostCard discount = new AdditionalCostCard(contentWidth, "Discount");
        discount.getCardNumber().setText(String.format("%.2f", invoice.getDiscount()));

        AdditionalCostCard pointsUsed = new AdditionalCostCard(contentWidth, "Points Used");
        pointsUsed.getCardNumber().setText(String.format("%.2f", invoice.getUsedPoint()));

        AdditionalCostCard grandTotal = new AdditionalCostCard(contentWidth, "Grand Total");
        grandTotal.getCardNumber().setText(String.format("%.2f", invoice.getGrandTotal().getValue()));
        grandTotal.getCardLabel().setFont(Theme.getHeading2Font());

        AdditionalCostCard pointsGot = new AdditionalCostCard(contentWidth, "Points");
        pointsGot.getCardNumber().setText(String.format("+%.2f", invoice.getGetPoint()));
        pointsGot.getCardLabel().setFont(Theme.getHeading2Font());

        HBox exportContainer = new HBox();
        exportContainer.setMinWidth(contentWidth);
        exportContainer.setPrefWidth(contentWidth);
        exportContainer.setMaxWidth(contentWidth);
        exportContainer.setPadding(new Insets(20,15,5,15));
        exportContainer.setAlignment(Pos.CENTER_RIGHT);

        Button exportPdf = new DefaultButton(0.2 * contentWidth, 20.0, "Export as PDF");

        exportContainer.getChildren().add(exportPdf);

        this.getChildren().addAll(discount, pointsUsed, grandTotal, pointsGot, exportContainer);
    }
}
