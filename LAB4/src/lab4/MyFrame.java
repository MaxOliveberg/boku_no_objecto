/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import javax.swing.JFrame;

/**
 *
 * @author moliv
 */
public class MyFrame extends JFrame {
    
    int L = 1;
    
    public MyFrame(int numParticles){
        Model myModel = new Model(L);
        
        for(int i = 0; i < numParticles; i ++){
            myModel.addParticle();
        }
        
        View myView = new View(myModel);
        Controller myController = new Controller(myModel, myView);
        this.add(myController);
        //this.add(myView);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) {
        MyFrame theFrame = new MyFrame(Integer.parseInt(args[0]));
        
    }
    
}
