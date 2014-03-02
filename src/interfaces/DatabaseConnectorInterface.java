package interfaces;

import dataTypes.AClass;
import dataTypes.Notification;
import java.util.ArrayList;

/**
 * Created by Kristo Koert on 3/02/14.
 */
public interface DatabaseConnectorInterface {

    /**
     * Adds a class description to the correct database table. Checks for duplicates.
     *
     * @param aClass a AClass instance
     */
    public void addClass(AClass aClass);

    /**
     * Adds a notification to the correct table. Checks for time overlap.
     * Todo how to handle same time notifications? Python appindicator displays most recent notification.
     *
     * @param notif a Notification instance
     */
    public void addNotification(Notification notif);

    /**
     * Adds a new item to to do table.
     *
     * @param toDo a description
     */
    public void addToDoItem(String toDo);

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


    //HowTo: Query methods that allow for filtering? Java or SQLite filter?
}
