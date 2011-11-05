/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

import java.io.*;
import java.util.Vector;

/**
 *
 * @author jose
 */
public class FileReader {

    public String filename;
    public Vector<String []> file;
    public int x_length;

    public FileReader(String name){
        this.filename = name;
    }

    // Reads the whole input file
    void read(){

        Vector<String []> f = new Vector<String []>();

        try {
            FileInputStream fstream = new FileInputStream(
                    filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;

            while ((strLine = br.readLine()) != null)   {
                String [] line = strLine.split(" ");
                f.add(line);
                this.x_length = line.length;
            }

            in.close();
        } catch (Exception e) { 
            System.err.println("Error: " + e.getMessage());
        }

        this.file = f;

    }

    // Returns the X input matrix to be used in the backpropagation algorithm
    double[][] returnX(){
        double [][] x = new double[this.file.size()][this.x_length-1];
        for (int i = 0; i < this.file.size(); i++){
            String [] temp = file.get(i);
            for (int j = 0; j<temp.length-1; j++){
                x[i][j] = Double.parseDouble(temp[j]);
            }
        }
        return x;
    }

    // Returns the Y vector to be used in the backpropagation algorithm
    double[] returnY(){
        double [] y = new double[this.file.size()];
        for (int i = 0; i < this.file.size(); i++){
            String [] temp = file.get(i);
            // A -> 0
            // B -> 1
            if (temp[temp.length-1].equals("A"))
                y[i] = 0;
            else
                y[i] = 1;
        }
        return y;
    }

}
