package com.cbr.view.components.cards;

import com.cbr.models.BoughtProduct;
import com.cbr.models.FixedInvoice;
import com.cbr.view.theme.Theme;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CustomerHistoryCard extends VBox {
    public CustomerHistoryCard(FixedInvoice invoice) {
        this.setMinWidth(0.7 * Theme.getScreenWidth());
        this.setPrefWidth(0.7 * Theme.getScreenWidth());
        this.setMaxWidth(0.7 * Theme.getScreenWidth());
        this.setStyle("-fx-background-color: " + Theme.getPrimaryBase());

        Double contentWidth = 0.6 * Theme.getScreenWidth();

        // TITLE
        HBox historyCardTitle = new HBox();
        historyCardTitle.setMinWidth(contentWidth);
        historyCardTitle.setPrefWidth(contentWidth);
        historyCardTitle.setMaxWidth(contentWidth);

        Label transactionId = new Label("Transaction " + invoice.getId());
        transactionId.setFont(Theme.getHeading2Font());
        transactionId.setTextFill(Color.WHITE);

        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        Label transactionDate = new Label(invoice.getCreatedAt().toString().replace("T", " "));
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

            Label itemCount = new Label(bp.getCount().toString());
            itemCount.setFont(Theme.getBodyFont());
            itemCount.setTextFill(Color.WHITE);
            itemCount.setMinWidth(30);

            Label itemName = new Label(bp.getProductName());
            itemName.setFont(Theme.getBodyFont());
            itemName.setTextFill(Color.WHITE);

            Region itemInfosSpacer = new Region();
            HBox.setHgrow(itemInfosSpacer, Priority.ALWAYS);

            Label sellPrice = new Label(String.format("@%.2f", bp.getSellPrice().getValue()));
            sellPrice.setFont(Theme.getBodyFont());
            sellPrice.setTextFill(Color.WHITE);

            Label total = new Label(String.format("%.2f", bp.total()));
            total.setFont(Theme.getBodyFont());
            total.setTextFill(Color.WHITE);

            itemInfos.getChildren().addAll(itemCount, itemName, itemInfosSpacer, sellPrice, total);

            this.getChildren().add(itemInfos);
        }

        AdditionalCostCard discount = new AdditionalCostCard(contentWidth, "Discount");
        discount.getCardNumber().setText(String.format("%.2f", invoice.getDiscount()));

        AdditionalCostCard pointsUsed = new AdditionalCostCard(contentWidth, "Points Used");
        pointsUsed.getCardNumber().setText(String.format("%.2f", invoice.getUsedPoint()));

        AdditionalCostCard grandTotal = new AdditionalCostCard(contentWidth, "Grand Total");
        grandTotal.getCardNumber().setText(String.format("%.2f", invoice.total() - invoice.getDiscount() - invoice.getUsedPoint()));

        AdditionalCostCard pointsGot = new AdditionalCostCard(contentWidth, "Grand Total");
        pointsGot.getCardNumber().setText(String.format("+%.2f", invoice.getGetPoint()));

        this.getChildren().addAll(discount, pointsUsed, grandTotal, pointsGot);
    }
}
