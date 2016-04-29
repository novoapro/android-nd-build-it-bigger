package com.joketeller.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeTellerRepository {

    private static List<String> sJokeList;
    private static final Random sRandom = new Random();

    public static String getJoke(){
        initRepository();
        return sJokeList.get(sRandom.nextInt(4));
    }

    private static void initRepository() {
        if (sJokeList != null)
            return;

        sJokeList = new ArrayList<>();
        sJokeList.add("Q: What does a nosey pepper do?\nA: Gets jalapeno business!");
        sJokeList.add("Q: What do you call a fake noodle?\nA: An Impasta");
        sJokeList.add("Q: What do you call an alligator in a vest?\nA: An Investigator");
        sJokeList.add("Q: What's the difference between a guitar and a fish?\nA: You can't tuna fish.");
        sJokeList.add("Q: What do lawyers wear to court?\nA: Lawsuits!");
    }

}
