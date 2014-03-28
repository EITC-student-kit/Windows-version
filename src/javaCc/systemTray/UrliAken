package javaCc.systemTray;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class UrliAken {
    Frame tippAken;
    // konstruktor
    public void Liides() {

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
        String text = vastus.getText();
        vastus.setForeground(Color.white);
        vastus.setBackground(Color.black);
        vastus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nimi = ((JTextField) e.getSource()).getText();
                System.out.println("Nimi on: " + nimi);
            }
        }
        );
      Button exitNupp = new Button("Kinnita");
        exitNupp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nimi = ((JTextField) ((Component) e.getSource()).getParent().getComponent(1)).getText();
                System.out.println(nimi);
                System.exit(0);
            }
        }
        );
        System.out.println(text);
        tippAken.setLayout(new GridLayout(3, 1));
        tippAken.add(kysimus);
        tippAken.add(vastus);
        tippAken.add(exitNupp);
        tippAken.setVisible(true);
    }


}
