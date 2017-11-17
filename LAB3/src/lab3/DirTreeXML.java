package lab3;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;        
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class DirTreeXML extends JFrame implements ActionListener {

    /*
    The quick rundown är att jag använder en rekursiv metod för att skapa
    nod-träden, och sen använder jag de färdigkodade ramprogrammet för att 
    visa mitt träd.
    */

    
   public DirTreeXML() {
       
      Container c = getContentPane(); // Tror den här är inherited från jframe
      
      
      try{
        root = readNode();
      }catch(FileNotFoundException e){
          /*
          Det här är om filen ej kan hittas, vilket ej bör hända för oss.
          */
          System.out.println("Något måste gått fel lol.");
      }catch(unbalancedTagsException e){
          /* 
          Lite redundant exception, man hade kunnat trycka in det i XMLformat
          */
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
     // buildTree();
      
      //*** panel the JFrame to hold controls and the tree
      controls = new JPanel();
      box = new JCheckBox( showString );
      
      init(); //** set colors, fonts, etc. and add buttons
      c.add( controls, BorderLayout.NORTH );
      c.add( tree, BorderLayout.CENTER );   
      
      setVisible( true ); //** display the framed window
   } 

   
   public void actionPerformed( ActionEvent e ) {
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
      
      //Hämtar noden man klickat på
      MyNode infoNode = (MyNode)p.getLastPathComponent();
      
      /*
      Den här delen sköter C-uppgift kravet "...som är ... ... som är ..."
      */
      String cPrint = infoNode.toString();
      MyNode parentNode = (MyNode)infoNode.getParent();
      while(!(parentNode==null)){
          cPrint = cPrint+" som är "+parentNode.toString();
          parentNode=(MyNode)parentNode.getParent();
      }

      //Här skickas stringsen som skall visas in
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
       /*
       Det här är en rage-coding shitfest men den if checkar mer eller mindre
       alla möjliga fuckups i XML formatteringen, på en linje.
       
       Angående indexOf(): Returnerar -1 om tecknet, eller stringen, ej finns.
       
       Angående .split("TECKEN",integer): splittar upp en string till en array
       som är integer lång, vid varje TECKEN ( tills den är integer lång)
       */
       String checkedLine = argLine;
       String[] splitLine = checkedLine.split("");
       //System.out.println(checkedLine); // debug
       
       int rightArrowIndex = checkedLine.indexOf(">");
       if(rightArrowIndex==-1){
           throw new XMLFormatException("Line missing an \">\".");
       }
       
       /*
       Det här if-et är om linjen specifikt är en slut-tagg
       */
       if(splitLine[1].equals("/")){
            if(checkedLine.indexOf(" ")!= -1 ||
                    rightArrowIndex!=(checkedLine.length()-1) ){
                throw new XMLFormatException("Wrong close-tag formatting.");
            }
        return; // man ska ej kolla resten isf så returnera här
        }
       
       // varje linje skall börja på <
       if(!splitLine[0].equals("<")){
           throw new XMLFormatException("Line missing an opening \"<\".");
       }
       
       /*
       Den här delen checkar mest ordningen o så, fan läs det själv.
       */
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
       /*
       De här är en shitfest metod som kollar om man sprungit in i fel slut-tag
       Dvs att taggarna är obalanserade, eller fel, eller nåt
       */
        if(nextLine.split("")[1].equals("/")&& 
            !cleanLine(nextLine.split(" ")[0]).equals(endKey)){
                    throw new unbalancedTagsException(
                            ".xml file does not have valid"
                                    + " tags!");
       }
       
       
   }
   public String handleNextLine(Scanner argScanner, String endKey) 
           throws unbalancedTagsException, XMLFormatException{
       /*
       Denna metod hanterar stegandet för scannern, men checkar även efter fel
       i xml filen.
       */
       String nextLine = argScanner.nextLine();
       lineChecker(nextLine);
       endKeyCheck(endKey, nextLine);
       
       return nextLine;
       
   }
   public String handleNextLine(Scanner argScanner) throws XMLFormatException{
       /*
       Samma som förra men när man ej ska, eller behöver, checka endKey
       */
       String nextLine = argScanner.nextLine();
       lineChecker(nextLine);
       
       return nextLine;
   }
   public MyNode readNode() throws FileNotFoundException,
           unbalancedTagsException, XMLFormatException{
       /*
       De här är en argumentlösa versionen, som startar rekursionen
       */
       
       Scanner xmlScanner = new Scanner(new File("Liv.xml")); // läser filen
       xmlScanner.nextLine(); // Man måste hoppa över xml version linjen.
       String holdLine = handleNextLine(xmlScanner);// första raden
       holdLine = cleanLine(holdLine);
       String tempLine ="this is a placeholder line"; //exakt vad de står
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
       
       /*
       Den här versionen med argument är basically samma, fast används
       rekursivt.
       */
       String holdLine = cleanLine(argLine);
       Scanner xmlScanner = argScanner;
       
       String[] argList = holdLine.split(" ",3);
       
       if(argList.length>1){ // kommer ej ihåg varför
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
              else{ // får den slut på listan innan den hittat sin sluttagg
                  // är de ju problem
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
