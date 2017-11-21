/*
Model sköter all matte, particle är en behållare typ. Alla metoder i
patricle är ganska sjäkvförklarliga
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
        
    int numParticles;// används fan aldrig
    ArrayList<Particle> particleArray; // har alla partiklar i sig
    double L;
    double dL;// ??
    double dT;// ??? värsta är att jag var nykter genom hela denna labben
    // och de är fortfarande så här trash
    
    
    /*
    De här fältet är den värsta memen någonsin. De är en array för 10 arrays
    som innehåller 10 arrays för att hålla arrays för partiklar. Basically ett
    10x10 nät av partikel arrays så att man sparar datakraft när man ska dist-
    checka massa partiklar senare för att se om de är nära en annan låst 
    partikel.
    */
    ArrayList<ArrayList<ArrayList<Particle>>> lockMatrix;
    
    public Model(){
        /*
        tror aldrig jag kallar den här
        */
        particleArray = new ArrayList<Particle>();
    }
    
    public void addParticleToMatrix(int x, int y, Particle particle){
        /*
        Lägger till en partikel i rätt array i rutnätet
        */
        ((lockMatrix.get(x)).get(y)).add(particle);
    }
    
    public Model(double argL){
        
        particleArray = new ArrayList<Particle>();
        
        /*
        Laddar in ett 10x10 rutnät av arrays så vi slipper jobbiga nullpointer
        eller out-of-index exceptions senare kanske går o fixa på nåt annat sätt
        */
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
        
        
        setL(argL);// självförklarligt
        
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
        /*
        Welcome to hell goyim
        */
        for(int i = 0; i < particleArray.size(); i++){

            Particle currentParticle = particleArray.get(i);
            /*
            Uppenbarligen so kan man breaka ur If-en ifall man kör såhär med
            CHECKIF: eller bokstavligen vad man nu vill döpa den till men jag
            har ingen aning om de ens funkar o pallar ej sätta upp ett 
            experiment men om de funkar sparar de datakraft.
            */
            CHECKIF:{
            if(currentParticle.isLocked()==false){
                /*
                Här checkar den alla möjliga lock conditions o rör på partikeln.
                */
                
                double angle = Math.random()*2*Math.PI;
                double cX = currentParticle.getX();
                double cY =currentParticle.getY();
                double newX = cX + Math.cos(angle)*L;
                double newY = cY + Math.sin(angle)*L;
                
                /*
                Tog 5 av alla kanter för format anledningar men du får fixa med 
                det om du vill så de blir perfekt.
                */
                if(newX>495){ 
                    newX=495;
                    currentParticle.lock();
                    break CHECKIF;}
                if(newY>495){ 
                    newY=495;
                    currentParticle.lock();
                    break CHECKIF;// bör breakea checkif: teoretiskt
                }
                if(newX<5){ 
                    newX=5;
                    currentParticle.lock();
                    break CHECKIF;}
                if(newY<5){ 
                    newY=5;
                    currentParticle.lock();
                    break CHECKIF;}
                
                
                
                if(currentParticle.getDist(250,250)<4){
                    /*
                    bara en liten prick i typ mitten as per uppgift c3 eller nåt
                    */
                    currentParticle.lock();
                    break CHECKIF;
                }
                
                currentParticle.setPos(newX,newY); //  här rör den på sig
                
                
                int m_x = (int) Math.floor(newX/51); // floor = runda ner
                int m_y = (int) Math.floor(newY/51);// 51 = inga problem
                
                /*
                här tar vi ut rätt array ur 10x10 nät
                */
                ArrayList<Particle> lockList = (lockMatrix.get(m_x)).get(m_y);
                
                
                for(int n = 0; n < lockList.size();n++){
                    /*
                    går igenom arrayen element för element o kollar avstånd
                    De här är varför vi har nätet de sparar processing power
                    i slutändan.
                    tänk om man hade behövt jämnföra med alla låsta prickar
                    hade blivit on average säkert 25 ggr mer 
                    */
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
            /*
            Ganska självförklarlig, returnerar en lista med x koord på i, 
            y koord på i+1, med increments i=i+2
            
            */
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
