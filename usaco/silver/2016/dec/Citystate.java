import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Citystate {

    public static void main(String[] args) throws IOException {
        // 7
        // MIAMI FL
        // DALLAS TX
        // FLINT MI
        // CLEMSON SC
        // BOSTON MA
        // ORLANDO FL
        // ORLANDO OR

        // 1
        try (BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")))) {
            int n = Integer.parseInt(br.readLine());
            
            // a map of state to a map of cities
            // the map of cities has key as the 1st 2 characters of the city and how many times
            // such same 2-characters city we have
            HashMap<String, HashMap<String, Integer>> state2cities = new HashMap<>();
            
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String city = st.nextToken().substring(0, 2);
                String state = st.nextToken();
                if (state2cities.get(state) == null) {
                    state2cities.put(state, new HashMap<String, Integer>());
                }

                HashMap<String, Integer> cities = state2cities.get(state);
                if (cities.get(city) == null) {
                    cities.put(city, 0);
                }
                cities.put(city, cities.get(city) + 1);
            }

            int count = 0;
            for (Map.Entry<String, HashMap<String, Integer>> e : state2cities.entrySet()) {
                String currentState = e.getKey();
                HashMap<String, Integer> cities = e.getValue();
                for (String currentCity : cities.keySet()) {
                    if (state2cities.get(currentCity) != null) {
                        String otherState = currentCity;
                        if (!otherState.equals(currentState)) {
                            HashMap<String, Integer> otherCities = state2cities.get(otherState);
                            if (otherCities.get(currentState) != null) {
                                // we are double counting, since we add in both directions
                                count += otherCities.get(currentState) * cities.get(otherState);
                            }
                        }
                    }
                }
            }

            // fix double counting by divide by 2
            pw.println(count / 2);
        }
    }

}
