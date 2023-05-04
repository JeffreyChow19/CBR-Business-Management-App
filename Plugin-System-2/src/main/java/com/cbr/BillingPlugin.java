package com.cbr;

import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.view.MainView;
import com.cbr.view.theme.Theme;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class BillingPlugin implements Plugin {

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
        Spinner<Double> taxSpinner = new Spinner<>();
        Double defaultTaxValue = Double.parseDouble(AppSettings.getInstance().getAdditionalSettings().getOrDefault("tax", "10.0"));
        SpinnerValueFactory<Double> taxValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, defaultTaxValue);
        taxSpinner.setValueFactory(taxValueFactory);
        taxSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            MainView.getInstance().getSettingsPage().getAdditionalValues().put("tax", newValue.toString());
        });

        Label serviceLabel = new Label("Service Charge (%)");
        serviceLabel.setFont(Theme.getHeading2Font());
        serviceLabel.setTextFill(Color.WHITE);
        Spinner<Double> serviceSpinner = new Spinner<>();
        Double defaultServiceValue = Double.parseDouble(AppSettings.getInstance().getAdditionalSettings().getOrDefault("tax", "10.0"));
        SpinnerValueFactory<Double> serviceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, defaultServiceValue);
        serviceSpinner.setValueFactory(serviceValueFactory);
        serviceSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            MainView.getInstance().getSettingsPage().getAdditionalValues().put("service charge", newValue.toString());
        });
        newFormContainer.getChildren().addAll(taxLabel, taxSpinner, serviceLabel, serviceSpinner);

        MainView.getInstance().getSettingsPage().getFormContainer().getChildren().addAll(newFormContainer);
    }
}
