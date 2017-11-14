package lab2;

import lab2.uicomponent.MyNewButton;

import javax.swing.*;
import java.awt.event.*;

public class TestDrive implements ActionListener {
    MyNewButton knappen;

    public TestDrive() {
        knappen = new MyNewButton();
        knappen.addActionListener(this);
        JFrame window = new JFrame("Fönster");
        window.getContentPane().add(knappen);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }

    public static void main(String[] args) {
        TestDrive justwork = new TestDrive();
    }

    public void actionPerformed(ActionEvent e){
        ((MyNewButton)e.getSource()).changeState();
        //knappen.changeState();
    }
}
