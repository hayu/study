import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Created by 212365823 on 12/20/16.
 */
public class BalancingBrnz {

    public static void main(String[] args) throws IOException {
        // SAMPLE INPUT:
        //
        // 7 10
        // 7 3
        // 5 5
        // 9 7
        // 3 1
        // 7 7
        // 5 3
        // 9 1
        // SAMPLE OUTPUT:
        //
        // 2
    	
    	System.out.println(System.getProperty("user.dir"));
         try (BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[][] locations = new int[N][2];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 2; j++) {
                    locations[i][j] = Integer.parseInt(st.nextToken());
                }
            }
//            System.out.println(Arrays.deepToString(locations));

            // O(N**3) algorithm
            HashSet<String> tested = new HashSet<>();
            int minMax = N;
            for (int i = 0; i < N; i++) {
                int xFence = locations[i][0] + 1;
                for (int j = 0; j < N; j++) {
                    int yFence = locations[j][1] + 1;
                    String testing = xFence + "." + yFence;
                    if (tested.contains(testing)) {
                        continue;
                    }
                    
                    tested.add(testing);

                    // counter-clockwise
                    // 0 - lower left, 1 - lower right, 2 - upper right, 2 - upper left
                    int[] regions = new int[4];
                    for (int m = 0; m < N; m++) {
                        if (locations[m][0] < xFence && locations[m][1] < yFence) {
                            regions[0]++;
                        } else if (locations[m][0] > xFence && locations[m][1] < yFence) {
                            regions[1]++;
                        } else if (locations[m][0] > xFence && locations[m][1] > yFence) {
                            regions[2]++;
                        } else {
                            regions[3]++;
                        }
                    }

//                    System.out.println(String.format("%s %s: %s", xFence, yFence, Arrays.toString(regions)));

                    int max = 0;
                    for (int m = 0; m < regions.length; m++) {
                        max = Math.max(max, regions[m]);
                    }
                    minMax = Math.min(minMax, max);
                }
            }
//            System.out.println(minMax);
            pw.println(minMax);
        }
    }
}
