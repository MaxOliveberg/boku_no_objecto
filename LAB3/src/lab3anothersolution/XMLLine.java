package lab3anothersolution;

import lab3.XMLFormatException;

/**
 * Sparar relevant information från en rad i xml-filen. Hittar också fel i xmlfilen.
 */

public class XMLLine {
    private boolean closed;
    private String name;
    private String type;
    private String description;

    public XMLLine(String line) throws XMLFormatException {
        setAll(line);
    }

    private void setAll(String line) throws XMLFormatException {
        String workLine = line.trim(); //vi tillåter alltså whitespace innan och efter ett gilltigt utryck på en rad!
        int start = getStartIndex(workLine);
        int end = getEndIndex(workLine);

        String typeAndName = workLine.substring(start, end);
        setClosed(typeAndName);
        if (closed) {
            type = typeAndName.substring(1);
        } else {
            setTypeAndName(typeAndName);
            description = workLine.substring(end+1);
        }

    }

    private int getStartIndex(String line) throws XMLFormatException {
        int start = line.indexOf("<");
        if (start == 0) {
            return start+1;
        }
        throw new XMLFormatException("Line: "+line+" does not start with \"<\".");
    }

    private int getEndIndex(String line) throws XMLFormatException {
        int end = line.indexOf(">");
        if (end > 0) {
            return end;
        }
        throw new XMLFormatException("Line: "+line+", does not contain \">\".");
    }

    private void setClosed(String line) {
        closed = line.startsWith("/");
    }

    private void setTypeAndName(String line) throws XMLFormatException {
        if (!line.contains(" namn=")){
            throw new XMLFormatException("The line "+line+" is wrong.");
        }
        String[] holdString = line.split(" namn=");

        type = holdString[0];
        if (holdString[1].startsWith("\"") && holdString[1].endsWith("\"")) {
            name = holdString[1].replace("\"","");
        }
        else {
            throw new XMLFormatException("Input: "+line+", does not contain proper quatationmarks.");
        }
    }

    public boolean isClosed() {
        return closed;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}

