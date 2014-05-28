package dataTypes;

import database.DataFormatting;
import java.sql.Timestamp;

/**
 * Created by Kristo Koert on 3/2/14.
 */
public class AClass {

    /**
     * Since this class serves as a sort of container, these are the contents.
     */
    String subjectCode = null;
    String className = null;
    String groups = null;
    String type = null;
    Timestamp start_time = null;
    Timestamp end_time = null;
    String classRoom = null;
    String academician = null;

    /**
     * Creates a representation of a single class.
     *
     * @param subjectCode the subjects code (e.g. I241)
     * @param className the name of the class
     * @param groups attending groups separated by comas (e.g. 11, 15)
     * @param type Lecture, Exercise, Practice, Repeat prelim, Reservation, Consultation
     * @param start_time start_time in format 20140320T133000Z
     * @param classRoom class room where class takes place
     * @param academician the academician(s) format separated with comas
     */
    public AClass(String subjectCode, String className, String groups, String type, String start_time, String end_time, String classRoom,
           String academician) {
        this.subjectCode = subjectCode;
        this.className = className;
        this.groups = groups;
        this.type = type;
        this.classRoom = classRoom;
        this.academician = academician;
        this.start_time = DataFormatting.stringToTimestamp(start_time);
        this.end_time = DataFormatting.stringToTimestamp(end_time);
    }

    /**
     * Returns the values of this instance for writing to db.
     * @return a array of data suitable for writing to db
     */
    public Object[] getDatabaseRow() {
        return new Object[]{subjectCode, className, groups, type, classRoom, academician, start_time, end_time};
    }

    public String toString() {
        return "Subject code: " + subjectCode + "\n" +
                "Class name: " + className + "\n" +
                "Groups: " + groups + "\n" +
                "Type: " + type + "\n" +
                "Start Date and Time: " + start_time + "\n" +
                "End Date and Time: " + end_time + "\n" +
                "Class room: " + classRoom + "\n" +
                "Academician: " + academician + "\n";
    }
}
