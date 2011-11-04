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
        this.wout = new double[num_n+1][netOutputSize];
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
        for (int i = 0; i < this.num_neurons; i++)
            for (int j = 0; j < this.size_in + 1; j++)
                win[i][j] = randomGenerator.nextDouble();
        
    }
    
    // Running the backpropagation algorithm
    void run(int runs){

        int its = 0;

        // Running until the network classify correctly all the examples
        // from the training set
        while (its!=runs) {

            // Running all the examples on the training set
            for (int example = 0; example<this.x_in.length; example++) {

                // Running the neural network
                feedforward(example);

                // Backpropagating the errors
                errorTermNO(example);
                errorTermHU();

                // Updating the weights
                updateNetworkWeights(x_in[example]);

            }

            its++;

        } // Terminating condition reached
    }

    void feedforward(int example) {

        // Running all the hidden layer neurons

        // Loop over all the neurons in the hidden layer
        for (int i = 0; i<this.num_neurons; i++){
            double net = 0;
            // Loop over all the inputs/weights
            for (int j = 0; j<this.size_in; j++){
                net += this.x_in[example][j]*win[i][j];
            }
            // Adding the threshold
            net += this.win[i][this.size_in];
            this.x_out[i][0] = sigmoid(net);
        }

        // Running the output(s) neuron(s)

        for (int i = 0; i<this.netOutputSize; i++){

            double netfinal = 0;
            // Loop over all the output layer neurons results
            for (int j = 0; j<this.num_neurons; j++){
                netfinal += this.x_out[j][i]*this.wout[j][i];
            }
            netfinal += this.wout[this.netOutputSize][i];

            // Saving the network output
            this.net_out[i] = sigmoid(netfinal);

        }

    }
    
    // Sigmoidal Function
    double sigmoid(double net){
        return (1/( 1 + Math.pow(Math.E,(-1*net))));
    }
    
    // Error term for a network output
    void errorTermNO(int example){
        for(int k = 0; k < this.netOutputSize; k++){
            this.errorTermNOVector[k] = this.net_out[k]*(1 - this.net_out[k])*
                        (this.y[example] - this.net_out[k]);
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
            this.errorTermHUVector[i] = this.x_out[i][0]*(1-this.x_out[i][0])*sum;
        }
    }
    
    // Updates each network weights
    void updateNetworkWeights(double x_input[]){
        
        // Start first with output unit weights
        for(int j = 0; j < this.netOutputSize; j++){
            for(int i = 0; i < this.num_neurons; i++){
                this.wout[i][j] += calcDeltaWeight(this.errorTermNOVector[j],
                        this.x_out[i][j]);
            }
            // Modifying the threshold for the output neurons
            this.wout[this.num_neurons][j] += calcDeltaWeight(this.errorTermNOVector[j],1);
        }

        // Now proceed with input unit weights
        for(int i = 0; i < this.num_neurons; i++){
            for(int j = 0; j < this.size_in; j++){
                this.win[i][j] += calcDeltaWeight(this.errorTermHUVector[i],
                        x_input[j]);
            }
            // Modifying the threshold for the input neurons
            this.win[i][this.size_in] += calcDeltaWeight(this.errorTermHUVector[i],1);
        }
    }
    
    // Calculates delta weight for a given weight[i][j]
    double calcDeltaWeight(double errorTerm, double x_input){
        return (this.eta*errorTerm*x_input);
    }

}
