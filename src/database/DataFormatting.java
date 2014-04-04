package src.database;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Kristo Koert on 3/2/14.
 */
public class DataFormatting {

    /**
     * Converts a Sting into a Timestamp.
     * @param dt ical datetime in format like: 20140306T093000Z
     * @return a sql Timestamp
     */
    static public Timestamp stringToTimestamp(String dt) {
        String year = dt.substring(0, 4);
        String month = dt.substring(4, 6);
        String day = dt.substring(6, 8);
        String hour = String.valueOf(new Integer(dt.substring(9, 11)) + 2);
        String minute = dt.substring(11, 13);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date date = dateFormat.parse(day + '/' + month + '/' + year + ' ' + hour + ':' + minute);
            long time = date.getTime();
            return new Timestamp(time);
        } catch (ParseException e) {
            System.out.println("stringToTimestamp parameter must be in ical datetime format, like: 20140306T093000Z");
            System.exit(0);
            return new Timestamp(0);
        }
    }
}
