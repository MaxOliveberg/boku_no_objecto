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
public class LAB1E4 {


    public static void main(String[] args){
        // E4.4
        System.out.println("E4.4 prints");
        int numHumans = 15;
        ArrayList<Fysiker> Fysiker = new ArrayList<Fysiker>(); // de här googlade jag
        for(int i=0; i<numHumans; i++){
            //System.out.println(i);//debug;
            Fysiker.add(new Fysiker());
            }
        
        for(int i = 0; i < numHumans; i++){
            System.out.println( Fysiker.get(i) );
        
        }
        // E4.5
        System.out.println("E4.5 prints");
        ArrayList<Human> People = new ArrayList<Human>();
        for(int i = 0; i <5; i++){
            People.add(new Fysiker());
            People.add(new Human());
        }
        for(int i=0; i < People.size();i++ ){
            System.out.println(People.get(i));
    }
}
}