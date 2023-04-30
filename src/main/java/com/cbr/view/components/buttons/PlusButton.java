package com.cbr.view.components.buttons;

import com.cbr.view.theme.Theme;
import javafx.scene.control.Button;

public class PlusButton extends CircleImageButton {
    public PlusButton(double radius) {
        super(radius, "file:assets/icons/plus.png", Theme.getPrimaryLight());
    }
}
