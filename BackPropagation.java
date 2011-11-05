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

        // Reading the input file
        FileReader fr = new FileReader("500.txt");
        fr.read();
        // Retrieving the x and y vectors
        double [][] X = fr.returnX();
        double [] Y = fr.returnY();

        boolean output_file = true;
        
        int num_neu = 2;
        int num_out = 1;
        int size_in = 2;

        double eta = 0.1;
        int runs = 10000;

        NeuralNetwork nn = new NeuralNetwork(num_neu,size_in,num_out,X,Y,eta);

        nn.initialize();    // Randomizing the weights
        nn.run(runs);       // Running the algorithm with runs iterations

        System.out.println("Running the Neural Network "+runs+
                " iterations and "+num_neu+" neurons in the hidden layer\n");

        int right = 0;
        int wrong = 0;

        FileWriter fw_right = new FileWriter("right.txt");
        FileWriter fw_wrong = new FileWriter("wrong.txt");

        for (int i=0; i<X.length; i++) {

            // Feed forward for the examples
            nn.feedforward(i);

            // Neural Network output
            double ans = nn.net_out[0];

            if (((ans > 0.5) && (Y[i]==1.0)) || ((ans < 0.5) && (Y[i]==0.0))) {
                right++;

                if (output_file) {
                for (int j=0; j<2 ; j++){
                    fw_right.write(X[i][j]+" ");
                }
                fw_right.write("\n");
                }

            }
            else {
                wrong++;

                if (output_file) {
                for (int j=0; j<2 ; j++){
                    fw_wrong.write(X[i][j]+" ");
                }
                fw_wrong.write("\n");
                }

            }

        }

        if (output_file) {
                fw_right.closeFile();
                fw_wrong.closeFile();
        }

        System.out.println("Right Classifications: "+right+ "("+(float)right/(float)X.length*100+"%)");
        System.out.println("Wrong Classifications: "+wrong+ "("+(float)wrong/(float)X.length*100+"%)");


    }

}
