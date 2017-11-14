package lab2.uicomponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Color.*;

public class MyNewButton extends JButton {

    private int buttonState = 1; // init till 1 redan här
    /*
    Man hade ju kunnat  bygga den här klassen med bara typ 2-3 fält men jag
    hade extrafälten för ifall man skulle vilja plocka ut dem. Dock hade man
    även kunnat göra de genom JButton funk men de hade ej blivit clean typ.
    */
    private String currentText;
    private String buttonText1;
    private String buttonText2;

    private Color currentColor;
    private Color buttonColor1;
    private Color buttonColor2;

    public MyNewButton(Color argumentColor1, Color argumentColor2,
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
    public MyNewButton(){

        buttonColor1 = RED;
        buttonColor2 = GREEN;

        buttonText1 = "Press me!";
        buttonText2 = "Press me again!";

        currentColor = buttonColor1;
        currentText = buttonText1;

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

}
