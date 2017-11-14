    
package lab2;     
import lab2.uicomponent.MyButton;

import javax.swing.*;

//import static java.awt.Color.*;
/**
 *
 * @author moliv
 */

/*
De  var brutala problem att få den här o köra ordentligt, dvs som en applet.
Försök få ett html script o funka, annars bara kör via appletviewer i netbeans
eller eclipse så funkar de garanterat
*/
public class LAB2E4 extends JApplet {

    public void init(){
        /*
        Init körs när appletten startar. Knappen läggs på appleten, analogt med
        hur man lägger den på ramen typ.
        */
            
        MyButton labE2_memeButton = new MyButton();
        //labE2Button.addActionListener(labE2Button);
        this.add(labE2_memeButton);

    }
    public LAB2E4(){
   
    }
    

   /* public static void main(String[] args) {    
   //     LAB2E3 justWork = new LAB2E3(); 
    }*/

    
}
