package src.systemTray;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Traycreator {
//
    public void createTray() {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        Image image = Toolkit.getDefaultToolkit().getImage("C:/Users/Sten/IdeaProjects/Windows-version/src/systemTray/Tray_logo.jpg");
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(image, "CC tray", popup);
        final SystemTray tray = SystemTray.getSystemTray();
        trayIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    UrliAken Urliaken = new UrliAken();
                    Urliaken.Liides();
                }
            }
        });
        // Create a pop-up menu components
        MenuItem sisestaUrl = new MenuItem("Sisesta tunniplaani url");
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
        popup.add(sisestaUrl);
        sisestaUrl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
