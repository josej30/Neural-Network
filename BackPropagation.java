/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package neuralnetwork;
import neuralnetwork.ExampleGenerator;

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
        
        int num_neu = 8;
        int num_out = 1;
        int size_in = 2;

        double eta = 0.1;
        int runs = 10000;

        NeuralNetwork nn = new NeuralNetwork(num_neu,size_in,num_out,X,Y,eta);

        nn.initialize();    // Randomizing the weights
        nn.run(runs);       // Running the algorithm with runs iterations

        System.out.println("Running the Neural Network "+runs+
                " iterations and "+num_neu+" neurons in the hidden layer\n");

        // Reading the validation data input file
        FileReader fr_val = new FileReader("500_validacion.txt");
        fr_val.read();
        // Retrieving the x and y vectors
        double [][] X_val = fr_val.returnX();
        double [] Y_val = fr_val.returnY();

        int right = 0;
        int wrong = 0;

        FileWriter fw1 = new FileWriter("data1.dat");
        FileWriter fw2 = new FileWriter("data2.dat");

        for (int i=0; i<X_val.length; i++) {

            // Feed forward for the examples
            nn.feedforward(i,X_val);

            // Neural Network output
            double ans = nn.net_out[0];

            if (((ans > 0.5) && (Y_val[i]==1.0)) || ((ans < 0.5) && (Y_val[i]==0.0)))
                right++;
            else
                wrong++;

            if (ans > 0.5) {
                if (output_file) {
                for (int j=0; j<2 ; j++){
                    fw1.write(X_val[i][j]+" ");
                }
                fw1.write("\n");
                }
            }
            else {
                if (output_file) {
                for (int j=0; j<2 ; j++){
                    fw2.write(X_val[i][j]+" ");
                }
                fw2.write("\n");
                }
            }

        }

        if (output_file) {
                fw1.closeFile();
                fw2.closeFile();
        }

        System.out.println("Right Classifications: "+right+ 
                "("+(float)right/(float)X_val.length*100+"%)");
        System.out.println("Wrong Classifications: "+wrong+ 
                "("+(float)wrong/(float)X_val.length*100+"%)");
    }

}
