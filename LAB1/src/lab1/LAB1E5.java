/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;
import java.util.*;

/** Fråga E5.3 
 * 
 * Det går att jämnföra två fysiker genom compareTo() då de ärver den från 
 * Human
*/ 
/**
 *
 * @author moliv
 */
public class LAB1E5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // E5.2
        
        Human A = new Human();
        Human B = new Human();
        Human eldest;
        Human youngest;
        int ageDifference = A.compareTo(B);
        
        if(ageDifference == 0){
            System.out.println(A.getName() + " och " + B.getName() + " är båda "
            + A.getAge()+ " år gamla.");
            
        }
        else{
            if(ageDifference > 0){
                eldest = A;
                youngest = B;
            }
            else{
                eldest = B;
                youngest = A;
                
            }
            System.out.println(eldest.getName()+", som är " + eldest.getAge()
            + " år gammal, är äldre än " +youngest.getName()+ " som är "
            + youngest.getAge() +" år gammal." );
        }
        // E5.4
        System.out.println("E4.5 prints");
        ArrayList<Human> People = new ArrayList<Human>();
        for(int i = 0; i <5; i++){
            People.add(new Fysiker());
            People.add(new Human());
        }
        for(int i=0; i < People.size();i++ ){
            System.out.println(People.get(i));
    }
        Collections.sort(People);
        for(int i=0; i < People.size();i++ ){
            System.out.println(People.get(i));}
    }
    
}
