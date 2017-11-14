/*
den här filen är inte så viktig, den blir expanded upon i C1a o C1B
 */
package lab2;

import lab2.uicomponent.MyButton;

import java.awt.*;
import java.util.ArrayList;
import static java.awt.Color.*;
import javax.swing.JFrame;

/**
 *
 * @author moliv
 */
public class LAB2C1 {

    public static void main(String[] args) {
        int buttonAmount;
        if(args.length > 1){
            throw new IllegalArgumentException("Illegal argument. The only "+
                    "acceptable argument is an integer");
        }
        try{
            buttonAmount = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){ 
            throw new IllegalArgumentException("Illegal argument. The only "+
                                          "acceptable argument is an integer");
            
        }
    constructFrame(buttonAmount);
    }
    
    private static void constructFrame(int numButtons){
        
        JFrame theFrame = new JFrame();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gl = new GridLayout();
        gl.setColumns(1);
        gl.setRows(numButtons);
        gl.setVgap(0);
        gl.setHgap(0);
        theFrame.getContentPane().setLayout(gl);
        Double hold = 255.0/numButtons;
        int colorJump = hold.intValue();

        for(int i = 0; i < numButtons; i++){
            Color colorOne = new Color(i*colorJump, 255-i*colorJump, 150);
            Color colorTwo = new Color((3*colorJump*i)%255,i*colorJump,77);
            MyButton tempButton =new MyButton(colorOne,colorTwo,(i+1)+"A",(i+1)+"B");
            theFrame.getContentPane().add(tempButton);
        }
        theFrame.pack();
        theFrame.setVisible(true);
        
    }
    
}
