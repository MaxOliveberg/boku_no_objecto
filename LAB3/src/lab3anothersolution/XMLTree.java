package lab3anothersolution;

import lab3.MyNode;
import lab3.XMLFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Har som uppgift att skapa ett nodträd av en xmlfil. Det är här som rekursionen sker.
*/
public class XMLTree {
    private XMLReader xmlReader;
    private MyNode root;
    private int imovableCounter = 0;

    public XMLTree() throws FileNotFoundException, XMLFormatException {
        File liv = new File("Liv.xml");
        if (!liv.canRead()) {
            throw new FileNotFoundException("Could not find the file: " + liv);
        }
        Scanner livScanner = new Scanner(liv);
        xmlReader = new XMLReader(livScanner);
        root = ReadNode();
    }

    public MyNode getRoot() {
        return root;
    }

    private MyNode ReadNode() throws XMLFormatException {
        XMLLine currentXMLLine = xmlReader.getCurrentXMLLine();
        MyNode currentNode = CreateNode(currentXMLLine);

        while (!xmlReader.isNextEndTag()) {
            xmlReader.moveForward();
            currentNode.add(ReadNode());
        }

        String startType = currentXMLLine.getType();
        String endType = xmlReader.getNextXMLLine().getType();

        if (!startType.equals(endType)) {
            throw new XMLFormatException("Start and end tag does not match. We excpect: "+startType+", and we got: "+endType+".");
        }

        if (xmlReader.canMoveForward()) {
            xmlReader.moveForward();
        }
        else { //denna else sats är nödvändig om vi tillåter en typ av tag att ligga i samma typ av tag, tex Rike kan ligga i Rike.
            if (imovableCounter > 0) {
                throw new XMLFormatException("Unmatched amount of start tags and end tags.");
            }
            imovableCounter++;
        }

        return currentNode;
    }

    private MyNode CreateNode(XMLLine xmlLine) throws IllegalArgumentException {
        if (xmlLine.isClosed()) {
            return null;
        }
        String name = xmlLine.getName();
        String type = xmlLine.getType();
        String description = xmlLine.getDescription();
        return new MyNode(name,type,description);
    }
}
