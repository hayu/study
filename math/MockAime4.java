
public class MockAime4 {
    
    public static void main(String[] args) throws Exception {
        int n = 1000;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int m = i * (i + 1);
            //System.out.println(String.format("%d: %d %d %d %d", i, m, m%10, m%100, m%100 - m%10));
            if (m%10 == m%100) {
                count++;
                System.out.println(String.format("%d: %d (%d, %d)", i, m, m%10, m%100));
            }
        }
        
        System.out.println(count);
    }

}
