/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author moliv
 */
public class Controller extends JPanel implements ChangeListener,
        ActionListener{
    
    Model Model;
    View View;
    Timer timer;
    JSlider LSlider;
    JSlider TSlider;
    double timeElapsed = 0;
    double logTime = 5;
    boolean logInform;
    double L = 1;
    PrintWriter logger;
    /*
    public static void main(String[] args) {
    int numParticles = Integer.parseInt(args[0]);
    
    Model newModel = new Model(1);
    View newView = new View(newModel);
    Controller theController = new Controller(newModel, newView);
    
        
    }*/
    public Controller(Model argModel, View argView) {
        
        Model = argModel;
        View = argView;
        /*
        du kan ju alltid snygga till layouten om du vill
        */
        LSlider = new JSlider(JSlider.HORIZONTAL,0,10,1);
        TSlider = new JSlider(JSlider.HORIZONTAL,1,1000,5);
        logInform = false; // används för o kolla om den loggat färdigt
        try{
            /*
            den skapar en ny fil om den gamla ej finns
            
            VAAAARNING DEN RENSAR FILEN VARJE GÅNG DU KÖR
            */
        logger = new PrintWriter("data.txt");}
            catch(IOException e){
                System.out.println("Could not find textfile data.txt");
                }

        /*
        Lägger till sakerna på denna, denna läggs till på main skiten
        */
        this.add(LSlider); 
        this.add(TSlider);
        this.add(View);
        
        timer = new Timer(1,this); // klickar varje något sekund
        timer.setRepeats(true);
        timer.start(); // startar timern
        
        LSlider.addChangeListener(this); // lyssna på dennas changelistener
        TSlider.addChangeListener(this);
    }
    
    public void stateChanged(ChangeEvent e){
        JSlider sourceSlider = (JSlider)e.getSource();
        /*
        ganska självförklarligt
        */
        if(sourceSlider== LSlider){
            Model.setL(sourceSlider.getValue());
        }
        if(sourceSlider==TSlider){
            timer.setDelay(sourceSlider.getValue());
           }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timeElapsed = timeElapsed + timer.getDelay();
        /*
        Jag pallar ej göra a d v a n c e d testing här, skriv en snabbare
        data collector om denna ej är fast enough
        */
        if(timeElapsed <= logTime){
            /*
            1. få lista med positioner
            2. gör till string
            3. rensa från onödiga tecken
            4. lägg till tiden 
            5. skriv i logg filen
            */
            ArrayList<Double> posList = Model.getPositions();
            String posString  = posList.toString();
            posString = posString.replace("[", "");
            posString = posString.replace("]", "");
            posString = posString.replace(" ", "");
            posString = timeElapsed + "," + posString;
            logger.println (posString);
            //System.out.print(posString);
        }
        else if(logInform == false){
            /*
            slutar logga om time IS UP
            */
            logInform = true;
            logger.close();
            System.out.println("Stopped saving to log");
        }
        Model.step();    // kallar ett steg
        View.updateUI(); // uppdaterar grafiken typ ?? kanske finns bättre sätt
    }
}