package expii;

import org.junit.Test;

public class SnailOnSpring {

    @Test
    public void test() {
        double length = 100.0;
        double position = 0.0;
        double step = 1.0;
        for (int i = 0, size = 1000; i < size; i++) {
            position += step;
            if (position - length > 0.0) {
                System.out.println(String.format("Length: %s, position: %s", length, position));
                break;
            }
            position = position / length * (length + 1);
            System.out.println(String.format("Length: %s, position: %s", length+1, position));
            length += 1;
        }
    }

}
