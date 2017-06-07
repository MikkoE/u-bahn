/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u.bahn;

import helper.StationList;
import defines.Station;
import helper.Train;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author mikko
 */
public class UBahn extends Application {
    
    public StationList stationList = new StationList();
    Train train1;
    
    private Integer i = 0;
    
    private Timeline timeline;
    private AnimationTimer timer;
    
    @Override
    public void start(Stage primaryStage) {        
        Station startStation = new Station("1", 0, 10, false);
        
        initStations(startStation);
        initTrains(startStation);

         
        //create a circle with effect
        final Circle circle = new Circle(20,  Color.rgb(156,216,255));
        circle.setEffect(new Lighting());
        //create a text inside a circle
        final Text text = new Text (i.toString());
        text.setStroke(Color.BLACK);
        //create a layout for circle with text inside
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(30);
        stack.setLayoutY(30);
 
        //create a timeline for moving the circle
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
 
        
        
        //Canvas canvas = new Canvas(700, 500);
        //GraphicsContext gc = canvas.getGraphicsContext2D();
        //drawing(gc);
            
        StackPane root = new StackPane();
        //root.getChildren().add(canvas);
        root.getChildren().add(stack);
        
        root.setTranslateX(80);
        root.setTranslateY(80);
        
        //Scene scene = new Scene(root, 1920, 1080);
        Scene scene = new Scene(root, 750, 550);
        
        primaryStage.setTitle("U-Bahn Linie");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                //System.err.println(train1.getPosition());
                text.setText(i.toString());
                //i++;
            }
 
        };
        
         //create a keyValue with factory: scaling the circle 2times
        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 2);
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 2);
 
        //create a keyFrame, the keyValue is reached at time 2s
        Duration duration = Duration.millis(2000);
        //one can add a specific action when the keyframe is reached
        EventHandler onFinished = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                 stack.setTranslateX(train1.getPosition().x);
                 //reset counter
                 //i = 0;
            }
        };
 
        KeyFrame keyFrame = new KeyFrame(duration, onFinished , keyValueX, keyValueY);
 
        //add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);
 
        timeline.play();
        timer.start();
    
    }

    private void initTrains(Station startStation) {
        train1 = new Train(0, startStation, stationList);
        new Thread(train1).start();
        //new Thread(new Train(1, startStation, stationList)).start();        
        
    }

    private void initStations(Station startStation) {
        Station s1 = startStation;
        stationList.addStation(s1);
        
        Station s2 = new Station("2", 1, 7, true);
        stationList.addStation(s2);
        
        Station s3 = new Station("3", 2, 9, false);
        stationList.addStation(s3);
        
        Station s4 = new Station("4", 3, 14, true);
        stationList.addStation(s4);
        
        Station s5 = new Station("5", 4, 10, false);
        stationList.addStation(s5);
        
        Station s6 = new Station("6", 5, 11, true);
        stationList.addStation(s6);
    }
    
    private void drawing(GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        gc.setFill(Color.RED);
        
        gc.strokeRoundRect(0, 50, 30, 30, 10, 10);
        gc.strokeRoundRect(43, 50, 30, 30, 10, 10);
        gc.strokeRoundRect(115, 50, 30, 30, 10, 10);
        gc.strokeRoundRect(297, 50, 30, 30, 10, 10);
        gc.strokeRoundRect(387, 50, 30, 30, 10, 10);
        gc.strokeRoundRect(497, 50, 30, 30, 10, 10);
        gc.strokeRoundRect(587, 50, 30, 30, 10, 10);
        
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        gc.strokeLine(train1.getPosition().x, 55, train1.getPosition().x+10, 55);
        
    }
    
    
    
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
