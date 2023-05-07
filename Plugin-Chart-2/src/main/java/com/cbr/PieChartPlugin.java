package com.cbr;

import com.cbr.models.InventoryProduct;
import com.cbr.plugin.Plugin;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PieChartPlugin extends BasePlugin implements Plugin{

    public PieChartPlugin() {
        super();
        this.setPageName("Analyze Products");
    }
    public String getName(){
        return "com.cbr.PieChartPlugin";
    }
    public void load() {
        PieChart chart = new PieChart();
        VBox chartContainer = new VBox();
        PageTitle chartTitle = new PageTitle("Product Count by Category");
        chartContainer.setStyle("-fx-background-color: " + Theme.getPrimaryLight() + ";-fx-background-radius: 12");
        chartContainer.setMaxWidth(Theme.getScreenWidth() * 0.5);
        chartContainer.getChildren().addAll(chartTitle, chart);
        chartContainer.setAlignment(Pos.CENTER);
        chartContainer.setPadding(new Insets(20));

        PageTitle title = new PageTitle("Analyze Products");

        chart.setLabelsVisible(true);
        chart.setLabelLineLength(10);
        chart.setStyle("-fx-text-fill: white");
        this.getPageContent().getChildren().addAll(title, chartContainer);
        chart.setMinSize(Theme.getScreenWidth() * 0.3, Theme.getScreenWidth() * 0.3);
        chart.setMaxWidth(Theme.getScreenWidth() * 0.4);
        super.load();
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
