package cz.lukynka.halloweenmobs.Utils;

import java.util.Random;

public class math {

    public static int randomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }


}
