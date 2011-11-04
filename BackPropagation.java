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

        
        double eta = 0.1;
        int runs = 10000;

        /*
         * 2 = neurons on the hidden layer
         * 2 = size a single input example
         * 1 = neuron on the output layer
         */
        NeuralNetwork nn = new NeuralNetwork(4,2,1,X,Y,eta);

        nn.initialize();    // Randomizing the weights
        nn.run(runs);       // Running the algorithm with runs iterations

        System.out.println("Running the Neural Network "+runs+" iterations\n");

        int right = 0;
        int wrong = 0;

        for (int i=0; i<X.length; i++) {

            // Feed forward for the examples
            nn.feedforward(i);

            // Neural Network output
            double ans = nn.net_out[0];

            if (((ans > 0.5) && (Y[i]==1.0)) || ((ans < 0.5) && (Y[i]==0.0)))
                right++;
            else
                wrong++;

        }

        System.out.println("Right Classifications: "+right+ "("+(float)right/(float)X.length*100+"%)");
        System.out.println("Wrong Classifications: "+wrong+ "("+(float)wrong/(float)X.length*100+"%)");


    }

}
