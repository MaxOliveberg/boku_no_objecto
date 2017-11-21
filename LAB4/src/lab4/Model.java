/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author moliv
 */
public class Model {
    
        public class Particle {
    
        double x;
        double y;
        boolean locked;
        double MAX_X = 500;
        double MAX_Y = 500;
        
    
        public Particle(){
            this(Math.random()*500,Math.random()*500);

    }
        public Particle(double argX, double argY){
            x = argX;
            y = argY;
            locked = false;
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
        public boolean isLocked(){
            return locked;
        }
        public void lock(){
            int m_x = (int) Math.floor(x/50);
            int m_y = (int) Math.floor(y/50);
            Model.this.addParticleToMatrix(m_x,m_y,this);
            locked = true;
        }
        
        public double getDist(double argX, double argY){
            return Math.sqrt((x-argX)*(x-argX) + (y-argY)*(y-argY));
        }
}
        
    int numParticles;
    ArrayList<Particle> particleArray;
    double L;
    double dL;
    double dT;
    ArrayList<ArrayList<ArrayList<Particle>>> lockMatrix;
    public Model(){
        particleArray = new ArrayList<Particle>();
    }
    
    public void addParticleToMatrix(int x, int y, Particle particle){
        ((lockMatrix.get(x)).get(y)).add(particle);
    }
    public Model(double argL){
        particleArray = new ArrayList<Particle>();
        lockMatrix = new ArrayList<ArrayList<ArrayList<Particle>>>();
        for(int i  = 0; i < 10; i ++){
            ArrayList<ArrayList<Particle>> array_i 
                    = new ArrayList<ArrayList<Particle>>();
            for(int n = 0; n < 10; n++){
                ArrayList<Particle> array_n = new ArrayList<Particle>();
                array_i.add(n, array_n);
            }
            lockMatrix.add(i, array_i);
        }
        setL(argL);
        //setdL(argdL);
       // setdT(argdT);
    }
    
    public void setL(double argL){
        L = argL;
    }
    
    public double getL(){
        return L;
    }
    
    public void setdL(double argdL){
        dL=argdL;
    }
    
    public void setdT(double argdT){
        dT = argdT;
    }
    
    public void addParticle(){
        particleArray.add(new Particle());
    }
    
    public void addParticle(double x, double y){
        Particle newParticle = new Particle(x,y);
        particleArray.add(newParticle);
    }
    
    public ArrayList<Particle> getParticles(){
        return particleArray;
    }
    
    public boolean isLocked(int i){
        return particleArray.get(i).isLocked();
    }
    
    public void step(){
        for(int i = 0; i < particleArray.size(); i++){

            Particle currentParticle = particleArray.get(i);
            CHECKIF:{
            if(currentParticle.isLocked()==false){
                
                double angle = Math.random()*2*Math.PI;
                double cX = currentParticle.getX();
                double cY =currentParticle.getY();
                double newX = cX + Math.cos(angle)*L;
                double newY = cY + Math.sin(angle)*L;

                if(newX>495){ 
                    newX=495;
                    currentParticle.lock();
                    break CHECKIF;}
                if(newY>495){ 
                    newY=495;
                    currentParticle.lock();
                    break CHECKIF;}
                if(newX<0){ 
                    newX=0;
                    currentParticle.lock();
                    break CHECKIF;}
                if(newY<5){ 
                    newY=5;
                    currentParticle.lock();
                    break CHECKIF;}
                
                
                
                if(currentParticle.getDist(250,250)<4){
                    currentParticle.lock();
                    break CHECKIF;
                }
                currentParticle.setPos(newX,newY);
                int m_x = (int) Math.floor(newX/51);
                int m_y = (int) Math.floor(newY/51);
                ArrayList<Particle> lockList = (lockMatrix.get(m_x)).get(m_y);
                
                
                for(int n = 0; n < lockList.size();n++){
                    double lX = (lockList.get(n)).getX();
                    double lY = (lockList.get(n)).getY();
                    if(currentParticle.getDist(lX,lY)<4){
                        currentParticle.lock();
                        break ;
                    }
                }
                    
            }

        }
        }
    }
    
    public ArrayList<Double> getPositions(){
        ArrayList<Double> positionList = new ArrayList<Double>();
      //  System.out.println("oi oi");
        for(int i = 0; i<particleArray.size()*2; i= i+2){
            //System.out.println(particleArray.size());
           // System.out.println(i);
            Particle currentParticle = particleArray.get(i/2);
            double X = currentParticle.getX();
            double Y = currentParticle.getY();
            positionList.add(i,X);
            positionList.add(i+1,Y);
            
        }
        return positionList;
    }
}
