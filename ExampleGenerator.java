/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralnetwork;

import java.util.Random;
import neuralnetwork.FileWriter;

/**
 *
 * @author heraclio
 */
public class ExampleGenerator {
    int size;
    double rxmin;
    double rxmax;
    double rymin;
    double rymax;
    double cxmin;
    double cxmax;
    double cymin;
    double cymax;
    
    public ExampleGenerator(int size, double rxmin, double rxmax,
            double rymin, double rymax, double cxmin, double cxmax,
            double cymin, double cymax) {
        this.size = size;
        this.rxmin = rxmin;
        this.rxmax = rxmax;
        this.rymin = rymin;
        this.rymax = rymax;
        this.cxmin = cxmin;
        this.cxmax = cxmax;
        this.cymin = cymin;
        this.cymax = cymax;
        
    }
    
    public void generate(){
        Random randomGenerator = new Random();
        double[] xrectangle = new double[this.size/2];
        double[] yrectangle = new double[this.size/2];
        double[] xcircle = new double[this.size/2];
        double[] ycircle = new double[this.size/2];
        // Generate points for rectangle
        int i = 0;
        double x;
        double y;
        double check;
        while (i < this.size/2){
            while(true){
                x = randomGenerator.nextDouble()*(this.rxmax - this.rxmin + 1);
                y = randomGenerator.nextDouble()*(this.rymax - this.rymin + 1);
                check = Math.pow(x - 15, 2) + Math.pow(y - 6.0, 2);
                if (check != 9.0) {
                    xrectangle[i] = x;
                    yrectangle[i] = y;
                    break;
                }
            }
            i++;
        }
        
        // Generate points for rectangle
        i = 0;
        while (i < this.size/2) {
            while(true){
                x = randomGenerator.nextDouble()*(this.cxmax - this.cxmin + 1)
                        + this.cxmin;
                y = randomGenerator.nextDouble()*(this.cymax - this.cymin + 1)
                        + this.cymin;
                check = Math.pow(x - 15, 2) + Math.pow(y - 6, 2);
                if (check <= 9.0) {
                    xcircle[i] = x;
                    ycircle[i] = y;
                    break;
                }
            }
            i++;
        }
        // Creating file
        FileWriter f = new FileWriter(Integer.toString(this.size)+
                "_validacion.txt");
        // Writing rectangle's coordinates
        for(int j = 0; j < this.size/2; j++){
            f.write(Double.toString(xrectangle[j])+" "+
                    Double.toString(yrectangle[j])+" A");
        }
        // Writing circle's coordinates
        for(int j = 0; j < this.size/2; j++){
            f.write(Double.toString(xcircle[j])+" "+
                    Double.toString(ycircle[j])+" B");
        }
    }

}
