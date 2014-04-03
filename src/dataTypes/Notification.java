package dataTypes;


import database.DataFormatting;

import java.sql.Timestamp;

/**
 * Created by Kristo Koert on 3/2/14.
 */
public class Notification {
    /**
     * Since this class serves as a sort of container, these are the contents.
     */
    String message = null;
    Timestamp datetime = null;

    /**
     * Constructor method.
     *
     * @param message Some description of the notification.
     * @param datetime datetime in format 08:15 02/03/2014
     */
   Notification(String message, String datetime) {
       this.message = message;
       this.datetime = DataFormatting.stringToTimestamp(datetime);
    }
}
