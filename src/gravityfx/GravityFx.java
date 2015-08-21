/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityfx;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Johnogel
 */
public class GravityFx extends Application {
public ArrayList<CircleCelestial> celestials;
public Point2D origin, clickPoint;
public double canvasOriginX = 0;
public double canvasOriginY = 0;
    @Override
    public void start(Stage theStage) {
        
        
        Rectangle2D screen = Screen.getPrimary().getBounds();
        theStage.setTitle("Collect the Money Bags!");
        Canvas canvas = new Canvas(screen.getWidth(), screen.getHeight());
        Group root = new Group();
        Scene theScene = new Scene(root);
        
        theStage.setScene(theScene);
        theStage.setFullScreen(true);
        
        ScrollPane pane = new ScrollPane();
        pane.setPrefViewportWidth(theScene.getWidth());
        pane.setPrefViewportHeight(theScene.getHeight());
        pane.setFitToHeight(true);
        pane.setFitToWidth(true);
        pane.setPannable(true);
        
        root.getChildren().add(pane);
        pane.setContent(canvas);
        
        //root.getChildren().add(canvas);
        //Camera camera = new Camera();
        
        final double SCALE_DELTA = 1.1;
        
        
        origin = new Point2D(0,0);
        clickPoint = new Point2D(0,0);
        
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        celestials = new ArrayList<>();
       
       
        Gravity gravity = new Gravity(celestials, origin);
        
        for (int i = 0; i < 70; i++){
            celestials.add(new Planet(Math.random()*20+2 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1), origin));
            //celestials.add(new Planet(Math.random()*5+2 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1)));
            //celestials.add(new Planet(Math.random()*20,20,Math.random()*canvas.getWidth(),new Color(Math.rando)));
            celestials.get(celestials.size()-1).setVelocity(Math.random()*20-10, Math.random()*20-10);
            
            
        }
        
        //planets.add(new Planet(20, 100, 100, Color.BLUE));
        //planets.add(new Planet(50, canvas.getWidth()/2, canvas.getHeight()/2, Color.YELLOW));
        //planets.add(new Planet(50, canvas.getWidth()/2+900, canvas.getHeight()/2-500, Color.YELLOW));
        //planets.get(0).setVelocity(60000, 60000);
        //planets.add(new Planet(30, canvas.getWidth()/2+150, canvas.getHeight()/2+50, Color.AQUA));
       //planets.add(new Planet(20, 800,200,Color.BLUE));
        //planets.get(1).addVelocity(-30, -10);
//        planets.add(new Planet(20, 20, 20, Color.RED));
//        planets.add(new Planet(30, 40, 90, Color.BLUE));
        
