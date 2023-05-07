package com.cbr;

import com.cbr.models.Customer;
import com.cbr.models.FixedInvoice;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineBarChartPlugin extends BasePlugin implements Plugin {

    public LineBarChartPlugin() {
        super();
        this.setPageName("Analyze Clients & Sales");
    }
    public String getName(){
        return "com.cbr.LineBarChartPlugin";
    }
    public void load() {
        PageTitle title = new PageTitle("Analyze Clients & Sales");
        HBox chartsContainer = new HBox();

        /* Clients Count Bar Chart */
        VBox barChartContainer = new VBox();
        PageTitle barChartTitle = new PageTitle("Client Count by Type");
        barChartContainer.setStyle("-fx-background-color: " + Theme.getPrimaryLight() + ";-fx-background-radius: 12");
        barChartContainer.setMaxWidth(Theme.getScreenWidth() * 0.5);

        NumberAxis barXAxis = new NumberAxis();
        CategoryAxis barYAxis = new CategoryAxis();
        barXAxis.setTickUnit(1);
        barXAxis.setTickMarkVisible(false);
        barXAxis.setMinorTickVisible(false);
        barXAxis.setStyle("-fx-tick-label-fill: white");
        barXAxis.setTickLabelRotation(90);
        barYAxis.setStyle("-fx-tick-label-fill: white");

        BarChart<Number,String> barChart = new BarChart<>(barXAxis, barYAxis);
        barChart.setMaxWidth(Theme.getScreenWidth() * 0.45);
        barChartContainer.getChildren().addAll(barChartTitle, barChart);
        barChartContainer.setAlignment(Pos.CENTER);
        barChartContainer.setPadding(new Insets(10));

        /* Sales Line Chart */
        VBox lineChartContainer = new VBox();
        PageTitle lineChartTitle = new PageTitle("Sales Revenue Chart");
        lineChartContainer.setStyle("-fx-background-color: " + Theme.getPrimaryLight() + ";-fx-background-radius: 12");
        lineChartContainer.setMaxWidth(Theme.getScreenWidth() * 0.5);

        CategoryAxis lineXAxis = new CategoryAxis();
        NumberAxis lineYAxis = new NumberAxis();
        lineXAxis.setStyle("-fx-tick-label-fill: white");
        lineXAxis.setTickLabelRotation(90);
        lineYAxis.setStyle("-fx-tick-label-fill: white");

        LineChart<String,Number> lineChart = new LineChart<>(lineXAxis, lineYAxis);
        lineChart.setMaxWidth(Theme.getScreenWidth() * 0.45);
        lineChartContainer.getChildren().addAll(lineChartTitle, lineChart);
        lineChartContainer.setAlignment(Pos.CENTER);
        lineChartContainer.setPadding(new Insets(10));


        /* Charts Container Styling */
        chartsContainer.getChildren().addAll(barChartContainer, lineChartContainer);
        chartsContainer.setAlignment(Pos.CENTER);
        chartsContainer.setSpacing(20);


        this.getPageContent().getChildren().addAll(title, chartsContainer);
        super.load();

        Thread barTask = new Thread(() -> {
            while (true) {
                /* Bar Chart data */
                List<Customer> customerList = App.getDataStore().getCustomers();
                List<Member> memberList = App.getDataStore().getMembers();
                List<VIP> vipList = App.getDataStore().getVips();

                Platform.runLater(() -> {
                    ObservableList<XYChart.Series<Number, String>> barChartData = FXCollections.observableArrayList();

                    /* Bar Chart Data */
                    XYChart.Series<Number, String> customerSeries = new XYChart.Series<>();
                    customerSeries.setName("Customers");
                    customerSeries.getData().add(new XYChart.Data<>(customerList.size(), "Customer"));

                    XYChart.Series<Number, String> memberSeries = new XYChart.Series<>();
                    memberSeries.setName("Members");
                    memberSeries.getData().add(new XYChart.Data<>(memberList.size(), "Member"));

                    XYChart.Series<Number, String> vipSeries = new XYChart.Series<>();
                    vipSeries.setName("VIPs");
                    vipSeries.getData().add(new XYChart.Data<>(vipList.size(), "VIP"));

                    barChartData.addAll(customerSeries, memberSeries, vipSeries);

                    // Update the UI components here
                    barChart.setData(barChartData);
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
        barTask.start();

        Thread lineTask = new Thread(() -> {
            while(true) {
                /* Line Chart data */
                List<FixedInvoice> fixedInvoices = App.getDataStore().getInvoices().getDataList();
                Map<LocalDate, List<FixedInvoice>> datedInvoicesMap = new HashMap<>();
                for (FixedInvoice invoice : fixedInvoices) {
                    LocalDate date = invoice.getCreatedAt().toLocalDate();
                    if (datedInvoicesMap.containsKey(date)) {
                        datedInvoicesMap.get(date).add(invoice);
                    } else {
                        List<FixedInvoice> invoiceList = new ArrayList<>();
                        invoiceList.add(invoice);
                        datedInvoicesMap.put(date, invoiceList);
                    }
                }

                Platform.runLater(() -> {
                    ObservableList<XYChart.Series<String, Number>> lineChartData = FXCollections.observableArrayList();

                    XYChart.Series<String, Number> salesSeries = new XYChart.Series<>();
                    salesSeries.setName("Sales Revenue");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
                    datedInvoicesMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {      // key: date, value: list of invoice
                        salesSeries.getData().add(new XYChart.Data<>(entry.getKey().format(formatter), entry.getValue().stream().mapToDouble(FixedInvoice::revenue).sum()));
                    });
                    lineChartData.add(salesSeries);

                    // Update line chart data
                    lineChart.setData(lineChartData);
                });
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        lineTask.start();
    }
}
