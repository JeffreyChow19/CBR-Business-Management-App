package com.cbr.view.components.cardslist;

import com.cbr.App;
import com.cbr.models.FixedInvoice;
import com.cbr.view.components.cards.CustomerHistoryCard;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CustomerHistoryCardList extends ScrollPane {
    @Getter @Setter private List<FixedInvoice> historyList;

    private VBox customerHistoryContainer;
    public CustomerHistoryCardList(String customerId) {
        this.setMinWidth(Theme.getScreenWidth());
        this.setPrefWidth(Theme.getScreenWidth());
        this.setMaxWidth(Theme.getScreenWidth());
        this.setBorder(null);
        this.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setFitToHeight(true);

        customerHistoryContainer = new VBox();
        customerHistoryContainer.setMinWidth(Theme.getScreenWidth());
        customerHistoryContainer.setPrefWidth(Theme.getScreenWidth());
        customerHistoryContainer.setMaxWidth(Theme.getScreenWidth());
        customerHistoryContainer.setStyle("-fx-background-color:" + Theme.getPrimaryDark() + ";");
        customerHistoryContainer.setAlignment(Pos.TOP_CENTER);

        searchHistoryData();

        this.setContent(customerHistoryContainer);
    }

    public void searchHistoryData() {
        historyList = App.getDataStore().getInvoices().getDataList();
    }

    public void renderHistoryDataList() {
        this.customerHistoryContainer.getChildren().clear();
        for (FixedInvoice fi : historyList) {
            CustomerHistoryCard customerHistoryCard = new CustomerHistoryCard(fi);
            this.customerHistoryContainer.getChildren().add(customerHistoryCard);
        }
    }
}