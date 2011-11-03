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
    
    public int num_neurons;             // Number of neurons
    public int size_in;                 // Size of the input vector x
    public int netOutputSize;           // Number of output neurons

    public double win[][];              // Input weights
    public double wout[][];             // Output weights
    public double net_out[];            // Network Outputs

    public double errorTermNOVector[];  // Output neurons errors
    public double errorTermHUVector[];  // Hidden-layer neurons errors
    public double x_in[][];             // Input
    public double x_out[][];            // Output from the hidden-layer neurons
    public double y[];                  // Target
    public double eta;                  // Learning rate
    
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
        this.wout = new double[num_n][netOutputSize+1];
        this.win = new double[num_n][size_i+1];
        this.eta = eta;
        this.net_out = new double[netOutputSize];
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

        int its = 0;
        int example = 0; // Example number

        // Running until the network classify correctly all the examples
        // from the training set
        //while (!stopcondition) {

            // Running all the hidden layer neurons

            // Loop over all the neurons in the hidden layer
            for (int i = 0; i<this.num_neurons; i++){
                double net = 0;
                // Loop over all the inputs/weights
                for (int j = 0; j<this.size_in; j++){
                    net += this.x_in[example][j]*win[i][j];
                }
                // Adding the threshold
                net += this.win[i][this.win.length-1];
                x_out[i][0] = sigmoid(net);
            }

            // Running the output(s) neuron(s)

            for (int z = 0; z<this.netOutputSize; z++){

                double netfinal = 0;
                // Loop over all the output layer neurons results
                for (int i = 0; i<this.x_out.length; i++){
                    netfinal += this.x_out[i][0]*this.wout[0][i];
                }
                netfinal += this.wout[0][this.wout.length-1];

                // Saving the network output
                net_out[z] = sigmoid(netfinal);

            }

            // Checking if the output is correct
            ///////////////// Condition here

            // Backpropagating the errors
            errorTermNO(net_out,y);
            errorTermHU();

            // Updating the weights
            updateNetworkWeights(x_in[example]);
            
        //} // Terminating condition reached
    }
    
    // Sigmoidal Function
    double sigmoid(double net){
        return (1/( 1 + Math.pow(Math.E,(-1*net))));
    }
    
    // Error term for a network output.
    void errorTermNO(double[] output, double[] target){
        for(int i = 0; i < this.netOutputSize; i++){
            this.errorTermNOVector[i] = output[i]*(1 - output[i])*
                        (target[i] - output[i]);
        }
    }
    
    // Error term for all the h hidden units.
    void errorTermHU(){
        double sum;
        for(int i = 0; i < this.num_neurons; i++){
            sum = 0.0;
            for(int j = 0; j < this.netOutputSize; j++){
                sum += this.wout[i][j]*this.errorTermNOVector[j];
            }
            this.errorTermHUVector[i] = x_out[i][0]*(1-x_out[i][0])*sum;
        }
    }
    
    // Updates each network weights
    void updateNetworkWeights(double x_input[]){
        // Start first with output unit weights
        for(int j = 0; j < this.netOutputSize; j++){
            for(int i = 0; i < this.num_neurons; i++){
                this.wout[j][i] += calcDeltaWeight(this.errorTermNOVector[j],
                        this.x_out[i][j]);
            }
            // Modifying the threshold for the output neurons
            this.wout[j][this.num_neurons-1] = calcDeltaWeight(this.errorTermNOVector[j],
                        1);
        }
        // Now proceed with input unit weights
        for(int j = 0; j < this.num_neurons; j++){
            for(int i = 0; i < this.size_in; i++){
                this.win[j][i] += calcDeltaWeight(this.errorTermHUVector[j],
                        x_input[i]);
            }
        }
    }
    
    // Calculates delta weight for a given weight[i][j]
    double calcDeltaWeight(double errorTerm, double x_input){
        return (this.eta*errorTerm*x_input);
    }

}
