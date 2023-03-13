package com.app.fourinline;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;

public class GameView extends BorderPane {

    private PlayerBar localp;
    private PlayerBar opponent;
    private GameGrid gameGrid;
    private BorderPane leftSide;
    private BorderPane rightSide;
    private Controller controller;

    public GameView() {
        URL cssUrl = getClass().getResource("/styles/gameView.css");
        this.getStylesheets().add(Objects.requireNonNull(cssUrl).toExternalForm());
        getStyleClass().add("game-view");

        // left side of game view
        leftSide = new BorderPane();
        localp = new PlayerBar();
        opponent = new PlayerBar();
        gameGrid = new GameGrid();
        leftSide.getStyleClass().add("left-side-container");
        localp.getStyleClass().add("player-bar");
        opponent.getStyleClass().add("player-bar");
        gameGrid.getStyleClass().add("game-grid");

        leftSide.setTop(opponent);
        leftSide.setCenter(gameGrid);
        leftSide.setBottom(localp);

        setMargin(opponent, new Insets(10));
        setMargin(localp, new Insets(10));



        // right side of game view
        rightSide = new BorderPane();
        rightSide.getStyleClass().add("right-side-container");
        // only temporary parts
        Pane mainControls = new Pane();
        FlowPane secondaryControls = new FlowPane();
        Button abortBtn = new Button("Abort");
        Button reportBtn = new Button("Report");
        Pane chat = new Pane();

        secondaryControls.getChildren().addAll(abortBtn, reportBtn);
        secondaryControls.setHgap(10);


        rightSide.setTop(mainControls);
        rightSide.setCenter(secondaryControls);
        rightSide.setBottom(chat);

        setMargin(mainControls, new Insets(10,10,0,0));
        setMargin(secondaryControls, new Insets(10,10,0,0));
        setMargin(chat, new Insets(10,10,10,0));


        rightSide.getStyleClass().add("left-side-container");
        mainControls.getStyleClass().add("main-controls");
        secondaryControls.getStyleClass().add("secondary-controls");
        abortBtn.getStyleClass().add("abort-btn");
        reportBtn.getStyleClass().add("report-btn");
        chat.getStyleClass().add("chat");


        setRight(rightSide);
        setLeft(leftSide);
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public PlayerBar getLocalp() {
        return localp;
    }

    public void setLocalp(PlayerBar localp) {
        this.localp = localp;
    }

    public PlayerBar getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerBar opponent) {
        this.opponent = opponent;
    }

    public GameGrid getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public BorderPane getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(BorderPane leftSide) {
        this.leftSide = leftSide;
    }

    public BorderPane getRightSide() {
        return rightSide;
    }

    public void setRightSide(BorderPane rightSide) {
        this.rightSide = rightSide;
    }

    public Controller getController() {
        return controller;
    }
}
