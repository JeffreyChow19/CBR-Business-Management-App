package com.cbr.view.components.clockwidget;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.String.format;

public class ClockWidget extends VBox {
    private static class TimeClock extends Label {
        public TimeClock() {
            super();
            startTimeClock();
        }

        public void startTimeClock() {
            Thread clockThread = new Thread(() -> {
                while (true) {
                    Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int minute = cal.get(Calendar.MINUTE);
                    String time = format("%02d", hour) + ":" + format("%02d", minute);
                    Platform.runLater(() -> setText(time));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            clockThread.start();
        }
    }

    private static class DateClock extends Label {
        public DateClock() {
            super();
            startTimeClock();
        }

        public void startTimeClock() {
            Thread clockThread = new Thread(() -> {
                while (true) {
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH);
                    String formattedDate = sdf.format(cal.getTime());
                    Platform.runLater(() -> setText(formattedDate));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            clockThread.start();
        }
    }
    public ClockWidget() {
        super();
        this.getChildren().add(new DateClock());
        this.getChildren().add(new TimeClock());
    }
}