import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Moocast {

    public static void main(String[] args) throws IOException {
//    SAMPLE INPUT
//      4
//      1 3 5
//      5 4 3
//      7 2 1
//      6 1 1
//  
//    SAMPLE OUTPUT
//      3

        try (BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")))) {

            int n = Integer.parseInt(br.readLine());
            Cow[] cows = new Cow[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()));
            }
//            Arrays.sort(cows);
//            System.out.println(Arrays.toString(cows));
            
            for (int i = 0, cnt = cows.length; i < cnt; i++) {
                Cow start = cows[i];
                for (int j = 0; j < cnt; j++) {
                    if (!start.equals(cows[j]) && start.canReach(cows[j])) {
                        start.directReachables.add(cows[j]);
                    }
                }
//                System.out.println(start + ": " + start.directReachables);
            }
            
            int max = 0;
            for (Cow currentCow : cows) {
                for (Cow c : cows) {
                    c.visited = false;
                }
                
                HashSet<Cow> allReachables = new HashSet<>();
                allReachables.add(currentCow);
                traverseReachables(currentCow, allReachables);
                max = Math.max(max, allReachables.size());
//                System.out.println(currentCow.toString() + ": " + max);
//                System.out.println();
            }
            
//            System.out.println(max);
            pw.println(max);
        }
    }
    
    /**
     * Traverse node and collect all its reachable ones recursively to <code>union</code>.
     * @param node
     * @param union
     */
    public static void traverseReachables(Cow node, HashSet<Cow> union) {
        node.visited = true;
        union.addAll(node.directReachables);
//        System.out.println(reachable.toString() + ": " + union);
        for (Cow c : node.directReachables) {
            union.addAll(c.directReachables);
//            System.out.println(reachable.toString() + ": " + union);
            if (!c.visited) {
                traverseReachables(c, union);
            }
        }
    }

    static class Cow implements Comparable<Cow> {
        public int x, y, p;
        public boolean visited = false;
        public HashSet<Cow> directReachables = new HashSet<>();
        public Cow(int x, int y, int p) {
            this.x = x;
            this.y = y;
            this.p = p;
        }

        public boolean canReach(Cow other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2)) <= this.p;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + x;
            result = prime * result + y;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Cow other = (Cow) obj;
            if (x != other.x)
                return false;
            if (y != other.y)
                return false;
            return true;
        }

        @Override
        public int compareTo(Cow o) {
            if (this.p == o.p) {
                return 0;
            }
            
            return (this.p - o.p) > 0 ? -1 : 1;
        }

        @Override
        public String toString() {
            return "Cow [x=" + x + ", y=" + y + ", p=" + p + ", visited=" + visited + "]";
        }

    }

}
