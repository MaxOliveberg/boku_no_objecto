/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author moliv
 */
public class MyNode extends DefaultMutableTreeNode {
    
    String description;
    /*
    Parent classen har redan nån typ av "level" så jag kallar detta fält för
    biolevel för att skilja
    */
    String bioLevel; 
    
    
    public MyNode(String argName, String argLevel, String argDesc){
        super(argName);
        bioLevel = argLevel;
        description = argDesc;
    }
    
    public String getBioLevel(){
        return bioLevel;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getRundown(){
        return bioLevel +": "+this.toString()+ " "+description;
    }
    
}
