import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class Gates {

    public static void main(String[] args) throws IOException {
        // SAMPLE INPUT:
        // 14
        // NNNESWWWSSEEEE
        //
        // SAMPLE OUTPUT:
        // 2
        try (BufferedReader br = new BufferedReader(new FileReader("gates.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")))) {

            int x = 0;
            int y = 0;
            int xprev = x;
            int yprev = y;
            Set<String> vertices = new HashSet<>();
            vertices.add(x + " " + y); // starting point is a vertex
            
            Set<String> edges = new HashSet<>();

            int n = Integer.parseInt(br.readLine());
            String walk = br.readLine();
            for (int i=0;i<n;i++) {
                xprev = x;
                yprev = y;
                switch (walk.charAt(i)) {
                    case 'N':
                        y++;
                        break;
                    case 'S':
                        y--;
                        break;
                    case 'E':
                        x++;
                        break;
                    case 'W':
                        x--;
                        break;
                }

                vertices.add(x + " " + y);

                // double-count edges so we don't have to worry about direction
                edges.add(xprev + " " + yprev + " " + x + " " + y);
                edges.add(x + " " + y + " " + xprev + " " + yprev);
            }

            // divide edge count by 2 to fix double-counting
            pw.println(edges.size() / 2 + 1 - vertices.size());
        }
    }
}
