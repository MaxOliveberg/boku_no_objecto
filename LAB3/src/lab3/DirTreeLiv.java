package lab3;

import javax.swing.*;
import javax.swing.tree.*;
import java.io.*;        
import java.awt.*;
import java.awt.event.*;

public class DirTreeLiv extends JFrame implements ActionListener {

    /*
    Fält definierade längst ner
    */
    
   public DirTreeLiv() {
       
      Container c = getContentPane(); // Tror den här är inherited från jframe
      
      //*** Build the tree and a mouse listener to handle clicks
      
      //Bygger trädet ?
      root = new DefaultMutableTreeNode(katalog);
      treeModel = new DefaultTreeModel( root );
      tree = new JTree( treeModel );
      
      // sätter upp musen
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
      File f = new File( p.getLastPathComponent().toString() );
      JOptionPane.showMessageDialog( this, f.getPath() + 
                                     "\n   " + 
                                     getAttributes( f ) );
   }

   private String getAttributes( File f ) {
       /*
       Denna metoden samlar in info på en fil som skickas in hit genom
       showDetails() metoden.
       */
      String t = "";
      if ( f.isDirectory() )
        t += "Directory";
      else
        t += "Nondirectory file";
      t += "\n   ";
      if ( !f.canRead() )
        t += "not ";
      t += "Readable\n   ";
      if ( !f.canWrite() )
        t += "not ";
      t += "Writeable\n  ";
      if ( !f.isDirectory() )
        t += "Size in bytes: " + f.length() + "\n   ";
      else {
        t += "Contains files: \n     ";
        String[ ] contents = f.list();
        for ( int i = 0; i < contents.length; i++ )
           t += contents[ i ] + ", ";
        t += "\n";
      } 
      return t;
   }

   public static void main( String[ ] args ) {
       if(args.length>0) katalog=args[0];
       new DirTreeLiv();
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
