package com.app.fourinline;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Objects;

public class PlayerBar extends FlowPane {

    private Pane imageFrame;
    private BorderPane infoBox;
    private Label playerName;
    private Pane playerColor;
    private Pane clock;
    private Pane[] connBars;
    private FlowPane connectionStrength;


    public PlayerBar() {
        URL cssUrl = getClass().getResource("/styles/playerBar.css");
        this.getStylesheets().add(Objects.requireNonNull(cssUrl).toExternalForm());

        setPadding(new Insets(10));
        setHgap(10);


        imageFrame = new Pane();
        infoBox = new BorderPane();
        playerName = new Label("Guest");
        playerColor = new Pane();
        clock = new Pane();
        connectionStrength = new FlowPane();
        connectionStrength.setHgap(3);       // gap between conn strength bars

        connBars = new Pane[4];
        for (int i = 0; i < 4; i++) {
            connBars[i] = new Pane();
            connBars[i].getStyleClass().add("conn-bar");
        }
        connectionStrength.getChildren().addAll(connBars);


        // info box holds name and network conn strength info
        infoBox.setTop(connectionStrength);
        infoBox.setBottom(playerName);
        setMargin(playerName, new Insets(10,0,0,0));


        // setting css classes
        imageFrame.getStyleClass().add("player-img-frame");
        infoBox.getStyleClass().add("info-box");
        connectionStrength.getStyleClass().add("conn-bars-frame");
        playerName.getStyleClass().add("player-name");
        playerColor.getStyleClass().add("player-token-holder");
        clock.getStyleClass().add("clock");




        getChildren().addAll(imageFrame, infoBox, playerColor, clock);
    }









    public Pane getImageFrame() {
        return imageFrame;
    }

    public void setImageFrame(Pane imageFrame) {
        this.imageFrame = imageFrame;
    }

    public BorderPane getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(BorderPane infoBox) {
        this.infoBox = infoBox;
    }

    public Label getPlayerName() {
        return playerName;
    }

    public void setPlayerName(Label playerName) {
        this.playerName = playerName;
    }

    public Pane getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(Pane playerColor) {
        this.playerColor = playerColor;
    }

    public Pane getClock() {
        return clock;
    }

    public void setClock(Pane clock) {
        this.clock = clock;
    }

    public Pane[] getConnBars() {
        return connBars;
    }

    public void setConnBars(Pane[] connBars) {
        this.connBars = connBars;
    }

    public FlowPane getConnectionStrength() {
        return connectionStrength;
    }

    public void setConnectionStrength(FlowPane connectionStrength) {
        this.connectionStrength = connectionStrength;
    }
}
