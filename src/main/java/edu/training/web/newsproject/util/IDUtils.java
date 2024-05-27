package edu.training.web.newsproject.util;

import java.util.Random;

public class IDUtils {
    private static final Random RANDOM = new Random();

    private static final int ID_BOUND = 1000000;

    public static int generateID() {
        return RANDOM.nextInt(ID_BOUND);
    }
}
