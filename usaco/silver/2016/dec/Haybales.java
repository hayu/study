import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Haybales {

    public static void main(String[] args) throws IOException {
        // SAMPLE INPUT:
        // 4 6
        // 3 2 7 5
        // 2 3
        // 2 4
        // 2 5
        // 2 7
        // 4 6
        // 8 10
        // SAMPLE OUTPUT:
        // 2
        // 2
        // 3
        // 4
        // 1
        // 0
        try (BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int[] bales = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                bales[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(bales);
//            System.out.println(Arrays.toString(bales));
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0, len = bales.length; i < len ; i++) {
                map.put(bales[i], i);
            }
//            System.out.println(map);
            
            int min = bales[0];
            int max = bales[n-1];
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                int lower = Integer.parseInt(st.nextToken());
                int upper = Integer.parseInt(st.nextToken());
                
                // handle out-of-range special cases
                if (lower > max || upper < min) {
                    pw.println(0);
                } else {
                    int start = map.get(map.ceilingKey(lower));
                    int end = map.get(map.floorKey(upper));
//                    System.out.println(String.format("%s %s: %s %s", lower, upper, start, end));
                    pw.println(end - start + 1);
                }
            }
        }
    }

}
