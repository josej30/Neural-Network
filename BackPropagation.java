/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;

/**
 *
 * @author jose y lfundaro
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

        // Reading the input file
        FileReader fr = new FileReader("500.txt");
        fr.read();
        // Retrieving the x and y vectors
        double [][] X = fr.returnX();
        double [] Y = fr.returnY();


        
        double eta = 0.5;
        int runs = 5;

        /*
         * 2 = neurons on the hidden layer
         * 2 = size a single input example
         * 1 = neuron on the output layer
         */
        NeuralNetwork nn = new NeuralNetwork(2,2,1,X,Y,eta);

        nn.initialize();    // Randomizing the weights
        //nn.run(runs);       // Running the algorithm with runs iterations

        System.out.println("Running the Neural Network "+runs+" iterations\n");

        // Checking for right answers
        nn.feedforward(0);
        System.out.println("Network Output [0]: "+nn.net_out[0]);
        System.out.println("Expected Output [0]: "+Y[0]);
        System.out.println("-----");
        nn.feedforward(1);
        System.out.println("Network Output [1]: "+nn.net_out[0]);
        System.out.println("Expected Output [1]: "+Y[1]);
        System.out.println("-----");
        nn.feedforward(2);
        System.out.println("Network Output [2]: "+nn.net_out[0]);
        System.out.println("Expected Output [2]: "+Y[2]);
        System.out.println("-----");
        nn.feedforward(3);
        System.out.println("Network Output [3]: "+nn.net_out[0]);
        System.out.println("Expected Output [3]: "+Y[3]);


    }

}
