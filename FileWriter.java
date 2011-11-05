/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

import java.io.*;

/**
 *
 * @author jose
 */
public class FileWriter {

    public String filename;
    public FileOutputStream out;
    public PrintStream p;

    public FileWriter(String filename) {

        this.filename = filename;

        try {
            this.out = new FileOutputStream(this.filename);
            this.p = new PrintStream( out );
        }
        catch (Exception e) {
                System.err.println ("Error writing to file");
        }

    }

    public void write(String text) {
    
        try {
                this.p.print (text);
        }
        catch (Exception e) {
                System.err.println ("Error writing to file");
        }
    }

    public void closeFile() {
        this.p.close();
    }

}
