import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BalancingSlvr {

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
        try (BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")))) {

            // Local
            // try (BufferedReader br = new BufferedReader(new FileReader("bronze/balancing.in"));
            // PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bronze/balancing.out")))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            Position[] positions = new Position[N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                // why do we divide y by 2 ???
                positions[i] = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            Arrays.sort(positions);
            // System.out.println(Arrays.toString(positions));
            int minMax = N; // minimum value of the most populated quandrant
            // O(N**2) algorithm
            for (int i = 0; i < N; i++) {
                int yBorder = positions[i].y;
                ArrayList<Position> below = new ArrayList<>();
                ArrayList<Position> above = new ArrayList<>();
                for (int j = 0; j < N; j++) {
                    if (positions[j].y <= yBorder) {
                        below.add(positions[j]);
                    } else {
                        above.add(positions[j]);
                    }
                }

                // System.out.println("yBorder " + yBorder + ": below=" + below.size() + ", above=" + above.size());
                // System.out.println("below=" + below + ", above=" + above);

                // counts # in the lower left region, also a index to the below list
                int lowerLeftCounter = 0;
                // counts # in the upper left region, also a index to the above list
                int upperLeftCounter = 0;
                int belowSize = below.size();
                int aboveSize = above.size();
                while (lowerLeftCounter < belowSize || upperLeftCounter < aboveSize) {

                    // xBorder is the smallest X from either the below or above lists
                    int xBorder = Integer.MAX_VALUE;
                    if (lowerLeftCounter < belowSize) {
                        xBorder = Math.min(xBorder, below.get(lowerLeftCounter).x);
                    }
                    if (upperLeftCounter < aboveSize) {
                        xBorder = Math.min(xBorder, above.get(upperLeftCounter).x);
                    }

                    // move lowerLeftCounter to point to the next x value greater than xBorder in "below",
                    // that value would then be the # in lowerLeft quadrant when xBorder is sweeping to
                    // the right
                    while (lowerLeftCounter < belowSize && below.get(lowerLeftCounter).x == xBorder) {
                        lowerLeftCounter++;
                    }

                    // move upperLeftCounter to point to the next x value greater than xBorder in "above",
                    // that value would then be the # in upperLeft quadrant when xBorder is sweeping to
                    // the right
                    while (upperLeftCounter < aboveSize && above.get(upperLeftCounter).x == xBorder) {
                        upperLeftCounter++;
                    }

                    // System.out.println(
                    // "xBorder " + xBorder + ": belowIndex=" + belowIndex + ", aboveIndex=" + aboveIndex);

                    int maxFromLowerQuadrants = Math.max(lowerLeftCounter, belowSize - lowerLeftCounter);
                    int maxFromUpperQuadrants = Math.max(upperLeftCounter, aboveSize - upperLeftCounter);
                    minMax = Math.min(minMax, Math.max(maxFromLowerQuadrants, maxFromUpperQuadrants));

                    // System.out.println("minMax: " + minMax);
                }
                // System.out.println();
            }
            pw.println(minMax);
            pw.close();
        }
    }

    static class Position implements Comparable<Position> {
        public final int x, y;

        public Position(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public int compareTo(Position other) {
            return this.x - other.x;
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }

    }
}
