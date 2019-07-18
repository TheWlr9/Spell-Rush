package com.spellrush.services;

public class JavaTestUtility {
    public static synchronized boolean isRunningEspressoTest () {
        boolean istest;

        try {
            Class.forName ("androidx.test.espresso.Espresso");
            istest = true;
        } catch (ClassNotFoundException e) {
            istest = false;
        }
        return istest;
    }
}
