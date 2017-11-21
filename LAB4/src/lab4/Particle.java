/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package lab4;

/**
 *
 * @author moliv
 */
public class Particle {
    
    double x;
    double y;
    
    public Particle(){
        
        this(Math.random()*500,Math.random()*500);
        System.out.println("Ayooooo");

    }
    public Particle(double argX, double argY){
        System.out.println("Ayooooo2 " + argX + " "+ argY);
        x = argX;
        y = argY;
    }
    
    
    public void setX(double argX){
        x = argX;
    }
    
    public void setY(double argY){
        y = argY;
    }
    
    public void setPos(double argX,double argY){
        x = argX;
        y= argY;
    }
    
    public String toString(){
        return "Particle at x: " + x + ", y: " + y; 
    }
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
}
