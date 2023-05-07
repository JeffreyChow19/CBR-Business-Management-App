package com.cbr;

import com.cbr.models.TemporaryInvoice;
import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import com.cbr.view.components.cards.AdditionalCostCard;
import com.cbr.view.components.header.tabmenu.TabMenuBar;
import com.cbr.view.pages.TransactionPage;
import com.cbr.view.theme.Theme;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.stream.Collectors;

public class BillingPlugin implements Plugin {
    private Spinner<Double> taxSpinner;
    private Spinner<Double> serviceSpinner;

    public String getName(){
        return "com.cbr.BillingPlugin";
    }
    public void load(){
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
        Thread task = new Thread(() -> {
            while (true) {
                List<TransactionPage> transactionPages = TabMenuBar.getInstance().getTabs()
                        .stream()
                        .filter(tab -> tab.getContent() instanceof TransactionPage)
                        .map(tab -> (TransactionPage) tab.getContent())
                        .collect(Collectors.toList());
                String taxPercentage = AppSettings.getInstance().getAdditionalSettings().get("Tax");
                if (taxPercentage!=null){
                    TemporaryInvoice.addAdditionalCosts("Tax", Double.parseDouble(taxPercentage)/100);
                }
                String serviceChargePercentage = AppSettings.getInstance().getAdditionalSettings().get("Service Charge");
                if (serviceChargePercentage!=null){
                    TemporaryInvoice.addAdditionalCosts("Service Charge", Double.parseDouble(serviceChargePercentage)/100);
                }

                Platform.runLater(() -> {
                    transactionPages.stream().forEach(transactionPage -> {
                        if (transactionPage.getAdditionalCostsContainer().getChildren().isEmpty()){
                            AdditionalCostCard serviceCard = new TaxCard(transactionPage.getTemporaryInvoice(),0.8 *MainView.getInstance().getTransactionPage().getManagementContainerWidth(), "Service Charge");
                            AdditionalCostCard taxCard = new ServiceCard(transactionPage.getTemporaryInvoice(),0.8 *MainView.getInstance().getTransactionPage().getManagementContainerWidth(), "Tax");
                            transactionPage.getAdditionalCostsContainer().getChildren().add(serviceCard);
                            transactionPage.getAdditionalCostsContainer().getChildren().add(taxCard);
                        }
                        else {
                            ((TaxCard)(transactionPage.getAdditionalCostsContainer().getChildren().get(0))).setTemporaryInvoice(transactionPage.getTemporaryInvoice());
                            ((ServiceCard)(transactionPage.getAdditionalCostsContainer().getChildren().get(1))).setTemporaryInvoice(transactionPage.getTemporaryInvoice());
                        }
                    });
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        task.start();
    }
}
