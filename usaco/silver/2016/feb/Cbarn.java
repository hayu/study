import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Cbarn {

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // # of bales
            int k = Integer.parseInt(st.nextToken()); // # of cows
            int[] position = new int[n];
            for (int i = 0; i < n; i++) {
                position[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(position);

            // min and max radius
            int minRadius = 1;
            int maxRadius = position[n - 1];

            while (minRadius != maxRadius) {
                int currRadius = (minRadius + maxRadius) / 2;
                int numCowUsed = 0;

                // starting from the left, calculate how many cows are needed to bomb all bales given the radius
                int leftmost = 0;
                while (leftmost < n) {
                    numCowUsed++; // launched a cow
                    int curr = leftmost + 1; // keeping moving cow to the right to increase its damage
                    while (curr < n && (position[curr] - position[leftmost] <= 2 * currRadius)) {
                        curr++;
                    }
                    leftmost = curr;
                }

                if (numCowUsed <= k) {
                    // we have enough cows to continue minimizing the radius with binary search
                    maxRadius = currRadius;
                } else {
                    // we don't hava enough cow, increase the minimum radius
                    minRadius = currRadius + 1;
                }
            }

            pw.println(minRadius);
        }
    }

}
