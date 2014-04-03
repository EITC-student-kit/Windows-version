package src.dataTypes;


import src.database.DataFormatting;

import java.sql.Timestamp;

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
    boolean attendible;

    /**
     * Creates a representation of a single class.
     *
     * @param subjectCode the subjects code (e.g. I241)
     * @param className the name of the class
     * @param groups attending groups separated by comas (e.g. 11, 15)
     * @param type Lecture, Exercise, Practice, Repeat prelim, Reservation, Consultation
     * @param start_time start_time in format 08:15 02/03/2014
     * @param classRoom class room where class takes place
     * @param academician the academician(s) format separated with comas
     * @param attendible does the user attend this class or not
     */
    public AClass(String subjectCode, String className, String groups, String type, String start_time, String end_time, String classRoom,
           String academician, boolean attendible) {
        this.subjectCode = subjectCode;
        this.className = className;
        this.groups = groups;
        this.type = type;
        this.classRoom = classRoom;
        this.academician = academician;
        this.attendible = attendible;
        this.start_time = DataFormatting.stringToTimestamp(start_time);
        this.end_time = DataFormatting.stringToTimestamp(end_time);
    }

    //HowTo: Account for instances where not all information is available?
    //Suggestion: Create other constructor methods dependant on how much information is available. (varargs?)
    //Suggestion: Or use inheritance and improve upon super.ConstructorMethod()?

    public String toString() {
        return "Subject code: " + subjectCode + "\n" +
                "Class name: " + className + "\n" +
                "Groups: " + groups + "\n" +
                "Type: " + type + "\n" +
                "Date and Time: " + start_time + "\n" +
                "Date and Time: " + end_time + "\n" +
                "Class room: " + classRoom + "\n" +
                "Academician: " + academician + "\n" +
                "Attendible: " + attendible;
    }
}
