/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityfx;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Johnogel
 */
public class SpaceShip {
    private Point2D origin, center;
    private double angle, angAccel;
    private double[] xPoints;
    private double[] yPoints;
    
    public SpaceShip(Point2D origin){
        
        this.origin = origin;
        this.center = new Point2D(origin.getX(), origin.getY());
        this.angle = 0;
        for(int i = 0; i < 6; i++){
            switch (i){
                case 0:
                    
            }
        }
        
    }
    public void rotate(double time){
        
        
        
    }
    
    
    public void render(GraphicsContext gc){
        gc.setStroke(Color.WHITE);
        gc.strokePolygon(xPoints, yPoints, 6);
        
    }
}
    

