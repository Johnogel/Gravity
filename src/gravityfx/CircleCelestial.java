/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gravityfx;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author Johnogel
 */
public abstract class CircleCelestial extends Circle implements SpaceBody {
    protected double mass, velocityX, velocityY;
    protected Point2D point, origin; 
    protected Color color;
    @Override
    public double getMass() {
        return mass;
    }

    @Override
    public void setMass(double mass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPosition(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        point = new Point2D(x,y);
    }

    @Override
   public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    @Override
    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }
    @Override
    public double getVelocityX(){
        return velocityX;
    }
    
    @Override
    public double getVelocityY(){
        return velocityY;
    }

    @Override
    public void gravitateTo(SpaceBody p, double time) {
       double angle, dvX, dvY;
        if(this.getY() < p.getY()){
            angle = Math.toRadians(180+ this.getPoint().angle(new Point2D(this.getX()-10, this.getY()), p.getPoint()));
            
        }
        else{
            angle = Math.toRadians(this.getPoint().angle(new Point2D(this.getX()+10, this.getY()), p.getPoint()));

        }
        //System.out.println(""+angle);
        dvY = this.accelerationTo(p)*Math.sin(angle)*time;
        dvX = this.accelerationTo(p)*Math.cos(angle)*time;
        
        //System.out.println(color.toString() + " "+angle);
        
        this.addVelocity(dvX, -dvY); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double accelerationTo(SpaceBody p) {
        return (p.getMass()*p.getMass())/SpaceMath.distanceSquared(this, p)*SpaceMath.distanceSquared(this, p); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Color getColor() {
        return color; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(double time) {
        this.setCenterX(this.getCenterX() + velocityX * time);
        this.setCenterY(this.getCenterY() + velocityY * time);
        point = new Point2D(this.getCenterX(), this.getCenterY()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getX() {
        return this.getCenterX(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getY() {
        return this.getCenterY();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(origin.getX()+this.getCenterX()-this.getRadius(), origin.getY() + this.getCenterY() - this.getRadius(), 
                this.getRadius() +this.getRadius(), this.getRadius() +this.getRadius()); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void renderLines(GraphicsContext gc, SpaceBody p) {
        gc.setStroke(Color.WHITE);
        gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY());
        //gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ this.getY());
        //gc.strokeLine(origin.getX()+p.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Point2D getPoint() {
        return point; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double distance(SpaceBody planet) {
       return point.distance(planet.getPoint()); //To change body of generated methods, choose Tools | Templates.
    }

  
    public boolean intersects(CircleCelestial s) {
        return ((this.getRadius()+s.getRadius())*(this.getRadius()+s.getRadius()) > 
                    SpaceMath.distanceSquared(this, s)); //To change body of generated methods, choose Tools | Templates.
    }

   
    public boolean intersects(Canvas c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
