package mar;
import java.io.*;
import java.util.*;
public class PairUp {
    public static void main(String[] args) throws IOException{
        pairup();
    }

    public static void pairup() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("pairup.in"));
             PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int total = 0;
            ArrayList<Pair> pairs = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int cnt = Integer.parseInt(st.nextToken());
                pairs.add(new Pair(Integer.parseInt(st.nextToken()), cnt));
                total += cnt;
            }
            
            Collections.sort(pairs);
            Pair firstPair = pairs.get(0);
            Pair lastPair = pairs.get(pairs.size() - 1);
            
            int max = 0;
            int sum = 0;
            while (pairs.size() > 0) {
        		sum = firstPair.t + lastPair.t;
        		firstPair.c--;
        		lastPair.c--;
        		if (sum > max) {
        			max = sum;
        		}

            	if (firstPair == lastPair) {
            		break;
            	}
            	
            	if (firstPair.c == 0) {
            		pairs.remove(0);
            		if (pairs.size() > 0) {
            			firstPair = pairs.get(0);
            		}
            	}

            	if (lastPair.c == 0) {
            		pairs.remove(pairs.size() - 1);
            		if (pairs.size() > 0) {
            			lastPair = pairs.get(pairs.size() - 1);
            		}
            	}
            }
            System.out.println(max);
            pw.print(max);
        }
    }
    
    static class Pair implements Comparable<Pair> {
    	int c, t;
    	Pair(int t, int c) {
    		this.t= t;
    		this.c = c;
    	}
		@Override
		public int compareTo(Pair o) {
			return this.t - o.t;
		}
    	
    	
    }
}

