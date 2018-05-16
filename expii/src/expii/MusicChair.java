package expii;

public class MusicChair {

//    public static void main(String[] args) {
//        long n = Long.parseLong(args[0]);
//        int count = 0;
//        for (long i = 1; i <= n; i++) {
//            Set<Long> chairs = new HashSet<>();
//            for (long j = 1; j <= n; j++) {
//                chairs.add((i*j)%n);
//            }
//            System.out.println(String.format("%s: %s", i, chairs.size()));
//            count += chairs.size();
//        }
//        
//        System.out.println("----------------------------------------");
//        System.out.println(String.format("For %s: %s, %s", n, count, Double.valueOf(count)/n));
//    }
    
    public static void main(String[] args) {
        int[] primes = new int[] {2,3,5,7,11,13,17,19,23,29};
        long prod = 1;
        for (int i = 0, len = primes.length; i< len; i++) {
            prod *= (primes[i]*primes[i]*primes[i] + 1) / (primes[i] + 1);
        }
        
        System.out.println(prod);
    }
}
