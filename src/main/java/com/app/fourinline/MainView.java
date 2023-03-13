package com.app.fourinline;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;

public class MainView extends VBox {

    private Button[] btn;
    private Controller controller;

    public MainView() {
        URL cssUrl = getClass().getResource("/styles/mainMenu.css");
        this.getStylesheets().add(Objects.requireNonNull(cssUrl).toExternalForm());


        Label gameTitle = new Label("Four in Line");
        Pane spacer = new Pane();
        btn = new Button[4];
        btn[0] = new Button();
        btn[1] = new Button();
        btn[2] = new Button();
        btn[3] = new Button();

        Label[] btnLabel = new Label[4];
        btnLabel[0] = new Label("New Game");
        btnLabel[1] = new Label("Play Computer");
        btnLabel[2] = new Label("Settings");
        btnLabel[3] = new Label("Quit Game");

        
        // css classes
        this.getStyleClass().add("menu-vbox");
        gameTitle.getStyleClass().add("main-title");
        spacer.getStyleClass().add("main-spacer");
        for (Button b : btn) {
            b.getStyleClass().add("main-scene-btn");
        }
        btn[0].getStyleClass().add("top-btn");
        btn[3].getStyleClass().add("main-quit-btn");

        // adding labels to btn
        for (int i = 0; i < btnLabel.length; i++) {
            btnLabel[i].getStyleClass().add("btn-label");
            btn[i].setGraphic(btnLabel[i]);
        }




        getChildren().setAll(gameTitle, spacer, btn[0], btn[1], btn[2], btn[3]);
    }


    public Button[] getBtn() {
        return btn;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
