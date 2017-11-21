/*
KVAR ATT GÖRA: 
E5.2-6

C4-5

!!!

STRUNTA INTE I DEM !
 */
package lab4;

import javax.swing.JFrame;

/**
 *
 * @author moliv
 */
public class MyFrame extends JFrame {
    
    int L = 1;// fuck if i know
    
    public MyFrame(int numParticles){
        Model myModel = new Model(L);
        
        for(int i = 0; i < numParticles; i ++){
            myModel.addParticle();
        }
        /*
        den riktiga magin händer i klasserna här bara lägger jag in allt typ.
        */
        View myView = new View(myModel);
        Controller myController = new Controller(myModel, myView);
        this.add(myController);
        //this.add(myView);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) {
        /*
        Skicka in antalet partiklar som argument ! Lägg till exception
        check här jag pallar inte.
        */
        MyFrame theFrame = new MyFrame(Integer.parseInt(args[0]));
        
    }
    
}
