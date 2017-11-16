package lab3;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;        
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DirTreeXML extends JFrame implements ActionListener {


    
   public DirTreeXML() {
       
      Container c = getContentPane(); // Tror den här är inherited från jframe
      
      
      try{
        root = readNode();
      }catch(FileNotFoundException e){
          System.out.println("Något måste gått fel lol.");
      }catch(unbalancedTagsException e){
          System.out.println(e);
      }catch(XMLFormatException e){
          System.out.println(e);
      }
      treeModel = new DefaultTreeModel( root );
      tree = new JTree( treeModel );

      MouseListener ml = 
        new MouseAdapter() {
          public void mouseClicked( MouseEvent e ) {
            if ( box.isSelected() ) // box är showdetails lådan längst upp
              showDetails( tree.getPathForLocation( e.getX(), 
                                                    e.getY() ) );
          }
        };
      tree.addMouseListener( ml );
      
      //*** build the tree by adding the nodes
      buildTree();
      
      //*** panel the JFrame to hold controls and the tree
      controls = new JPanel();
      box = new JCheckBox( showString );
      
      init(); //** set colors, fonts, etc. and add buttons
      c.add( controls, BorderLayout.NORTH );
      c.add( tree, BorderLayout.CENTER );   
      
      setVisible( true ); //** display the framed window
   } 

   
   public void actionPerformed( ActionEvent e ) {
      /*
       Se init() och addButton() för mer info på detta, men knappen lyssnar
       på det här programmet uppenbarligen. Det är möjligt att om man
       endast gör en knappt med en string så sätts knappens "actioncommand" till
       samma string som står på knappen. Pallar ej gräva i det just nu.
       */
      String cmd = e.getActionCommand();
      if ( cmd.equals( closeString ) )
        dispose();
   }

   private void init() {
      tree.setFont( new Font( "Dialog", Font.BOLD, 12 ) );
      controls.add( box );
      addButton( closeString );
      controls.setBackground( Color.lightGray );
      controls.setLayout( new FlowLayout() );    
      setSize( 400, 400 );
   }

   private void addButton( String n ) {
      JButton b = new JButton( n );
      b.setFont( new Font( "Dialog", Font.BOLD, 12 ) );
      b.addActionListener( this );
      controls.add( b );
   }

   private void buildTree() {
       /*
       För mig var det lite oklart här exakt hur snyggt programmet skulle vara.
       Man kan ju hardcodea att den skall lägga till en massa noder, vilket jag
       gjort, men man hade ju även kunnat få den att läsa ut från nån lista 
       eller w/e. Jag tolkade e1.2 formuleringen som att de var okej att 
       hardcodea.
       
       "I metoden buildTree() suddar ni allt och skriver nya satser som 
       tillverkar en nod child på samma sätt som rotnoden men med texten Växter.
       Addera den till trädmodellen med root som förälder. Gör likadant med djur
       och svampar."
       
       */
      String[] subFolders = {"Växter","Svampar","Djur"};
      buildTree(root, subFolders); 
   }

   private void buildTree(DefaultMutableTreeNode parent, String[] childList){
       for(int i =0; i< childList.length; i++){
           DefaultMutableTreeNode child = 
                   new DefaultMutableTreeNode( childList[i] );
           parent.add(child);
       }
   }
   /*
   private void buildTree( File f, DefaultMutableTreeNode parent) {

      DefaultMutableTreeNode child = 
         new DefaultMutableTreeNode( f.toString() );
      parent.add(child);
      if ( f.isDirectory() ) {
        String list[] = f.list();
        for ( int i = 0; i < list.length; i++ )
           buildTree( new File(f,list[i]), child);            
      }        
   }    
*/
   private void showDetails( TreePath p ) {
       /*
       Treepath är en array some representerar "vägen" till en nod i ett träd.
       Dokumentation:
       https://docs.oracle.com/javase/7/docs/api/javax/swing/tree/TreePath.html
       */
      if ( p == null ) // crashskydd typ
        return;
      MyNode infoNode = (MyNode)p.getLastPathComponent();
      String cPrint = infoNode.toString();
      MyNode parentNode = (MyNode)infoNode.getParent();
      while(!(parentNode==null)){
          cPrint = cPrint+" som är "+parentNode.toString();
          parentNode=(MyNode)parentNode.getParent();
      }

      JOptionPane.showMessageDialog( this, infoNode.getRundown()
                                    + "\n"+
                                    cPrint);
   }


   public String cleanLine(String inputString){
       /*
       Den här funktionen rensar line-stringen från alla jobbiga tecken
       */
        String holdLine = inputString;
        holdLine =holdLine.replace("<","");
        holdLine =holdLine.replace(">","");
        holdLine = holdLine.replace("namn=","");
        /*
        Rensar " INTE  \   !!!
        */
        holdLine= holdLine.replace("\"","");
        return holdLine;
   }
   public static void main( String[ ] args ) {
       if(args.length>0) katalog=args[0];
       new DirTreeXML();
   }


   public void lineChecker(String argLine) throws XMLFormatException{
       String checkedLine = argLine;
       String[] splitLine = checkedLine.split("");
       //System.out.println(checkedLine); // debug
       
       int rightArrowIndex = checkedLine.indexOf(">");
       if(rightArrowIndex==-1){
           throw new XMLFormatException("Line missing an \">\".");
       }
       if(splitLine[1].equals("/")){
            if(checkedLine.indexOf(" ")!= -1 ||
                    rightArrowIndex!=(checkedLine.length()-1) ){
                throw new XMLFormatException("Wrong close-tag formatting.");
            }
        return;
        }
       if(!splitLine[0].equals("<")){
           throw new XMLFormatException("Line missing an opening \"<\".");
       }
       

       int nameIndex =checkedLine.indexOf("namn=");
       int firstCitIndex = checkedLine.indexOf("\"");
       int secondCitIndex = checkedLine.indexOf("\"",firstCitIndex+1);
       
       if(nameIndex == -1){
           throw new XMLFormatException("Line missing a name.");
       }
       
       if(firstCitIndex ==-1 || secondCitIndex == -1){
           throw new XMLFormatException("Line missing a \".");
       }
       if((nameIndex+5 != firstCitIndex)||(secondCitIndex+1) != rightArrowIndex){
           throw new XMLFormatException("Line not ordered properly.");
       }
   }
   
   public void endKeyCheck(String endKey, String nextLine) throws
           unbalancedTagsException{
        System.out.println(cleanLine(nextLine.split(" ")[0]) + " v s " +endKey);
        if(nextLine.split("")[1].equals("/")&& 
            !cleanLine(nextLine.split(" ")[0]).equals(endKey)){
                    throw new unbalancedTagsException(
                            ".xml file does not have valid"
                                    + " tags!");
       }
       
       
   }
   public String handleNextLine(Scanner argScanner, String endKey) 
           throws unbalancedTagsException, XMLFormatException{
       String nextLine = argScanner.nextLine();
       lineChecker(nextLine);
       endKeyCheck(endKey, nextLine);
       
       return nextLine;
       
   }
   public String handleNextLine(Scanner argScanner) throws XMLFormatException{
       
       String nextLine = argScanner.nextLine();
       lineChecker(nextLine);
       
       return nextLine;
   }
   public MyNode readNode() throws FileNotFoundException,
           unbalancedTagsException, XMLFormatException{
       
       Scanner xmlScanner = new Scanner(new File("Liv.xml"));
       xmlScanner.nextLine(); // Man måste hoppa över xml version linjen.
       String holdLine = handleNextLine(xmlScanner);
       holdLine = cleanLine(holdLine);
       String tempLine ="this is a placeholder line";
       String endKey = "/"+ holdLine.split(" ")[0];
       String[] argList = holdLine.split(" ",3);
       if(argList.length>1){

         MyNode retNode = new MyNode(argList[1],argList[0],argList[2]);
         tempLine = handleNextLine(xmlScanner, endKey);
         while(!cleanLine(tempLine).split(" ")[0].equals(endKey)){
             
            recNode(retNode, tempLine,endKey,xmlScanner);
            
             tempLine = handleNextLine(xmlScanner);
         }    
        return retNode;
       }
       return null; // bör aldrig hända med en riktigt formatterad lista
   }
   
   public void recNode(MyNode parent, String argLine, String
           parentEndKey, Scanner argScanner) 
           throws unbalancedTagsException, XMLFormatException{
       String holdLine = cleanLine(argLine);
       Scanner xmlScanner = argScanner;
       
       String[] argList = holdLine.split(" ",3);
       if(argList.length>1){
          String endKey = "/"+ argList[0];
          MyNode retNode = new MyNode(argList[1],argList[0],argList[2]);
          String tempLine = handleNextLine(xmlScanner,endKey);
          //System.out.println(argList[1]);
          
          parent.add(retNode);
          while(!cleanLine(tempLine).split(" ")[0].equals(endKey)){
              recNode(retNode, tempLine,endKey,xmlScanner);
              if(xmlScanner.hasNext()){
                 tempLine = handleNextLine(xmlScanner, endKey);
              }
              else{
                  throw new unbalancedTagsException(".xml file does not have"
                          + " balanced tags!");
              }
       }
           
       }
       
       
   }
   
   
   private JCheckBox box;
   private JTree tree;
   private DefaultMutableTreeNode root;
   private DefaultTreeModel treeModel;
   private JPanel controls;
   private static String katalog="Liv";
   private static final String closeString = " Close ";
   private static final String showString = " Show Details ";
}
