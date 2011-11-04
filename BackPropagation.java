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

        
        double eta = 0.05;
        int runs = 1000;

        /*
         * 2 = neurons on the hidden layer
         * 2 = size a single input example
         * 1 = neuron on the output layer
         */
        NeuralNetwork nn = new NeuralNetwork(2,2,1,X,Y,eta);

        nn.initialize();    // Randomizing the weights
        nn.run(runs);       // Running the algorithm with runs iterations

        System.out.println("Running the Neural Network "+runs+" iterations\n");

        // Checking for right answers
        nn.feedforward(3);
        System.out.println("Ej[3]: "+nn.net_out[0]+" -> "+Y[3]);
        nn.feedforward(110);
        System.out.println("Ej[110]: "+nn.net_out[0]+" -> "+Y[110]);
        nn.feedforward(284);
        System.out.println("Ej[284]: "+nn.net_out[0]+" -> "+Y[284]);
        nn.feedforward(333);
        System.out.println("Ej[333]: "+nn.net_out[0]+" -> "+Y[333]);
        nn.feedforward(401);
        System.out.println("Ej[401]: "+nn.net_out[0]+" -> "+Y[401]);
        nn.feedforward(5);
        System.out.println("Ej[5]: "+nn.net_out[0]+" -> "+Y[5]);


    }

}
