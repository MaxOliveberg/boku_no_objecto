/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author moliv
 */
import java.util.*;
public class LAB1C {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
        args kommer in som en string lista, dvs en lista som innehåller strings
        tex ["a","b","1337"] osv
        */
        ArrayList<Human> People = new ArrayList<Human>();
        int i = 0;
       // System.out.println(args.length);//Debug prylar
       // System.out.println(args[0]);
        while(i < args.length){
            /*
            Script som kollar igenom args listan och skapar människor o fysiker
            därefter. Bör crasha med korrekta crashmedellanden om man fuckar upp
            nånstans, men de var latenight captain morgan fuelled kodning så jag 
            är ej helt 100
            Läs upp på exceptions om du ej har det redan.
            */
            if(args[i].equals("-H")){
                System.out.println("human");
                try{
                    int humanAge= Integer.parseInt(args[i+2]);
                }
                catch(NumberFormatException e){
                    System.out.println(e);
                    return;
                }
                
            
                int humanAge= Integer.parseInt(args[i+2]);
                //System.out.print(humanAge);
                
                People.add(new Human(humanAge,args[i+1]));
                i = i+3;
            }
            else if(args[i].equals("-F")){
                System.out.println("fys");
                try{
                    int humanAge= Integer.parseInt(args[i+2]);
                }catch(NumberFormatException e){
                    System.out.println(e);
                    return;
                }
                try{
                    int humanAge= Integer.parseInt(args[i+3]);
                }catch(NumberFormatException e){
                    System.out.println(e);
                    return;
                }
                int fysikerAge= Integer.parseInt(args[i+2]);
                int fysikerExperience= Integer.parseInt(args[i+3]); 
                People.add(new Fysiker(fysikerAge,args[i+1],fysikerExperience));
                i=i+4;
                        }
                else{
                    throw new IllegalArgumentException("Wrong format!");
                                
                                }
                    }
                    
            for(int var=0; var < People.size();var++ ){
            System.out.println(People.get(var));}
        }
    }

    

