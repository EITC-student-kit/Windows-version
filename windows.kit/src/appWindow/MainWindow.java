package appWindow;


import systemTray.Traycreator;


import java.io.IOException;

public class MainWindow {
	//
	public static void main(String[] args) throws IOException {

		Traycreator cTray = new Traycreator();
		cTray.createTray();

	}

}