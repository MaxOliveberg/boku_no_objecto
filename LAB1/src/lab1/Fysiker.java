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
public class Fysiker extends Human{
    /*
    Fysiker är ett barn av Human, två nya fält varav ett är static
    */
    private int startYear;
    static int currentYear = 2017;
    
    private int physicsAge(){// räknar ut hur länge sen de var personen började
        //plugga fysik
        return currentYear-startYear;
    }
    public Fysiker(){
        /*
        Kallas när man skapar en fysiker utan input. Använder ageCheck() för att 
        se till att ingen var under 15 när de började osv osv 
        */
        randomizeHuman();
        int startYearSpan = currentYear-1932;
        //double pga datatyp shenanigans
        double doubleStartYear= 1932 + Math.random()*startYearSpan;
        startYear = (int) doubleStartYear;
        ageCheck(false); // försämrar ju kanske the randomness ytterligare
        
    }
    public Fysiker(int argumentAge, String argumentName, int argumentStartYear){
        /*
        Age o name är ju privata variables för human så man måste set-a dem med
        hjälp av funktionerna setAge osv här.
        */
        this.setAge(argumentAge); 
        this.setName(argumentName);
        startYear= argumentStartYear;
        if(this.getAge()-this.physicsAge()<15 || startYear <1932 
                || startYear> 2015||this.getAge()>100 || this.getAge()<14){
            // kastar ut ett exception om man ger ogiltig indata.
                throw new IllegalArgumentException("Ogiltiga värden för "
                        +this.getName()+". "
                + "Ålder vid påbörjade fysik: "+this.physicsAge() + " Startår: "
                + startYear+". Ålder: " + this.getAge());
        }
    }
    
    public int getYear(){
        return startYear;
    }
    
    private void ageCheck(boolean print){ // print bestämmer om den printar
                                           // felmedellanden
                                           /*
                                           Den här metod tar ogiltig
                                           indata o sätter den till närmaste 
                                           giltiga, typ.
                                           */
        if(this.startYear>2015){
            if(print){
                System.out.println("Invalid starting year. "
                      + "Starting year set to 2015.");}
            this.startYear = 2015;
                    
        }
        if(this.startYear<1932){
            this.startYear = 1932;
            if(print){
                System.out.println("Invalid starting year. "
                        + "Starting year set to 1932.");}
        }
        
        if(this.getAge()-this.physicsAge()<15){
            this.setAge(15 + this.physicsAge());
            if(print){
                System.out.println("Invalid age at starting year."
                     + " Age set to: " + this.getAge());}
        }
    }
        public String toString(){
            /*
           formaterar om startåret till en string och tar sen ut de två sista
            tecknen för att kunna printa startåret som tex F96, F15 osv osv
            */
        String fYearString= String.format("%s", this.startYear);
        String fSubString = fYearString.substring(Math.max(fYearString.length() - 2, 0));
        String compoundString = "Namn: " + this.getName() + " Ålder: " 
                + this.getAge() + " Tillhör årskurs: F" + fSubString;
        return compoundString;}
        
        public int compareTo(Human argumentHuman){
            if(argumentHuman instanceof Fysiker && 
                    this.getAge() == argumentHuman.getAge() ){
                /*
                Om två fysiker är lika gamla rankas de efter när de började fysik
                */
                
                    Fysiker argumentFysiker = (Fysiker)argumentHuman;
                    return (-1)*this.startYear+argumentFysiker.getYear();
        }
            int ageDifference = this.getAge() - argumentHuman.getAge();
            return ageDifference;    
    }
}
    
    
