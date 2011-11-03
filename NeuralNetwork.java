/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

import java.util.Random;

/**
 *
 * @author jose
 */
public class NeuralNetwork {

    public int num_neurons;
    public int size_in;
    public double win[][];
    public double wout[];
    public double x[][];
    public double y[];

    public NeuralNetwork( int num_n, int size_i, double [][] x, double [] y){
        this.num_neurons = num_n;
        this.size_in = size_i;
        this.x = x;
        this.y = y;
        this.wout = new double[num_n];
        this.win = new double[num_n][size_i];
    }

    // Initializing the variables of the Neural Network
    public void initialize(){
        Random randomGenerator = new Random();

        // Initializing the w array for the hidden layer output
        for (int i=0;i<this.wout.length;i++)
            wout[i] = randomGenerator.nextDouble();

        // Initializing the w array for the hidden layer input
        for (int i=0;i<this.win.length;i++)
            for (int j=0;j<this.win[i].length;j++)
            win[i][j] = randomGenerator.nextDouble();

    }

    // Running the backpropagation algorithm
    void run(){
        
    }

    // Sigmoidal Function
    double sigmoid(double net){
        return (1/( 1 + Math.pow(Math.E,(-1*net))));
    }

}
