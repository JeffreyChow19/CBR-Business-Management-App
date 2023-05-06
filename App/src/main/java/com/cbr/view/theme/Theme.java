package com.cbr.view.theme;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import lombok.Getter;

@Getter
public class Theme {
    @Getter
    private static Double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    @Getter
    private static Double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
    @Getter
    private static String primaryDark = "#211A38";
    @Getter
    private static String primaryBase = "#282043";
    @Getter
    private static String primaryLight = "#6D68EE";
    @Getter
    private static String accentGreen = "#55F184";
    @Getter
    private static String accentRed = "#E4403F";
    @Getter
    private static String accentBlue = "#498CDA";
    @Getter
    private static String secondaryBase = "#332E59";
    @Getter
    private static String secondaryLight = "#494178";
    @Getter
    private static Font captionFont = Font.font("Ubuntu", FontWeight.NORMAL,14.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font bodyFont = Font.font("Ubuntu", FontWeight.NORMAL,16.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font bodyMediumFont = Font.font("Ubuntu", FontWeight.MEDIUM,24.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font bodyBoldFont = Font.font("Ubuntu", FontWeight.BOLD,24.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font heading1Font = Font.font("Ubuntu", FontWeight.BOLD,36.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font heading2Font = Font.font("Ubuntu", FontWeight.BOLD,24.0/1920 * Theme.getScreenWidth());
    public static String darkenColor(String hexColor, double percentage) {
        Color color = Color.web(hexColor);
        double hue = color.getHue();
        double saturation = color.getSaturation();
        double brightness = color.getBrightness() * percentage; // reduce brightness by (100-percentage)%
        Color darkerColor = Color.hsb(hue, saturation, brightness);
        String darkerColorString = String.format("#%02X%02X%02X",
                (int) (darkerColor.getRed() * 255),
                (int) (darkerColor.getGreen() * 255),
                (int) (darkerColor.getBlue() * 255));
        return darkerColorString;
    }
}
