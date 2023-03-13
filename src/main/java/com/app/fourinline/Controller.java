package com.app.fourinline;

import game.GameLogic;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Controller {

    private MainView mainView;
    private GameView gameView;


    public Controller(MainView mainView, GameView gameView) {
        this.mainView = mainView;
        this.gameView = gameView;


        // main menu controls
        // Play Game
        mainView.getBtn()[0].setOnAction(e -> {
            mainView.setVisible(!mainView.isVisible());
            gameView.setVisible(!gameView.isVisible());
        });


        // Play Computer
        mainView.getBtn()[1].setOnAction(e -> {
            mainView.setVisible(!mainView.isVisible());
            gameView.setVisible(!gameView.isVisible());

            // setting token color for players
            GameLogic.init();
            if (GameLogic.getLocalp() == 'r') {
                gameView.getLocalp().getPlayerColor().setId("red-token");
                gameView.getOpponent().getPlayerColor().setId("yellow-token");
            } else {
                gameView.getOpponent().getPlayerColor().setId("red-token");
                gameView.getLocalp().getPlayerColor().setId("yellow-token");
            }





            // disabling clickable if not my turn
            /*
            if (GameLogic.getNtp() != GameLogic.getLocalp()) {
                ClickBox[] clickable = gameView.getGameGrid().getClickBoxes();
                for (ClickBox c : clickable) {
                    c.setDisable(true);
                }
            }

             */
        });






        ClickBox[] clickBoxes = gameView.getGameGrid().getClickBoxes();
        for (int i = 0; i < clickBoxes.length; i++) {
            clickBoxes[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    ClickBox cb = (ClickBox) e.getSource();
                    int ci = cb.getColumnIndex();
                    GameGrid g = gameView.getGameGrid();
                    GameLogic.move(GameLogic.getNtp(), ci, g);
                    GameLogic.setLastMove(String.valueOf(GameLogic.getNtp()) + ci);
                    GameLogic.updateNtp();
                    GameLogic.print();




                    Random random = new Random();
                    int i = random.nextInt(7);
                    GameGrid gg = gameView.getGameGrid();
                    Timer timer = new Timer();
                    int delay = (random.nextInt(3) + 1) * 1000;
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                GameLogic.move(GameLogic.getNtp(), i, gg);
                                GameLogic.setLastMove(String.valueOf(GameLogic.getNtp()) + i);
                                GameLogic.updateNtp();
                                GameLogic.print();
                            });
                        }
                    }, delay);


                }
            });
        }








    }

}
