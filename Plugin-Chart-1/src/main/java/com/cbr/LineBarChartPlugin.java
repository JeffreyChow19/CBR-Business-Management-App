package com.cbr;

import com.cbr.models.Customer;
import com.cbr.models.Member;
import com.cbr.models.VIP;
import com.cbr.plugin.Plugin;
import com.cbr.view.theme.Theme;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LineBarChartPlugin implements Plugin{
    @Setter
    @Getter
    private Boolean status;

    public LineBarChartPlugin() {

    }
    public String getName(){
        return "com.cbr.LineBarChartPlugin";
    }
    public void load() {
        VBox container = new VBox();

        /* Clients Count Bar Chart */
        VBox barChartContainer = new VBox();
        barChartContainer.setStyle("-fx-background-color: " + Theme.getPrimaryDark() + ";-fx-background-radius: 12");
        barChartContainer.setMaxWidth(Theme.getScreenWidth() * 0.5);

        List<Customer> customerList = App.getDataStore().getCustomers();
        List<Member> memberList = App.getDataStore().getMembers();
        List<VIP> vipList = App.getDataStore().getVips();

        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickUnit(1);
        xAxis.setTickMarkVisible(false);
        xAxis.setMinorTickVisible(false);

        CategoryAxis yAxis = new CategoryAxis();
        BarChart<Number,String> clientBarChart =
                new BarChart<>(xAxis,yAxis);
        clientBarChart.setTitle("Client Count by Type");
        xAxis.setStyle("-fx-tick-label-fill: white");
        xAxis.setTickLabelRotation(90);
        yAxis.setStyle("-fx-tick-label-fill: white");

        XYChart.Series customerSeries = new XYChart.Series();
        customerSeries.setName("Customers");
        customerSeries.getData().add(new XYChart.Data<>(customerList.size(), "Customer"));

        XYChart.Series memberSeries = new XYChart.Series();
        memberSeries.setName("Members");
        memberSeries.getData().add(new XYChart.Data<>(memberList.size(), "Member"));

        XYChart.Series vipSeries = new XYChart.Series();
        vipSeries.setName("VIPs");
        vipSeries.getData().add(new XYChart.Data<>(vipList.size(), "VIP"));

        clientBarChart.getData().addAll(customerSeries, memberSeries, vipSeries);
        clientBarChart.setStyle("-fx-title-text-fill: white;");
        clientBarChart.setMaxWidth(Theme.getScreenWidth() * 0.4);
        barChartContainer.getChildren().add(clientBarChart);
        barChartContainer.setAlignment(Pos.CENTER);
        barChartContainer.setPadding(new Insets(20));

        container.getChildren().add(barChartContainer);
        container.setAlignment(Pos.CENTER);

        // TODO: integrate plugin
    }
}
