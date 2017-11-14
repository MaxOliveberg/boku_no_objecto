package lab2.uicomponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyButtonArray {
    public MyNewButton[] buttonArray;

    public MyButtonArray(int numButtons) {

        buttonArray = new MyNewButton[numButtons];
        Double hold = 255.0 / numButtons;
        int colorJump = hold.intValue();
        for (int j = 0; j < numButtons; j++) {
            Color colorOne = new Color(j * colorJump, 255 - j * colorJump, 150);
            Color colorTwo = new Color((3 * colorJump * j) % 255, j * colorJump, 77);
            MyNewButton holdButton = new MyNewButton(colorOne, colorTwo, (j + 1) + "A", (j + 1) + "B");
            holdButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for (int k = 0; k < buttonArray.length; k++) {
                        if (e.getSource() != buttonArray[k]) {
                            buttonArray[k].changeState();
                        }
                    }
                }
            });

            buttonArray[j] = holdButton;
        }
    }
}