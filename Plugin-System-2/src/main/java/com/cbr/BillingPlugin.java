package com.cbr;

import com.cbr.models.TemporaryInvoice;
import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;
import com.cbr.view.MainView;
import com.cbr.view.components.cards.AdditionalCostCard;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillingPlugin implements Plugin {
    @Setter
    @Getter
    private Boolean status;
    private Spinner<Double> taxSpinner;
    private Spinner<Double> serviceSpinner;

    public BillingPlugin(){
        this.status = false;
    }
    public String getName(){
        return "com.cbr.BillingPlugin";
    }
    public void load(){
        if (!this.status){
            VBox newFormContainer = new VBox();
            newFormContainer.setSpacing(30);
            newFormContainer.setAlignment(Pos.TOP_LEFT);
            Label taxLabel = new Label("Tax (%)");
            taxLabel.setFont(Theme.getHeading2Font());
            taxLabel.setTextFill(Color.WHITE);
            taxSpinner = new Spinner<>();

            Double defaultTaxValue = Double.parseDouble(AppSettings.getInstance().getAdditionalSettings().getOrDefault("Tax", "10.0"));
            SpinnerValueFactory<Double> taxValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, defaultTaxValue);
            taxSpinner.setValueFactory(taxValueFactory);
            taxSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                MainView.getInstance().getSettingsPage().getAdditionalValues().put("Tax", newValue.toString());
            });

            Label serviceLabel = new Label("Service Charge (%)");
            serviceLabel.setFont(Theme.getHeading2Font());
            serviceLabel.setTextFill(Color.WHITE);
            serviceSpinner = new Spinner<>();

            Double defaultServiceValue = Double.parseDouble(AppSettings.getInstance().getAdditionalSettings().getOrDefault("Service Charge", "10.0"));
            SpinnerValueFactory<Double> serviceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, defaultServiceValue);
            serviceSpinner.setValueFactory(serviceValueFactory);
            serviceSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                MainView.getInstance().getSettingsPage().getAdditionalValues().put("Service Charge", newValue.toString());
            });

            newFormContainer.getChildren().addAll(taxLabel, taxSpinner, serviceLabel, serviceSpinner);

            MainView.getInstance().getSettingsPage().getAdditionalValues().put("Tax", defaultTaxValue.toString());
            MainView.getInstance().getSettingsPage().getAdditionalValues().put("Service Charge", defaultServiceValue.toString());
            TemporaryInvoice.addAdditionalCosts("Tax", defaultTaxValue/100);
            TemporaryInvoice.addAdditionalCosts("Service Charge", defaultServiceValue/100);

            MainView.getInstance().getSettingsPage().getFormContainer().getChildren().addAll(newFormContainer);
            this.status = true;
        }
        SettingsUpdate update = new BillingUpdate();
        update.onSave();


//        MainView.getInstance().getTransactionPage().getAdditionalCostsContainer().getChildren().add(serviceCard);
//        MainView.getInstance().getTransactionPage().getAdditionalCostsContainer().getChildren().add(taxCard);
        List<TransactionPage> transactionPages = TabMenuBar.getInstance().getTabs()
                .stream()
                .filter(tab -> tab.getContent() instanceof TransactionPage)
                .map(tab -> (TransactionPage) tab.getContent())
                .collect(Collectors.toList());

        transactionPages.stream().forEach(transactionPage -> {
            System.out.println("masuk ke transaction page");
            if (transactionPage.getAdditionalCostsContainer().getChildren().isEmpty()){
                AdditionalCostCard serviceCard = new TaxCard(transactionPage.getTemporaryInvoice(),0.8 *MainView.getInstance().getTransactionPage().getManagementContainerWidth(), "Service Charge");
                AdditionalCostCard taxCard = new ServiceCard(transactionPage.getTemporaryInvoice(),0.8 *MainView.getInstance().getTransactionPage().getManagementContainerWidth(), "Tax");
                transactionPage.getAdditionalCostsContainer().getChildren().add(serviceCard);
                transactionPage.getAdditionalCostsContainer().getChildren().add(taxCard);
            }
        });
        System.out.println(MainView.getInstance().getTransactionPage());
    }
}
