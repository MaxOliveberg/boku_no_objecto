package lab3anothersolution;

import lab3.XMLFormatException;

import java.util.Scanner;
/**
 * Denna klass har som uppgift att läsa igenom en xmlfil och vi ska kunna plocka relevant infomation från varje rad som
 * vi går igenom.
 */
public class XMLReader {
    private static final String XML_INFO = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private Scanner scanner;
    private XMLLine currentXMLLine;
    private XMLLine nextXMLLine;

    public XMLReader(Scanner scanner) throws XMLFormatException {
        String line = scanner.nextLine();
        while (line.equals(XML_INFO) || line.equals("")) {
            line = scanner.nextLine();
        }
        this.scanner = scanner;
        currentXMLLine = new XMLLine(line);
        nextXMLLine = new XMLLine(getNextLine());
    }

    public void moveForward() throws XMLFormatException {
        currentXMLLine = nextXMLLine;
        nextXMLLine = new XMLLine(getNextLine());
    }

    public boolean isNextEndTag() {
        return nextXMLLine.isClosed();
    }

    public boolean canMoveForward() {
        return scanner.hasNext();
    }

    private String getNextLine() throws XMLFormatException {
        if (scanner.hasNext()) {
            return scanner.nextLine();
        }
        throw new XMLFormatException("XMLReader is empty");
    }

    public XMLLine getCurrentXMLLine() {
        return currentXMLLine;
    }

    public XMLLine getNextXMLLine() {
        return nextXMLLine;
    }
}
