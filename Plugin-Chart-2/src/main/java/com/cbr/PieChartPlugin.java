package com.cbr;

import com.cbr.models.InventoryProduct;
import com.cbr.plugin.Plugin;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class PieChartPlugin extends BasePlugin implements Plugin{
    @Setter
    @Getter
    private Boolean status;

    public PieChartPlugin() {
        super();
        this.setPageName("Analyze Products");
        this.status = false;
    }
    public String getName(){
        return "com.cbr.PieChartPlugin";
    }
    public void load() {
        if (!this.status){
            PieChart chart = new PieChart();
            PageTitle title = new PageTitle("Analyze Products");

            chart.setLabelsVisible(true);
            chart.setLabelLineLength(10);
            this.getPageContent().getChildren().addAll(title, chart);
            chart.setMinSize(Theme.getScreenWidth() * 0.3, Theme.getScreenWidth() * 0.3);
            super.load();
            this.status = true;
            Thread task = new Thread(() -> {
                while (true) {
                    List<InventoryProduct> inventory = App.getDataStore().getInventory().getDataList();
                    Map<String, Long> productCountByCategory = inventory.stream()
                            .filter(product -> product.getStatus()) // only consider products with status == true
                            .collect(Collectors.groupingBy(InventoryProduct::getCategory, Collectors.counting()));
                    List<String> colors = Arrays.asList(
                            Theme.getAccentBlue(), Theme.getAccentRed(), Theme.getAccentGreen(), Theme.getPrimaryLight(), Theme.getDarkRed());


                    Platform.runLater(() -> {
                        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
                        for(Map.Entry<String, Long> product : productCountByCategory.entrySet()) {
                            PieChart.Data slice = new PieChart.Data(product.getKey(), product.getValue());
                            pieChartData.add(slice);
                        }
                        // Update the UI components here
                        if(pieChartData!=null){
                            chart.setData(pieChartData);
                        }
                    });


                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            task.start();
        }
    }
}
