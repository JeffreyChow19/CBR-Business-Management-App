package com.cbr;

import com.cbr.models.Customer;
import com.cbr.models.Member;
import com.cbr.models.VIP;
import com.cbr.plugin.Plugin;
import com.cbr.view.components.labels.PageTitle;
import com.cbr.view.theme.Theme;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LineBarChartPlugin extends BasePlugin implements Plugin {
    @Setter
    @Getter
    private Boolean status;

    public LineBarChartPlugin() {
        super();
        this.setPageName("Analyze Clients & Sales");
        this.status = false;
    }
    public String getName(){
        return "com.cbr.LineBarChartPlugin";
    }
    public void load() {
        if (!this.status) {
            PageTitle title = new PageTitle("Analyze Clients & Sales");
            VBox chartsContainer = new VBox();

            /* Clients Count Bar Chart */
            VBox barChartContainer = new VBox();
            PageTitle barChartTitle = new PageTitle("Client Count by Type");
            barChartContainer.setStyle("-fx-background-color: " + Theme.getPrimaryLight() + ";-fx-background-radius: 12");
            barChartContainer.setMaxWidth(Theme.getScreenWidth() * 0.5);

            NumberAxis xAxis = new NumberAxis();
            CategoryAxis yAxis = new CategoryAxis();
            xAxis.setTickUnit(1);
            xAxis.setTickMarkVisible(false);
            xAxis.setMinorTickVisible(false);
            xAxis.setStyle("-fx-tick-label-fill: white");
            xAxis.setTickLabelRotation(90);
            yAxis.setStyle("-fx-tick-label-fill: white");

            BarChart<Number,String> barChart = new BarChart<>(xAxis,yAxis);
            barChart.setMaxWidth(Theme.getScreenWidth() * 0.4);
            barChartContainer.getChildren().addAll(barChartTitle, barChart);
            barChartContainer.setAlignment(Pos.CENTER);
            barChartContainer.setPadding(new Insets(20));

            /* TODO: line chart*/




            /* Charts Container Styling */
            chartsContainer.getChildren().add(barChartContainer);
            // Todo: add line chart to container
            chartsContainer.setAlignment(Pos.CENTER);

            this.getPageContent().getChildren().addAll(title, chartsContainer);
            super.load();
            this.status = true;

            Thread task = new Thread(() -> {
                while (true) {
                    /* Bar Chart data */
                    List<Customer> customerList = App.getDataStore().getCustomers();
                    List<Member> memberList = App.getDataStore().getMembers();
                    List<VIP> vipList = App.getDataStore().getVips();

                    Platform.runLater(() -> {
                        ObservableList<XYChart.Series<Number, String>> barChartData = FXCollections.observableArrayList();

                        /* Bar Chart Data */
                        XYChart.Series customerSeries = new XYChart.Series();
                        customerSeries.setName("Customers");
                        customerSeries.getData().add(new XYChart.Data<>(customerList.size(), "Customer"));

                        XYChart.Series memberSeries = new XYChart.Series();
                        memberSeries.setName("Members");
                        memberSeries.getData().add(new XYChart.Data<>(memberList.size(), "Member"));

                        XYChart.Series vipSeries = new XYChart.Series();
                        vipSeries.setName("VIPs");
                        vipSeries.getData().add(new XYChart.Data<>(vipList.size(), "VIP"));

                        barChartData.addAll(customerSeries, memberSeries, vipSeries);

                        // Update the UI components here
                        if(barChartData!=null){
                            barChart.setData(barChartData);
                        }
                    });
//
//
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            task.start();
        }


        // TODO: integrate plugin
    }
}
