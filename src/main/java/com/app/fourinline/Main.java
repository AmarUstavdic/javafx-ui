package com.app.fourinline;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main extends Application {

    MainView mainView;
    GameView gameView;
    Controller controller;

    @Override
    public void start(Stage stage) {

        /*
        final StackPane root = new StackPane();
        final ProgressBar bar = new ProgressBar();
        final Image image = new Image( "https://farm8.staticflickr.com/7036/6952932649_3fc1cfeb8a_o_d.jpg", true );
        final ImageView imageView = new ImageView( image );
        final Text clip = new Text( "JavaFx" );
        final Scene scene = new Scene( root );

        root.setStyle( "-fx-background: pink;" );
        root.setEffect( new InnerShadow() );

        bar.prefWidthProperty().bind( root.widthProperty() );
        bar.visibleProperty().bind( Bindings.notEqual( 1, image.progressProperty() ) );
        bar.progressProperty().bind( image.progressProperty() );

        imageView.setFitWidth( 800 );
        imageView.setFitHeight( 600 );



        Shape back = new Rectangle(100,100,Color.RED);
        Shape s = new Circle(10,10,10);


        //clip.setFont( Font.font( 144.0 ) );
        //clip.setX( 400 - clip.getBoundsInLocal().getWidth() / 2 );
        //clip.setY( 400 - clip.getBoundsInLocal().getHeight() / 2 );

        //setInverseClip( imageView, clip );
        setInverseClip(back, s);

        //root.getChildren().add( bar );
        //root.getChildren().add( imageView );
        root.getChildren().add( back );



        primaryStage.setScene( scene );
        primaryStage.show();

         */




        StackPane root = new StackPane();
        mainView = new MainView();
        gameView = new GameView();

        // ui controller
        controller = new Controller(mainView, gameView);
        mainView.setController(controller);
        gameView.setController(controller);

        root.getChildren().addAll(mainView, gameView);

        Rectangle roundedRect = new Rectangle(710, 550);
        roundedRect.setArcWidth(30);
        roundedRect.setArcHeight(30);
        root.setClip(roundedRect);

        mainView.setVisible(true);
        gameView.setVisible(false);

        Scene scene = new Scene(root, 710,550, Color.TRANSPARENT);

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();



    }


    private void setInverseClip(final Node node, final Shape clip ) {
        final Rectangle inverse = new Rectangle();
        inverse.setWidth( node.getLayoutBounds().getWidth() );
        inverse.setHeight( node.getLayoutBounds().getHeight() );
        node.setClip( Shape.subtract( inverse, clip ) );
    }






    public static void main(String[] args) {
        launch(args);
    }
}