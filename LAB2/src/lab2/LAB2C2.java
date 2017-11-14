
package lab2;

import lab2.uicomponent.MyButtonArray;
import lab2.uicomponent.MyNewButton;

import java.awt.*;
import javax.swing.JFrame;

/**
 *
 * @author moliv
 */
public class LAB2C2 {
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
        MyButtonArray knappArray = new MyButtonArray(numButtons);
        for (MyNewButton item: knappArray.buttonArray) {
            theFrame.getContentPane().add(item);
        }
        theFrame.pack();
        theFrame.setVisible(true);
    }
}
