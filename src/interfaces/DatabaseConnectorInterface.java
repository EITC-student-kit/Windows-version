package src.interfaces;

import src.dataTypes.AClass;
import src.dataTypes.Notification;
import java.util.ArrayList;

public interface DatabaseConnectorInterface {

    /**
     * Adds a class description to the correct java.database table. Checks for duplicates.
     *
     * @param aClass a AClass instance
     */
    public void addClass(AClass aClass);

    /**
     * Adds a notification to the correct table. Checks for time overlap.
     *
     * @param notif a Notification instance
     */
    public void addNotification(Notification notif);


    /**
     * Returns all the classes the user Attends.
     *
     * @return an ArrayList containing AClass instances
     */
    public ArrayList getAttendableClasses();

    /**
     * Returns all the users notifications.
     *
     * @return an ArrayList containing Notification instances
     */
    public ArrayList getNotifications();
}
