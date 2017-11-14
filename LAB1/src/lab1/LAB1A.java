package lab1;

/**
 *
 * @author moliv
 */
/**
 * Disclaimer: Jag kollar upp syntax osv nästan konstant på 
 * föreläsningsanteckningar + google.
  */

/**
 * Fråga E1.1:
 * Ålder är en integer "rl" - age är en integer
 * Namn är ju ett, eller flera, ord - name är en string
 * 
 * Fråga E1.2 
 * 
 * getName och getAge samma datatyp som de ska "get" (string resp int). 
 * toString bör returnera en string, som namnet implicerar.
 * 
 * Fråga E1.3
 * 
 * Alla fält bör vara private, så man ej kan nå dem utanför objektet, utan 
 * måste hämta dem med våra metoder.
 * 
 * Alla metoder bör vara public, så de kan användas för att hämta de privata
 * fälten. Man kan ju möjligen ha en privat metod some getAge i en större klass.
 */

public class LAB1A {
    
    public static void main(String[] args){
        
        Human Putte = new Human();
                
        //getAge print 
        System.out.println("getAge: " + Putte.getAge());
        //getName print
        System.out.println("getName: " + Putte.getName());
        //toString() print
        System.out.println("toString: " + Putte.toString());
        //Pröva att printa Putte direkt
        System.out.println("Putte-print: " + Putte);
    }
}
/** Fråga E2.1
 * Uppenbarligen är toString() som def __str__(self): i python.
 * Det bestämmer vad som printas när man println-ar instansen. Kanske finns
 * exakta förklaringen i println koden.
 */