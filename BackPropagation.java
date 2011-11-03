/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

/**
 *
 * @author jose
 */
public class BackPropagation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* XOR INPUT <---- Have to be erased */

        double [][] x = new double[4][2];

        x[0][0] = 0;
        x[0][1] = 0;
        x[1][0] = 0;
        x[1][1] = 1;
        x[2][0] = 1;
        x[2][1] = 0;
        x[3][0] = 1;
        x[3][1] = 1;

        double [] y = new double[4];

        y[0] = 0;
        y[1] = 1;
        y[2] = 1;
        y[3] = 0;

        /* End XOR INPUT <---- Have to be erased */

        
        NeuralNetwork nn = new NeuralNetwork(2,2,x,y);
        nn.initialize();
        nn.run();
        System.out.println("");

    }

}
