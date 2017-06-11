/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u.bahn;

import helper.StationList;
import defines.Station;
import helper.Train;
import helper.TrainImage;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author mikko
 */
public class UBahn extends Application {
    
    public static final int HEIGHT = 750;
    public static final int WIDTH = 550;
    
    public StationList stationList = new StationList();
    Train train1;
    
    public Image image = new Image("image/u-bahnFront.png");
    public TrainImage v_image = new TrainImage(image);
    
    @Override
    public void start(Stage primaryStage) {  
                
        Station startStation = new Station("1", 0, 10, false);
        initStations(startStation);
        initTrains(startStation);
        
        
        v_image.setImage(image);
        v_image.setFitWidth(50);
        v_image.setPreserveRatio(true);
        v_image.setSmooth(true);
        v_image.setCache(true);
        v_image.setLayoutX(300);
        v_image.setLayoutY(300);
        
        /*Path p = new Path();
        p.getElements().add(new MoveTo(train1.getPosition().x, 0));
        p.getElements().add(new LineTo(train1.getPosition().x + train1.getCurrentStation().getDistance(), 0));
        PathTransition pt = new PathTransition(Duration.millis(10000), p);
        pt.setNode(v_image);
        
        DoubleProperty xValue = new SimpleDoubleProperty();
        xValue.bind(v_image.translateXProperty());
        xValue.addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
             //   System.out.println((double) t1);
            }
        });*/
        


 
        StackPane root = new StackPane();
        
        HBox box = new HBox();
        box.getChildren().add(v_image);
        root.getChildren().add(v_image);
        
        root.setTranslateX(-HEIGHT);
        root.setTranslateY(-WIDTH + (image.getWidth()/4));
        
        //Scene scene = new Scene(root, 1920, 1080);
        Scene scene = new Scene(root, HEIGHT, WIDTH);
        
        primaryStage.setTitle("U-Bahn Linie");
        //pt.play();
        primaryStage.setScene(scene);
        primaryStage.show();    
    }

    private void initTrains(Station startStation) {
        //train1 = new Train(0, startStation, stationList);
        //new Thread(train1).start();
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
