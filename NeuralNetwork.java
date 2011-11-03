/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

/**
 *
 * @author jose
 */
public class NeuralNetwork {

    public int num_neurons;
    public int size_in;
    public double win[][];
    public double wout[];

    public NeuralNetwork(int num_n,int size_i){
        this.num_neurons = num_n;
        this.size_in = size_i;
        this.wout = new double[num_n];
        this.win = new double[num_n][size_i];
    }

}
