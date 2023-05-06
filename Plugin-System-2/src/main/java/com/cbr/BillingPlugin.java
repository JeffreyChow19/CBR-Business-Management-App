package com.cbr;

import com.cbr.plugin.Plugin;
import com.cbr.utils.AppSettings;
import com.cbr.utils.SettingsUpdate;
import com.cbr.view.MainView;
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
            System.out.println("loadinggg");
            VBox newFormContainer = new VBox();
            newFormContainer.setSpacing(30);
            newFormContainer.setAlignment(Pos.TOP_LEFT);
            Label taxLabel = new Label("Tax (%)");
            taxLabel.setFont(Theme.getHeading2Font());
            taxLabel.setTextFill(Color.WHITE);
            taxSpinner = new Spinner<>();
            Double defaultTaxValue = Double.parseDouble(AppSettings.getInstance().getAdditionalSettings().getOrDefault("tax", "10.0"));
            SpinnerValueFactory<Double> taxValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, defaultTaxValue);
            taxSpinner.setValueFactory(taxValueFactory);
            taxSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                MainView.getInstance().getSettingsPage().getAdditionalValues().put("tax", newValue.toString());
            });

            Label serviceLabel = new Label("Service Charge (%)");
            serviceLabel.setFont(Theme.getHeading2Font());
            serviceLabel.setTextFill(Color.WHITE);
            serviceSpinner = new Spinner<>();
            Double defaultServiceValue = Double.parseDouble(AppSettings.getInstance().getAdditionalSettings().getOrDefault("tax", "10.0"));
            SpinnerValueFactory<Double> serviceValueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 20, defaultServiceValue);
            serviceSpinner.setValueFactory(serviceValueFactory);
            serviceSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                MainView.getInstance().getSettingsPage().getAdditionalValues().put("service charge", newValue.toString());
            });
            newFormContainer.getChildren().addAll(taxLabel, taxSpinner, serviceLabel, serviceSpinner);

            MainView.getInstance().getSettingsPage().getAdditionalValues().put("tax", defaultTaxValue.toString());
            MainView.getInstance().getSettingsPage().getAdditionalValues().put("service charge", defaultServiceValue.toString());
            MainView.getInstance().getSettingsPage().getFormContainer().getChildren().addAll(newFormContainer);
            MainView.getInstance().getSettingsPage().getOnSaves().add(new BillingUpdate());
            this.status = true;
        }
    }
}
