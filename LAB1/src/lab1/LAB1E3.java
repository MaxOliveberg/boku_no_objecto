/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;
/**
 *
 * @author moliv
 */
public class LAB1E3 {

    public static void main(String[] args) {
        int numHumans = 15;
        ArrayList<Human> Humans = new ArrayList<Human>(); // de här googlade jag
        for(int i=0; i<numHumans; i++){
            //System.out.println(i);//debug;
            Humans.add(new Human());
            }
        
        for(int i = 0; i < numHumans; i++){
            System.out.println( Humans.get(i) );
        
        }
    }
    
}
