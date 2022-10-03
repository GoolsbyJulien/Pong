package engine.util;

import java.util.Random;

public class Util {
    public static int RandomRange(int min, int max) {


        max += 1;

        return (int) ((Math.random() * (max  - min)) + min);
    }
}
