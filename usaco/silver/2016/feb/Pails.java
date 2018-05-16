import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Pails {

    public static void main(String[] args) throws IOException {
    // SAMPLE INPUT:
    //
    // 14 50 2 32
    // SAMPLE OUTPUT:
    //
    // 18
    // In two steps FJ can be left with the following quanities in his pails
    //
    // (0, 0) = 0 units
    // (14, 0) = 14 units
    // (0, 50) = 50 units
    // (0, 14) = 14 units
    // (14, 36) = 50 units
    // (14, 50) = 64 units
    // The closest we can come to 32 units is 14 for a difference of 18. Note that it would require an extra step to
    // pour out the first pail to end up with (0, 36).
        try (BufferedReader br = new BufferedReader(new FileReader("pails.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")))) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(input.nextToken());
            int y = Integer.parseInt(input.nextToken());
            int k = Integer.parseInt(input.nextToken());
            int m = Integer.parseInt(input.nextToken());

            boolean[][] can = new boolean[x + 1][y + 1];
            can[0][0] = true; // with zero operation, this is the only reachable state
            for (int operationNum = 0; operationNum < k; operationNum++) {
                // if can[A][B] is true, then after at most operationNum operations,
                // it is possible to end with A units of milk in the size X bucket
                // and B units of milk in the size Y bucket.
                boolean[][] next = new boolean[x + 1][y + 1];
                for (int i = 0; i < can.length; i++) {
                    for (int j = 0; j < can[i].length; j++) {
                        if (can[i][j]) {
                            System.out.println("start: " + i + ", " + j);
                            next[i][j] = true; // we can always maintain the same state
                            next[0][j] = true; // empty size X bucket
                            System.out.println("empty X: " + 0 + ", " + j);
                            next[x][j] = true; // fill size X bucket
                            System.out.println("fill X: " + x + ", " + j);
                            next[i][0] = true; // empty size Y bucket
                            System.out.println("empty Y: " + i + ", " + 0);
                            next[i][y] = true; // fill size Y bucket
                            System.out.println("fill Y: " + i + ", " + y);

                            // pour from size X bucket to size Y bucket
                            int moveRight = Math.min(i, y - j);
                            next[i - moveRight][j + moveRight] = true;
                            System.out.println(String.format("moveRight: %s, i: %s, j: %s", moveRight, i - moveRight,
                                    j + moveRight));

                            // pour from size Y bucket to size X bucket
                            int moveLeft = Math.min(x - i, j);
                            next[i + moveLeft][j - moveLeft] = true;
                            System.out.println(String.format("moveLeft: %s, i: %s, j: %s", moveLeft, i + moveLeft,
                                    j - moveLeft));
                        }
                    }
                }
                can = next;
                for (int i = 0; i < can.length; i++) {
                    for (int j = 0; j < can[i].length; j++) {
                        if (next[i][j]) {
                            System.out.println(i + ", " + j);
                        }
                    }
                }
            }
            int ret = Integer.MAX_VALUE;
            for (int i = 0; i < can.length; i++) {
                for (int j = 0; j < can[i].length; j++) {
                    if (!can[i][j])
                        continue;
                    ret = Math.min(ret, Math.abs(i + j - m));
                }
            }
            pw.println(ret);
        }
    }

}
