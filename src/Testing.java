package src;

import dataTypes.AClass;

/**
 * Created by kris on 2/25/14.
 */
public class Testing {
    public static void main(String[] args) {
        AClass someClass = new AClass("I012", "Mathematics", "12, 14, 15", "Practice", "2014.03.02 08:15:00","2014.03.02 09:45:00", "314",
                "Kristiina Kalmistu", true);
        System.out.println(someClass);
    }
}