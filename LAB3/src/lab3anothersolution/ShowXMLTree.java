package lab3anothersolution;

import lab3.MyNode;
import lab3.XMLFormatException;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

/**
 * Allt i denna klass är i stort sett som DirTree, förutom att showDetails ändrats för specs på C-delen.
 * Allting irrelevant är också borttaget, till exempel buildTree. Denna klass har inget ansvar för att skapa ett träd
 * utan endast att visa trädet i grafisk form.
*/

public class ShowXMLTree extends JFrame implements ActionListener  {
    private static final String CLOSE_STRING = " Close ";
    private static final String SHOW_STRING = " Show Details ";
    private JCheckBox box;
    private JTree tree;
    private MyNode root;
    private DefaultTreeModel treeModel;
    private JPanel controls;

    public ShowXMLTree() throws XMLFormatException, FileNotFoundException {

        Container c = getContentPane(); // Tror den här är inherited från jframe

        try {
            root = new XMLTree().getRoot();
        }catch(FileNotFoundException e) {
            System.err.println("Could not find the file. "+e.getMessage());
            throw e;
        }catch(XMLFormatException e) {
            System.err.println("Format error. "+e.getMessage());
            throw e;
        }

        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        MouseListener ml =
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        if (box.isSelected()) // box är showdetails lådan längst upp
                            showDetails(tree.getPathForLocation(e.getX(),
                                    e.getY()));
                    }
                };
        tree.addMouseListener(ml);

        controls = new JPanel();
        box = new JCheckBox(SHOW_STRING);

        init(); //** set colors, fonts, etc. and add buttons
        c.add(controls, BorderLayout.NORTH);
        c.add(tree, BorderLayout.CENTER);

        setVisible(true); //** display the framed window
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals(CLOSE_STRING))
            dispose();
    }

    private void init() {
        tree.setFont(new Font("Dialog", Font.BOLD, 12));
        controls.add(box);
        addButton(CLOSE_STRING);
        controls.setBackground(Color.lightGray);
        controls.setLayout(new FlowLayout());
        setSize(400, 400);
    }

    private void addButton(String n) {
        JButton b = new JButton(n);
        b.setFont(new Font("Dialog", Font.BOLD, 12));
        b.addActionListener(this);
        controls.add(b);
    }

    private void showDetails(TreePath p) {
        if (p == null) // crashskydd typ
            return;

        MyNode infoNode = (MyNode) p.getLastPathComponent();

        String cPrint = infoNode.toString();
        MyNode parentNode = (MyNode) infoNode.getParent();
        while (!(parentNode == null)) {
            cPrint = cPrint + " som är " + parentNode.toString();
            parentNode = (MyNode) parentNode.getParent();
        }

        JOptionPane.showMessageDialog(this, infoNode.getRundown()
                + "\n" +
                cPrint);
    }

    public static void main(String[] args) throws XMLFormatException, FileNotFoundException {
        new ShowXMLTree();
    }
}
