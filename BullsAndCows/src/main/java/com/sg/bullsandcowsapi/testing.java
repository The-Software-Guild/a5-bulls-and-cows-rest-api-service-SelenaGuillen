package com.sg.bullsandcowsapi;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class testing {

    public static void main(String[] args) {
        String result = "e:2:p:3";
        int exactmatches; //correct digit & position
        int partialmatches; //correct digit & incorrect position

        String[] array = result.split(":");
        exactmatches = Integer.parseInt(array[1]);
        partialmatches = Integer.parseInt(array[3]);

//        System.out.println("Exact matches " + exactmatches);
//        System.out.println("partial amtcehs" +partialmatches);

        String number = "1245";
        String guess = "3252";
        String guess2 = "1245";
        //1 exact, 2 partial


//        int [] resultarray = findMatches(number, guess);
//
//        for (int n: resultarray) {
//            System.out.println(n);
//        }

        LocalDateTime ld = LocalDateTime.now();
        Timestamp ts = Timestamp.valueOf(ld);
        System.out.println(ts);

    }

    private static int[] findMatches(String number, String guess) {
        int[] result = new int[2];
        int partial = 0;
        int exact = 0;

        if (guess.contentEquals(number)) {
            result[0] = 4;
            return  result;
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guess.indexOf(number.charAt(i)) >= 0 && number.charAt(i) != guess.charAt(i)) {
                partial++;
                result[1]  = partial;
            }
            else if (guess.indexOf(number.charAt(i)) >= 0 && number.charAt(i) == guess.charAt(i)) {
                exact++;
                result[0] = exact;
            }


        }
        return result;
    }
}