        //for(Planet p:planets){
        //    p.addVelocity(Math.random()*100-50, Math.random()*100-50);
        //}
//        Point2D p1 = new Point2D(100,100);
//        Point2D p2 = new Point2D(80,100);
//        Point2D p3 = new Point2D(200,80);
//        
//        System.out.println(""+p2.angle(p3, p1));
      theScene.setOnKeyPressed((KeyEvent event) -> {
          event.consume();
          //System.out.println("Stufffff");
//                planets.removeAll(planets);
//                for (int i = 0; i < 150; i++){
//                    
//                    planets.add(new Planet(Math.random()*25+1 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1)));
//                    //planets.add(new Planet(Math.random()*5+2 ,Math.random()*canvas.getWidth(), Math.random()*canvas.getHeight(), new Color(Math.random(), Math.random(), Math.random(), 1)));
//                    //planets.add(new Planet(Math.random()*20,20,Math.random()*canvas.getWidth(),new Color(Math.rando)));
//                    planets.get(planets.size()-1).setVelocity(Math.random()*40-20, Math.random()*40-20);
//                if(event.getCharacter() == "R"){
//                    System.out.println("Stufffff");
//                    
//                    }
//                }
        });
        canvas.setOnMousePressed((MouseEvent event) -> {
            clickPoint = new Point2D(event.getX(), event.getY());
//                System.out.println("Origin Coordinates: ("+origin.getX()+", "+origin.getY()+"}");
//                System.out.println("ClickPoint Coordinates: ("+clickPoint.getX()+", "+clickPoint.getY()+"}");
        });
        canvas.setOnMouseDragged((MouseEvent event) -> {
            double dx = event.getX() - clickPoint.getX();
            double dy = event.getY() - clickPoint.getY();
            origin = new Point2D(dx+origin.getX(),  dy+origin.getY());
            clickPoint =  new Point2D(clickPoint.getX()+ dx, clickPoint.getY()+ dy);
//                System.out.println("Origin Coordinates: ("+origin.getX()+", "+origin.getY()+"}");
//                System.out.println("ClickPoint Coordinates: ("+clickPoint.getX()+", "+clickPoint.getY()+"}");
        });
        canvas.setOnScroll((ScrollEvent event) -> {
            event.consume();
            
            if (event.getDeltaY() == 0) {
                return;
            }
            
            double scaleFactor;
            int sign;
            if (event.getDeltaY() > 0){
                scaleFactor = SCALE_DELTA;
                sign = -1;
            }
            else{
                scaleFactor = 1 / SCALE_DELTA;
                sign = 1;
            }
            
            System.out.println("Width: "+canvas.getWidth()+" Height: "+canvas.getHeight());
            canvas.setScaleX(canvas.getScaleX() * scaleFactor);
            canvas.setScaleY(canvas.getScaleY() * scaleFactor);
            
            double scaleItX = (1/scaleFactor)*canvas.getWidth();
            double scaleItY = (1/scaleFactor)*canvas.getHeight();
            
            //canvas.setWidth((1/scaleFactor)*canvas.getWidth());
            //canvas.setHeight((1/scaleFactor)*canvas.getHeight());
            canvas.prefHeight(pane.getPrefViewportHeight());
            canvas.prefWidth(pane.getPrefViewportWidth());
            //canvas.relocate(sign*canvasOriginX+scaleItX/scaleFactor, sign*canvasOriginY+scaleItY/scaleFactor);
            //canvas.relocate(, scaleFactor);
            canvasOriginX = scaleItX;
            canvasOriginY = scaleItY;
            
            
            //canvas.resize(canvas.getWidth()+root.getScaleX() * scaleFactor,canvas.getHeight()+ root.getScaleY() * scaleFactor);
        });
        LongValue lastNanoTime = new LongValue(System.nanoTime());
         new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 100000000.0;
                lastNanoTime.value = currentNanoTime;

                
                // collision detection
//                Iterator<Planet> moneybagIter = moneybagList.iterator();
//                while (moneybagIter.hasNext()) {
//                    Sprite moneybag = moneybagIter.next();
//                    if (briefcase.intersects(moneybag)) {
//                        moneybagIter.remove();
//                        score.value++;
//                    }
//                }
//                if (canvas.getHeight() != theStage.getHeight()||canvas.getWidth() != theStage.getWidth())
//                {
//                    canvas.setWidth(theStage.getWidth());
//                    canvas.setHeight(theStage.getHeight());
//                }
                // render
                //gravity.attract(elapsedTime);
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gravity.attract(elapsedTime);
                celestials.stream().map((planet) -> {
                    planet.update(elapsedTime);
                    return planet;
                }).forEach((planet) -> {
                    //planets.get(i).render(gc);
                    planet.render(gc);
                    //System.out.println(planets.get(i).toString());
                });//                for (Sprite moneybag : moneybagList) {
//                    moneybag.render(gc);
//                }
//                String pointsText = "Cash: $" + (100 * score.value);
//                gc.fillText(pointsText, 360, 36);
//                gc.strokeText(pointsText, 362, 36);
            }
        }.start();
         
         theStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    
}
