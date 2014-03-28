package javaCc.appWindow;

import javaCc.timetable.GetIcal;
import javaCc.timetable.ParseIcal;

import java.io.IOException;

/**
 * Created by kris on 2/25/14.
 */
public class MainWindow {

    public static void main(String[] args) throws IOException {
       String SUrl = "";
        GetIcal Gic = new GetIcal();
        String path =  Gic.grabIcal(SUrl);
        ParseIcal Pic = new ParseIcal("C:/Users/Sten/Documents/ICal.txt");
        Pic.ParseIc("C:/Users/Sten/Documents/ICal.txt");
        Pic.toAclass();
    }
}