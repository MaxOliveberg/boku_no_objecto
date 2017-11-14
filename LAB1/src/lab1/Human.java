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
public class Human implements Comparable<Human> {
    /**
     * Implementerar compareable för compareTo(). <Human> specificerar att den
     * skall compareTo()-a mot andra Human och ej alla barn av Object (Tror jag)
     * Object är en superklass för alla object (Tror jag!)
     */
    private int age; // Definierar fälten för objektet
    private String name;
    private String[] possibleNames = {"Max","Sven","Putte","Nils"};
    
    public Human(){ 
        /**
         * Den här constructorn anropas när man skapar en human utan argument
         * Jag satte ihop en randomize funktion av nån anledning.
         */
        this.randomizeHuman();
    }
    public Human(int argumentAge, String argumentName){ // constructor#2
        /**
         * Den här constructorn anropas när man skapar en human med argument
         * som uppfyller specifikationerna ovan.
         */
        age = argumentAge;
        name = argumentName;
        if(age<0 || age > 100){
            /** 
             * Används i C-delen, crashar programmet om åldern ej är enligt
             * specifikationer.
             */
            throw new IllegalArgumentException("Illegal age for " + name + ": "
                    + age);
}}
    public void randomizeHuman(){
        /*
        Den här metoden slumpar ihop en human efter spec. Double -> integer
        sakerna för att math skapar en double men age är en integer.
        */
        double doubleAge;
        doubleAge = Math.random()*100;
        //System.out.println(doubleAge); //Debug
        this.age = (int) doubleAge;
        //System.out.println(age); //debug
        double doubleRandomNumber;
        doubleRandomNumber = Math.random()*(possibleNames.length);
        this.name = possibleNames[(int) doubleRandomNumber];
    }

//Alla set-o-get metoder är ganska självförklarliga.
    
    public int getAge(){ // man kan argumentera att denna o getName()...
                         // ...skall vara private om man skall hålla på och...
                         //...calla dem inom objektet massor
        return age;
    }
    public String getName(){
        return name;
    }
    
    public void setAge(int argumentAge){
        this.age = argumentAge;
    }
    
    public void setName(String argumentName){
        this.name = argumentName;
    }
    @Override 
    public String toString(){
        String compoundString = "Namn: " + name + " Ålder: " + age;
        return compoundString;
    }
/*
// Override behövs när man implementerar comparable
    Jag är ej 100% insatt i varför, kanske overridear den en metod av 
    föräldern men man bör ju inte behöva "override"a för det. Tag tex
    toString pre-compareable. Vore bra om vi visste vad den här override skiten
    är för något, men jag pallar ej kolla upp de i läsande stund.
*/
    @Override 
    public int compareTo(Human argumentHuman){
        /*
        Uppenbarligen har java inbyggda sorterare man kan använda om 
        ""storleken"" av en instans är definierad i objektet genom compareTo().
        CompareTo skall returnera integer, som är 0 om de är lika, + om
        instansen är större än argumentet o - om den är mindre.
        */
        int ageDifference = this.age - argumentHuman.getAge();
        return ageDifference;    
    }


}
