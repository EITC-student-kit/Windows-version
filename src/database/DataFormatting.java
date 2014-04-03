package src.database;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class DataFormatting {

    /**
     * Converts a Sting into a Timestamp.
     * @param datetime datetime in format 08:15 02/03/2014 Todo: Agree on comfortable format
     * @return a sql Timestamp
     */
    static public Timestamp stringToTimestamp(String datetime) {
        Timestamp timestamp = new Timestamp(1);
        try {
            java.util.Date regularDate = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.US).parse(datetime);
            timestamp = new Timestamp(regularDate.getTime());
        } catch (ParseException e) {
            System.out.println("The datetime format is wrong in stringToTimestamp");
        }
        return timestamp;
    }
}
