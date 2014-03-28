package javaCc.appWindow;

import javaCc.systemTray.Traycreator;
import javaCc.timetable.GetIcal;
import javaCc.timetable.ParseIcal;

import java.io.IOException;

/**
 * Created by ${Kris} on 2/25/14.
 */
public class MainWindow {

    public static void main(String[] args) throws IOException {
        String SUrl = "https://itcollege.ois.ee/timetable/ical?student_id=2800&key=50139976272982d7897e8225d45e36a5e74870f0";
        GetIcal Gic = new GetIcal();
        String path = String.valueOf(Gic.grabIcal(SUrl));
        ParseIcal Pic = new ParseIcal("C:/Users/Sten/Documents/ICal.txt");
        Pic.ParseIc("C:/Users/Sten/Documents/ICal.txt");
        // Pic.toAclass();
        Traycreator cTray = new Traycreator();
        cTray.createTray();
    }
}