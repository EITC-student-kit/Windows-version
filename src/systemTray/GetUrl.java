package src.systemTray;

import src.timetable.GetIcal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class GetUrl {

    public void retrieveURLfromfile(String fileLoc) throws IOException {
        String uRL = "";
        try {
            File file = new File(fileLoc);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                uRL = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GetIcal Gic = new GetIcal();
        try {
            Gic.grabIcal(uRL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

