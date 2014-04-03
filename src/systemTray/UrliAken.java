package src.systemTray;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;


public class UrliAken {

    // konstruktor
    public void Liides() {
        Frame tippAken;
        tippAken = new Frame();
        tippAken.setSize(300, 150);
        tippAken.setTitle("URLi sisestamine");
        tippAken.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Label kysimus = new Label("Sisesta URL!");
        kysimus.setForeground(Color.white);
        kysimus.setBackground(Color.black);

        JTextField vastus = new JTextField(20);// laius
        vastus.setForeground(Color.white);
        vastus.setBackground(Color.black);
        vastus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nimi = ((JTextField) e.getSource()).getText();
                writeUrltoFile(nimi);
                GetUrl retrieveURL = new GetUrl();
                try {
                    retrieveURL.retrieveURLfromfile(writeUrltoFile(nimi));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        );
        Button exitNupp = new Button("Kinnita");
        exitNupp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nimi = ((JTextField) ((Component) e.getSource()).getParent().getComponent(1)).getText();
                writeUrltoFile(nimi);
                GetUrl retrieveURL = new GetUrl();
                try {
                    retrieveURL.retrieveURLfromfile(writeUrltoFile(nimi));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        }
        );
        tippAken.setLayout(new GridLayout(3, 1));
        tippAken.add(kysimus);
        tippAken.add(vastus);
        tippAken.add(exitNupp);
        tippAken.setVisible(true);
    }

    private String writeUrltoFile(String data) {
        Writer writer = null;
        String fileLoc = "C:/Users/Sten/Documents/URL.txt";
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileLoc), "utf-8"));
            writer.write(data);
        } catch (IOException ex) {
            // report
        } finally {
            try {
                assert writer != null;
                writer.close();} catch (Exception ex) {
                System.out.println("ei saa lugeda");
            }
        }
    return fileLoc;
    }


}
