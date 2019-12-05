package Builder;

import java.io.IOException;
import java.util.Random;

public class Main2 {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 20; i++) {
            Integer r = new Random().nextInt(6) + 1;
            System.out.println(r);
        }
    }
}
