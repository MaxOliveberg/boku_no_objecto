/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author moliv
 */
public class scannerTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        Scanner xmlScanner = new Scanner(new File("Liv.xml"));
        while(xmlScanner.hasNext()==true){
            String holdLine = xmlScanner.nextLine();
            holdLine =holdLine.replace("<","");
            holdLine =holdLine.replace(">","");
            holdLine = holdLine.replace("namn=","");
            holdLine= holdLine.replace("\"","");
            
            System.out.println(holdLine);
            String endKey = "/"+ holdLine.split(" ")[0];
            String[] testArray = holdLine.split(" ",3);
            System.out.println(Arrays.toString(testArray));
            
            
            System.out.println(endKey);

        }
    }
    
}
