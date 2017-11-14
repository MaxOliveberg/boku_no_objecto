/*
Kolla igenom alla deluppgifterna, och gör E5.2, och skrivfrågorna på C!
 */

/**
 * @author moliv
 */
package lab2.uicomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.Color.*;

/*
Inget i den här klassen är advanced
*/
public class MyButton extends MyNewButton implements ActionListener {

    //int buttonState = 1; // init till 1 redan här
    /*
    Man hade ju kunnat  bygga den här klassen med bara typ 2-3 fält men jag
    hade extrafälten för ifall man skulle vilja plocka ut dem. Dock hade man 
    även kunnat göra de genom JButton funk men de hade ej blivit clean typ.
    */
    /*
    String currentText;
    String buttonText1;
    String buttonText2;
    
    Color currentColor;
    Color buttonColor1;
    Color buttonColor2;
    
    public MyButton(Color argumentColor1, Color argumentColor2,
                    String argumentString1, String argumentString2){
        
        buttonColor1 = argumentColor1;
        buttonColor2 = argumentColor2;
        
        buttonText1 = argumentString1;
        buttonText2 = argumentString2;
        
        currentColor = buttonColor1;
        currentText = buttonText1;
        
        setText(currentText);
        setBackground(currentColor);
        setOpaque(true); // behövs på osx
        
    }
    public MyButton(){
        
        buttonColor1 = RED;
        buttonColor2 = GREEN;
        
        buttonText1 = "Press me!";
        buttonText2 = "Press me again!";
        
        currentColor = buttonColor1;
        currentText = buttonText1;
        this.addActionListener(this);
        setText(currentText);
        setOpaque(true);
        setBackground(currentColor);
    }
    
    public void changeState(){
        if(buttonState ==1){
            currentColor = buttonColor2;
            currentText = buttonText2;
            buttonState = 2;    
        }
        else{
            currentColor = buttonColor1;
            currentText = buttonText1;
            buttonState = 1; 
        }
        this.setText(currentText);
        this.setBackground(currentColor);
    }
    
    public int getStat(){
        
        return buttonState;
    }
    
    public void actionPerformed(ActionEvent e){
    this.changeState();
}
*/

    public MyButton(Color argumentColor1, Color argumentColor2,
                    String argumentString1, String argumentString2) {
        super(argumentColor1, argumentColor2,
                argumentString1, argumentString2);
        this.addActionListener(this);
    }

    public MyButton() {
        super();
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.changeState();
    }

}
