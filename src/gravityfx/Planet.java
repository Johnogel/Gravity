package gravityfx;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Planet extends Circle
{
    private final double PI = 3.1415;
    private Point2D point;   
    private double velocityX;
    private double velocityY;
    private double mass;
    private Color color;
    

    public Planet (Planet p){
        velocityX = p.getVelocityX();
        velocityY = p.getVelocityY();
        point = new Point2D(p.getX(), p.getY());
        this.setCenterX(point.getX());
        this.setCenterY(point.getY());
        color = new Color(p.getColor().getRed(), p.getColor().getGreen(),p.getColor().getBlue(), p.getColor().getOpacity());
        this.setRadius(p.getRadius());
        mass = PI*(this.getRadius()*this.getRadius());
        
    }
    public Planet(Planet a, Planet b){
        
        this.setMass(a.getMass()+b.getMass());
        velocityX = (a.getMass()*a.getVelocityX()+b.getMass()*b.getVelocityX())/mass;
        velocityY = (a.getMass()*a.getVelocityY()+b.getMass()*b.getVelocityY())/mass;
        
        if (a.getMass()>b.getMass()){
            this.point = new Point2D(a.getX(), a.getY());
            this.color = new Color(a.getColor().getRed(), a.getColor().getGreen(),a.getColor().getBlue(), a.getColor().getOpacity());
        }
        else{
            this.point = new Point2D(b.getX(), b.getY());
            this.color = new Color(b.getColor().getRed(), b.getColor().getGreen(),b.getColor().getBlue(), b.getColor().getOpacity());
        }
        this.setCenterX(point.getX());
        this.setCenterY(point.getY());
    }
    public Planet(double radius)
    {   
        
        velocityX = 0;
        velocityY = 0;
        color = Color.BLUE;
        this.setCenterX(0);
        this.setCenterY(0);
        this.setRadius(radius);
        point = new Point2D(this.getCenterX(), this.getCenterY());
        mass = PI*(radius*radius);
    }
    
    public Planet(double radius, double x, double y)
    {   
        
        velocityX = 0;
        velocityY = 0;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        color = Color.BLUE;
        point = new Point2D(this.getCenterX(), this.getCenterY());
        mass = PI*(radius*radius);
    
    }
    
    public Planet(double radius, double x, double y, Color color)
    {   
        
        velocityX = 0;
        velocityY = 0;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        this.color = color;
        point = new Point2D(this.getCenterX(), this.getCenterY());
        mass = PI*(radius*radius);
    
    }
    public void updateRadius(double radius){
        
    }
    public double getMass(){
        return mass;
    }    
    
    public void setMass(double mass){
        this.mass = mass;
        this.setRadius(Math.sqrt(mass/(PI)));
    }
    public void setPosition(double x, double y)
    {
        this.setCenterX(x);
        this.setCenterY(y);
        point = new Point2D(this.getCenterX(), this.getCenterY());
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }
    public double getVelocityX(){
        return velocityX;
    }
    
    public double getVelocityY(){
        return velocityY;
    }
    
    
    public void gravitateTo(Planet p, double time){
        double angle, dvX, dvY;
        if(this.getY() < p.getY()){
            angle = Math.toRadians(180+ this.getPoint().angle(new Point2D(this.getX()-10, this.getY()), p.getPoint()));
            
        }
        else{
            angle = Math.toRadians(this.getPoint().angle(new Point2D(this.getX()+10, this.getY()), p.getPoint()));
            
            //dvY = -this.accelerationTo(p)*Math.sin(angle)*time;
        }
        //System.out.println(""+angle);
        dvY = this.accelerationTo(p)*Math.sin(angle)*time;
        dvX = this.accelerationTo(p)*Math.cos(angle)*time;
        
        //System.out.println(color.toString() + " "+angle);
        
        this.addVelocity(dvX, -dvY);
        
    }
    public double accelerationTo(Planet p){
        return (p.getMass())/(this.distance(p)*this.distance(p)); 
    }
    
    
    public Color getColor(){
        return color;
    
    }
    
    public void update(double time)
    {
        
        this.setCenterX(this.getCenterX() + velocityX * time);
        this.setCenterY(this.getCenterY() + velocityY * time);
        point = new Point2D(this.getCenterX(), this.getCenterY());
        
    }
    public double getX(){
        return this.getPoint().getX();
    }
    
    public double getY(){
        return this.getPoint().getY();
    }
    public void render(GraphicsContext gc)
    {
        gc.setFill(color);
        gc.fillOval(this.getCenterX()-this.getRadius(), this.getCenterY() - this.getRadius(), this.getRadius() +this.getRadius(), this.getRadius() +this.getRadius());
    }
    
    public void render(GraphicsContext gc, Point2D origin){
        gc.setFill(color);
        gc.fillOval(origin.getX()+this.getCenterX()-this.getRadius(), origin.getY() + this.getCenterY() - this.getRadius(), 
                this.getRadius() +this.getRadius(), this.getRadius() +this.getRadius());
    }
    public void renderLines(GraphicsContext gc, Planet p, Point2D origin){
        gc.setStroke(Color.WHITE);
        gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY());
        //gc.strokeLine(origin.getX()+this.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ this.getY());
        //gc.strokeLine(origin.getX()+p.getX(), origin.getY()+this.getY(),origin.getX()+ p.getX(),origin.getY()+ p.getY());
    }
    
    public Point2D getPoint(){
        return point;
    }
    public double distance(Planet planet){
        return this.getPoint().distance(planet.getPoint());
    }
    public boolean intersects(Planet s)
    {
        return ((this.getRadius()+s.getRadius())*(this.getRadius()+s.getRadius()) > 
                    (((this.getX()-s.getX())*(this.getX()-s.getX()))+((this.getY()-s.getY())*(this.getY()-s.getY()))));
    }
    
    public boolean intersects(Canvas c){
        return ((this.getPoint().getX() < this.getRadius())||(this.getPoint().getX() > c.getWidth()-this.getRadius())
                ||(this.getPoint().getY() < this.getRadius())||this.getPoint().getY() > c.getHeight() - this.getRadius());
    }
    
    @Override
    public String toString()
    {
        return " Position: [" + this.getCenterX() + "," + this.getCenterY() + "]" 
        + " Velocity: [" + velocityX + "," + velocityY + "] Color: " +color.toString() +" Hashcode: "+this.hashCode();
    }
}
