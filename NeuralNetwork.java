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
    public int netOutputSize;
    public double win[][];
    public double wout[][];
    public double errorTermNOVector[];
    public double errorTermHUVector[];
    public double x_in[][];
    public double x_out[][];
    public double y[];
    public double eta;
    
    public NeuralNetwork( int num_n, int size_i, int netOutputSize,
            double [][] x, double [] y, double eta){
        this.num_neurons = num_n;
        this.size_in = size_i;
        this.netOutputSize = netOutputSize;
        this.errorTermNOVector = new double[netOutputSize];
        this.errorTermHUVector = new double[num_n];
        this.x_in = x;
        this.x_out = new double[num_n][netOutputSize];
        this.y = y;
        this.wout = new double[num_n][netOutputSize];
        this.win = new double[num_n][size_i];
        this.eta = eta;
    }
    
    // Initializing the variables of the Neural Network
    public void initialize(){
        Random randomGenerator = new Random();
        
        // Initializing the w array for the hidden layer output
        for (int i = 0; i<this.num_neurons; i++){
            for(int j = 0; j < this.netOutputSize; j++){
                wout[i][j] = randomGenerator.nextDouble();
            }
        }
        
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
    
    // Error term for a network output.
    void errorTermNO(double[][] output, double[] target){
        for(int i = 0; i < this.num_neurons; i++){
            for(int j = 0; j < this.netOutputSize; j++){
                this.errorTermNOVector[j] = output[i][j]*(1 - output[i][j])*
                        (target[i] - output[i][j]);
            }
        }
    }
    
    // Error term for all the h hidden units.
    void errorTermHU(double[] output_h){
        double sum;
        for(int i = 0; i < this.num_neurons; i++){
            sum = 0.0;
            for(int j = 0; j < this.netOutputSize; j++){
                sum += this.wout[i][j]*this.errorTermNOVector[j];
            }
            this.errorTermHUVector[i] = output_h[i]*(1-output_h[i])*sum;
        }
    }
    
    // Updates each network weights
    void updateNetworkWeights(){
        // Start first with output unit weights
        for(int i = 0; i < this.num_neurons; i++){
            for(int j = 0; j < this.netOutputSize; j++){
                this.wout[i][j] += calcDeltaWeight(this.errorTermNOVector[j],
                        this.x_out[i][j]);
            }
        }
        // Now proceed with input unit weights
        for(int i = 0; i < this.size_in; i++){
            for(int j = 0; i < this.num_neurons; j++){
                this.win[i][j] += calcDeltaWeight(this.errorTermHUVector[j],
                        this.x_in[i][j]);
            }
        }
    }
    
    // Calculates delta weight for a given weight[i][j]
    double calcDeltaWeight(double errorTerm, double x_input){
        return (this.eta*errorTerm*x_input);
    }

}
