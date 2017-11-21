/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Color;

/**
 *
 * @author moliv
 */
public class View extends JPanel{
    
    Model Model;
    int prefSizeX = 500;
    int prefSizeY = 500;
    double radius = 2;
    
    public View(Model argModel){
        Model = argModel;
        setPreferredSize(new Dimension(prefSizeX, prefSizeY));
        
    }
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(new Color(0, 150, 0));
        ArrayList<Double> posList = Model.getPositions();
        //System.out.println("GOT SO FAR");
        for(int i = 0; i < posList.size(); i = i+2){ 
            //System.out.println("BUT IN THE END IT DOESNT EVEN MATTER");
          //  System.out.println(i);
            Ellipse2D circle = new Ellipse2D.Double(posList.get(i),posList.get(i+1),
            radius*2,radius*2);
            
            if(Model.isLocked(i/2)==true){
                g2.setPaint(new Color(128, 0, 0));
                g2.fill(circle);
                g2.setPaint(new Color(0, 150, 0));
            }
            g2.draw(circle);
        }
        
    }
}
