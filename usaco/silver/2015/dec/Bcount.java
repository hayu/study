import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Bcount {

    public static void main(String[] args) throws IOException {
        // SAMPLE INPUT
        // 6 3
        // 2
        // 1
        // 1
        // 3
        // 2
        // 1
        // 1 6
        // 3 3
        // 2 4

        // SAMPLE OUTPUT
        // 3 2 1
        // 1 0 0
        // 2 0 1
        try (BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // # of cows, one on each line
            int q = Integer.parseInt(st.nextToken()); // # of queries
            int[][] counts = new int[4][n + 1]; // use index 1 to 3 for breed, and 1 to n for aggregate breed counts
            for (int i = 1; i <= n; i++) {
                // copy over the current aggregated breed counts on the previous row, i-1
                for (int j = 1; j < 4; j++) {
                    counts[j][i] = counts[j][i - 1];
                }

                // update stats to include the current breed
                int curr = Integer.parseInt(br.readLine());
                counts[curr][i]++;
            }

            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()) - 1;
                int end = Integer.parseInt(st.nextToken());
                pw.println(String.format("%s %s %s", counts[1][end] - counts[1][start],
                        counts[2][end] - counts[2][start], counts[3][end] - counts[3][start]));
            }
        }
    }
}
