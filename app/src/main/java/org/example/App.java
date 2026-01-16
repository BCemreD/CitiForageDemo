package org.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import yahoofinance.YahooFinance;
import yahoofinance.Stock;


public class App extends Application {

    // The URL for the API
    private static final String YAHOO_FINANCE_API = "https://finance.yahoo.com/quote/";

    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private int timeTick = 0;
    private final String symbol = "^DJI";

    @Override
    public void start(Stage stage) {
        stage.setTitle("CityBank - Dow Jones Monitor");

        // 3: X axis time, Y axis price
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (Ticks)");
        yAxis.setLabel("Stock Price");
        yAxis.setForceZeroInRange(false);

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        series.setName("Dow Jones Industrial Average");
        lineChart.getData().add(series);

        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();


        startDataStream();
    }

    private void startDataStream() {
        // Yahoo settings
        System.setProperty("yahoofinance.baseurl.quotes", "https://query1.finance.yahoo.com/v7/finance/quote");

        //to access Yahoo finance
        System.setProperty("http.agent", "Mozilla/5.0");

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();


        executor.scheduleAtFixedRate(() -> {
            try {
                Stock stock = YahooFinance.get(symbol);
                BigDecimal price = stock.getQuote().getPrice();

                // Update graph
                Platform.runLater(() -> {
                    series.getData().add(new XYChart.Data<>(timeTick++, price));
                    System.out.println("New Price: " + price);
                });

            } catch (IOException e) {
                System.out.println("Connection error, using mock data...");

                BigDecimal mockPrice = new BigDecimal(38000 + Math.random() * 100);
                Platform.runLater(() -> {
                    series.getData().add(new XYChart.Data<>(timeTick++, mockPrice));
                });
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
    public static void main(String[] args) {


        launch(args);
    }
}
class Main {
    public static void main(String[] args) {
        App.main(args);
    }
}
