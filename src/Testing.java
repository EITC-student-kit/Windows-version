package src;

import src.dataTypes.AClass;
import src.database.DataFormatting;
/**
 * Created by Kristo Koert on 2/25/14.
 */
public class Testing {
    public static void main(String[] args) {
        System.out.println(DataFormatting.stringToTimestamp("20140306T093000Z"));
        System.out.println(DataFormatting.stringToTimestamp("20140320T133000Z"));
        //(String subjectCode, String className, String groups, String type, String start_time,
        // String end_time, String classRoom, String academician)
        AClass test_class = new AClass("I089", "Math", "11, 12 ,15", "Practice", "20140320T140000Z",
                "20140320T153000Z", "314", "Anett Mihkel");
        System.out.println(test_class);
    }
}