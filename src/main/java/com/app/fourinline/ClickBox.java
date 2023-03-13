package com.app.fourinline;

import javafx.scene.control.Button;

public class ClickBox extends Button {

    private int columnIndex;

    public ClickBox(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}
