package com.app.fourinline;

import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.net.URL;

public class GameGrid extends StackPane {

    private int RECT_WIDTH = 61;
    private int RECT_HEIGHT = 61;
    private int CIRCLE_X = 30;
    private int CIRCLE_Y = 30;
    private int RADIUS = 25;
    private ClickBox[] clickBoxes;
    private Pane backgroundLayer;


    public GameGrid() {
        // defining layers
        //this.setAlignment(Pos.CENTER);
        backgroundLayer = new Pane();   // color of holes and animation of tokens
        GridPane middleLayer1 = new GridPane();      // blue inverse clipped squares
        GridPane middleLayer2 = new GridPane();      // circle border inside square
        GridPane frontLayer = new GridPane();        // user interaction layer (clickable)

        // setting up back layer
        backgroundLayer.setMaxWidth(RECT_WIDTH * 7);
        backgroundLayer.setMaxHeight(RECT_WIDTH * 6);
        Rectangle bgClip = new Rectangle(RECT_WIDTH * 7, RECT_WIDTH * 6);
        bgClip.setArcHeight(25);
        bgClip.setArcWidth(25);
        backgroundLayer.setClip(bgClip);


        middleLayer1.setAlignment(Pos.CENTER);
        middleLayer2.setAlignment(Pos.CENTER);
        middleLayer2.setVgap(10);
        middleLayer2.setHgap(10);

        backgroundLayer.setStyle("-fx-background-color: #2E2E2E");



        // middleLayer1
        Shape clip = new Circle(CIRCLE_X, CIRCLE_Y, RADIUS);
        Shape[][] rects = new Shape[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                rects[i][j] = new Rectangle(RECT_WIDTH,RECT_HEIGHT, Color.rgb(0,70,150));
                setInverseClip(rects[i][j], clip);
                middleLayer1.add(rects[i][j], j, i);
            }
        }

        // middleLayer2
        Circle[][] inBorders = new Circle[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                inBorders[i][j] = new Circle(CIRCLE_X, CIRCLE_Y, RADIUS, Color.TRANSPARENT);
                inBorders[i][j].setStroke(Color.rgb(25,5,140));
                middleLayer2.add(inBorders[i][j], j, i);
            }
        }

        // clickables
        clickBoxes = new ClickBox[7];
        for (int i = 0; i < 7; i++) {
            clickBoxes[i] = new ClickBox(i);
            getChildren().add(clickBoxes[i]);
            clickBoxes[i].setMaxHeight(RECT_HEIGHT * 6);
            clickBoxes[i].setMinHeight(RECT_HEIGHT * 6);
            clickBoxes[i].setMaxWidth(RECT_WIDTH);
            clickBoxes[i].setMinWidth(RECT_WIDTH);
            clickBoxes[i].setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            /*
            clickBoxes[i].setOnAction(e -> {
                ClickBox cb = (ClickBox) e.getSource();
                int x = cb.getColumnIndex() + 1;
                System.out.println("Clickable at: " + x);
                // yPosMult needs to be in range [1,6]
                animate(x, x);
            });

             */
            frontLayer.add(clickBoxes[i], i, 0, 1, 6);

        }



        getChildren().add(backgroundLayer);
        getChildren().add(middleLayer1);
        getChildren().add(middleLayer2);
        getChildren().add(frontLayer);
    }


    public void animate(int xPosMult, int yPosMult, char color) {
        synchronized (this) {
            int duration = 100 * yPosMult;
            int spawnPosX = (30 + (60 * (xPosMult-1))) + xPosMult;
            int spawnPosY = -20;       // for now just fixed value
            int finalPosY = ((30 + (60 * (yPosMult-1))) + yPosMult) + Math.abs(spawnPosY);

            URL imageUrl = null;
            if (color == 'r') imageUrl = getClass().getResource("/images/red-token.png");
            else imageUrl = getClass().getResource("/images/yellow-token.png");
            Image image = new Image(imageUrl.toExternalForm());
            ImageView imageView = new ImageView(image);
            Circle c = new Circle(RADIUS, Color.RED);
            c.setFill(new ImagePattern(imageView.getImage()));

            c.setCenterX(spawnPosX);
            c.setCenterY(spawnPosY);
            backgroundLayer.getChildren().add(c);

            TranslateTransition transition = new TranslateTransition();
            transition.setNode(c);
            transition.setDuration(Duration.millis(duration));
            //transition.setCycleCount(TranslateTransition.INDEFINITE);
            //transition.setAutoReverse(true);
            // every time when player plays a move value for final position of token
            // gets passed to a function that animates that token till final position
            // formula: 30+60+60+60+60+60 + 6
            transition.setByY(finalPosY);
            transition.play();
        }
    }


    private void setInverseClip(final Node node, final Shape clip ) {
        final Rectangle inverse = new Rectangle();
        inverse.setWidth( node.getLayoutBounds().getWidth() );
        inverse.setHeight( node.getLayoutBounds().getHeight() );
        node.setClip( Shape.subtract( inverse, clip ) );
    }




    public ClickBox[] getClickBoxes() {
        return clickBoxes;
    }

    public void setClickBoxes(ClickBox[] clickBoxes) {
        this.clickBoxes = clickBoxes;
    }

}
