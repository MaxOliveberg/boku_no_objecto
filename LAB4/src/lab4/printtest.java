/*
Det här är en testklass

 */
package lab4;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author moliv
 */
public class printtest {

    /**
     * @param args the command line arguments
     */

  public static void main(String[] args) throws IOException {

    PrintWriter printWriter = new PrintWriter ("file.txt");
    printWriter.println ("hello");
    printWriter.println ("hello2");
    printWriter.close ();       
  }
}

