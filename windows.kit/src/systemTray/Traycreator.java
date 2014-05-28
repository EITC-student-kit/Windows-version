package systemTray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import timetable.CalendarWindow;
import timetable.Variables;
import timetable.WeekView;

public class Traycreator {
	//
	public void createTray() {
		// Check the SystemTray is supported
		if (!SystemTray.isSupported()) {
			System.out.println("SystemTray is not supported");
			return;
		}
		Image image = Toolkit.getDefaultToolkit().getImage("Tray_logo.jpg");
		final PopupMenu popup = new PopupMenu();
		final TrayIcon trayIcon = new TrayIcon(image, "CC tray", popup);
		final SystemTray tray = SystemTray.getSystemTray();
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {

				}
			}
		});
		// Create a pop-up menu components
		MenuItem sisestaUrl = new MenuItem("Sisesta tunniplaani url");
		MenuItem exitItem = new MenuItem("Exit");
		MenuItem avaWeekView = new MenuItem("open week view");
		MenuItem openCalendar = new MenuItem("Open Calendar");
		// Add components to pop-up menu
		popup.add(avaWeekView);
		avaWeekView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String sDate = String.valueOf(dateFormat.format(date));
				Variables sdate = new Variables();
				sdate.setDate(sDate);
				
				WeekView weeks = new WeekView(sDate);
				weeks.setDate(sDate);

			}
		});

		popup.add(openCalendar);
		openCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CalendarWindow calen = new CalendarWindow();
				calen.calenda();
			}
		});

		popup.add(sisestaUrl);
		sisestaUrl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UrliAken avaAken = new UrliAken();
				avaAken.Liides();
			}
		});
		popup.add(exitItem);
		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		trayIcon.setPopupMenu(popup);

		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			System.out.println("TrayIcon could not be added.");
		}

	}
}
