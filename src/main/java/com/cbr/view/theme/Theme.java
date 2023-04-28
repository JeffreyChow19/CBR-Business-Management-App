package com.cbr.view.theme;

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
    private static Font bodyFont = Font.font("Ubuntu", FontWeight.NORMAL,16.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font bodyMediumFont = Font.font("Ubuntu", FontWeight.MEDIUM,24.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font heading1Font = Font.font("Ubuntu", FontWeight.BOLD,24.0/1920 * Theme.getScreenWidth());
    @Getter
    private static Font heading2Font = Font.font("Ubuntu", FontWeight.BOLD,36.0/1920 * Theme.getScreenWidth());
}
