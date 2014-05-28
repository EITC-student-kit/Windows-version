package systemTray;

import timetable.GetIcal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class GetUrl {
	//
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
		writeUrltoFile(uRL);

		GetIcal Gic = new GetIcal();
		try {
			Gic.grabIcal(uRL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeUrltoFile(String data) {
		Writer writer = null;
		String fileLoc = "URL.txt";

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileLoc), "utf-8"));
			writer.write(data);
		} catch (IOException ex) {
			// report
		} finally {
			try {
				assert writer != null;
				writer.close();
			} catch (Exception ex) {
				System.out.println("ei saa Kirjutada");
			}
		}

	}
}
