import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Div7 {

    public static void main(String[] args) throws IOException {
        // SAMPLE INPUT
        // 7
        // 3
        // 5
        // 1
        // 6
        // 2
        // 14
        // 10
        //
        // SAMPLE OUTPUT
        // 5
       try (BufferedReader br = new BufferedReader(new FileReader("div7.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")))) {

            int n = Integer.parseInt(br.readLine());
            int[] first = new int[7]; // stores the first index (shortest prefix) when a given mod-7 value is found
            int[] last = new int[7];  // stores the last index (longest prefix) when a given mod-7 value is found
            Arrays.fill(first, Integer.MAX_VALUE);
            first[0] = 0;
            int currPref = 0;
            for (int i = 1; i <= n; i++) {
                currPref += Integer.parseInt(br.readLine()); // we are only adding mod 7 value together
                currPref %= 7; // we only care about mod 7 value
                // use min since we want the first time we found a given mod-7 value
                first[currPref] = Math.min(first[currPref], i);
                // use i, which is incrementing, since we want the last time a given mod-7 value is found
                last[currPref] = i;
            }
            int ret = 0;
            for (int i = 0; i < 7; i++) {
                if (first[i] <= n) {
                    ret = Math.max(ret, last[i] - first[i]);
                }
            }
            pw.println(ret);
            pw.close();
        }
    }
}
