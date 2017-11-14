
package lab2;
import lab2.uicomponent.MyNewButton;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author moliv
 */

/*
Den här main-en skapar en frame, lägger till en mybutton, och ritar den sen
*/
public class LAB2E2 implements ActionListener {

    static MyNewButton labE2Button;

    public static void main(String[] args) {

        
        JFrame labE2Frame = new JFrame();
        labE2Button = new MyNewButton();
        labE2Frame.add(labE2Button);
        labE2Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labE2Frame.pack();
        labE2Frame.setVisible(true);

    }
    public void actionPerformed(ActionEvent e){
        labE2Button.changeState();
    }
    
}

/**
 * Fråga E2
 * 
 * Pack fixar uppenbarligen så huvudfönstret har rätt dimensioner för 
 * de inblandade komponenterna. Skippar man pack så blir rutan minimerad, tills
 * man drar ut den på macOS.
 * 
 * Från JFrame dokumentationen för Java SE 7:
 * 
 * Causes this Window to be sized to fit the preferred size and layouts of its
 * subcomponents. The resulting width and height of the window are automatically
 * enlarged if either of dimensions is less than the minimum size as specified
 * by the previous call to the setMinimumSize method.
 * ¨
 * Setvisible gör rutan synlig, från dokumentationen:
 * 
 * Shows or hides this Window depending on the value of parameter b.
 * 
 * Skippar man setvisible syns ej knapprutan över huvud taget.
 * 
 * Fråga E3
 * 
 * Med setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) quittar programmet när
 * man stänger rutan, i macOS, medans de fortsätter vara "på" annars.
 * 
 */