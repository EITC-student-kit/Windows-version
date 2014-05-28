package timetable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

@SuppressWarnings("serial")
public class WeekView extends JFrame {
	GPanel jp = null;

	static JFrame Weeks;
	static int Acount = 0;
	static DisplayMode mode = java.awt.GraphicsEnvironment
			.getLocalGraphicsEnvironment().getDefaultScreenDevice()
			.getDisplayMode();

	private final static Charset ENCODING = StandardCharsets.UTF_8;
	public static String date;
	public String strBuff = "";

	@SuppressWarnings("static-access")
	public void setDate(String date) {
		this.date = date;
		if (Weeks != null) {
			WeekView.Weeks.dispose();
		}
	}

	public String getDate() {

		return date;
	}


	public WeekView(String date) {

		Weeks = new JFrame("WeekView");
		Acount++;
		this.strBuff = parseIclass();
		setVisible(true);
		setSize(mode.getWidth() - 330, mode.getHeight() - 40);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				Weeks = null;

			}
		});
		setResizable(false);

		jp = new GPanel();
		add(jp);

	}

	public String parseIclass() {
		String sBuffer = "";

		try (Scanner scanner = new Scanner(Paths.get("output.txt"),
				ENCODING.name())) {
			while (scanner.hasNext()) {

				sBuffer += scanner.nextLine() + "\n";
			}
		} catch (IOException e) {
			System.out.println("ei saa lugeda.");
			e.printStackTrace();
		}

		return sBuffer;

	}

	public class GPanel extends JPanel {

		
		int count = 0;

		@Override
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			count++;
			g.fillRect(0, 0, mode.getWidth() - 330, mode.getHeight() - 30);

			g.setColor(Color.white);

			int tundideks = (mode.getHeight() - 30) / 18;

			int seitsmeks = (mode.getWidth() - 330) / 7;
			for (int i = 10; i < mode.getWidth() - 330; i = i + seitsmeks) {
				g.drawLine(i, 10, i, mode.getHeight() - 30);
			}
			g.drawLine(10, mode.getHeight() - 10, mode.getWidth() - 330,
					mode.getHeight() - 10);
			g.setColor(Color.red);
			g.drawString(date, 45 + (seitsmeks * 3), 10);
			g.setColor(Color.white);
			int tunniaeg = 6;
			for (int i = 10; i < mode.getWidth() - 330; i = i + seitsmeks) {
				for (int j = 10; j < mode.getHeight() - 30; j = j + tundideks) {
					g.drawLine(i - 3, j, i + 3, j);

					String aeg = Integer.toString(tunniaeg);
					g.drawString(aeg, i + 5, j);
					tunniaeg++;
					if (tunniaeg == 24) {
						tunniaeg = 6;
					}
				}
			}

			drawClasses(g);
		}

	}

	public void drawClasses(Graphics g) {

		
		String year = date.substring(0, 4);
		String month = date.substring(5, 7);
		String pOneday = String.valueOf(new Integer(date.substring(8, 10)) + 1);
		String pTwoday = String.valueOf(new Integer(date.substring(8, 10)) + 2);
		String pThreeday = String
				.valueOf(new Integer(date.substring(8, 10)) + 3);
		if ((pOneday.length() == 1) || (pTwoday.length() == 1)
				|| (pThreeday.length() == 1)) {
			pOneday = "0" + pOneday;
			pTwoday = "0" + pTwoday;
			pThreeday = "0" + pThreeday;
		}
		String pOnedate = year + "-" + month + "-" + pOneday;
		String pTwodate = year + "-" + month + "-" + pTwoday;
		String pThreedate = year + "-" + month + "-" + pThreeday;
		String mOneday = String.valueOf(new Integer(date.substring(8, 10)) - 1);
		String mTwoday = String.valueOf(new Integer(date.substring(8, 10)) - 2);
		String mThreeday = String
				.valueOf(new Integer(date.substring(8, 10)) - 3);
		if ((mOneday.length() == 1) || (mTwoday.length() == 1)
				|| (mThreeday.length() == 1)) {
			mOneday = "0" + pOneday;
			mTwoday = "0" + pTwoday;
			mThreeday = "0" + pThreeday;
		}
		String mOnedate = year + "-" + month + "-" + mOneday;
		String mTwodate = year + "-" + month + "-" + mTwoday;
		String mThreedate = year + "-" + month + "-" + mThreeday;

		int tundideks = (mode.getHeight() - 30) / 18;
		int seitsmeks = (mode.getWidth() - 330) / 7;
		String[] parts = (strBuff.split(","));
		String nextL = "";
		for (int i = 0; i < parts.length; i++) {
			String subject = parts[i];

			try (Scanner scanner = new Scanner(subject)) {

				while (scanner.hasNext()) {

					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(date)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						double tunniM = new Integer((nextL.substring(35,37)))/(3./2.);
						int tunniMin = (int) tunniM;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 3),
								10 + (tundideks * tunniV) + tunniMin, seitsmeks, 60);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 3),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 3),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 3),
								10 + (tundideks * tunniV) + 40);
						g.drawString(date, 45 + (seitsmeks * 3), 10);

					}
					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(mOnedate)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 2),
								10 + (tundideks * tunniV), seitsmeks, 60);
						g.drawString(mOnedate, 45 + (seitsmeks * 2), 10);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 2),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 2),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 2),
								10 + (tundideks * tunniV) + 40);

					}
					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(mTwodate)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 1),
								10 + (tundideks * tunniV), seitsmeks, 60);
						g.drawString(mTwodate, 45 + (seitsmeks * 1), 10);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 1),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 1),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 1),
								10 + (tundideks * tunniV) + 40);

					}
					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(mThreedate)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 0),
								10 + (tundideks * tunniV), seitsmeks, 60);
						g.drawString(mThreedate, 45 + (seitsmeks * 0), 10);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 0),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 0),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 0),
								10 + (tundideks * tunniV) + 40);

					}

					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(pOnedate)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 4),
								10 + (tundideks * tunniV), seitsmeks, 60);
						g.drawString(pOnedate, 45 + (seitsmeks * 4), 10);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 4),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 4),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 4),
								10 + (tundideks * tunniV) + 40);
					}
					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(pTwodate)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 5),
								10 + (tundideks * tunniV), seitsmeks, 60);
						g.drawString(pTwodate, 45 + (seitsmeks * 5), 10);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 5),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 5),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 5),
								10 + (tundideks * tunniV) + 40);
					}
					if (nextL.contains("Start Date and Time: ")
							&& nextL.contains(pThreedate)) {

						int tunniV = new Integer(nextL.substring(32, 34)) - 6;
						g.setColor(Color.orange);

						g.fillRect(10 + (seitsmeks * 6),
								10 + (tundideks * tunniV), seitsmeks, 60);

						g.drawString(pThreedate, 45 + (seitsmeks * 6), 10);

						String[] classParts = (subject.split("\n"));
						g.setColor(Color.red);
						classParts[1] = classParts[1].replace("Class name: ",
								"");
						if (classParts[1].length() > 15) {
							classParts[1] = classParts[1].substring(0,
									classParts[1].length() / 2);
						}
						g.drawString(classParts[1], 15 + (seitsmeks * 6),
								10 + (tundideks * tunniV) + 10);
						classParts[6] = classParts[6].replace("Class room: ",
								"");
						g.drawString(classParts[6], 15 + (seitsmeks * 6),
								10 + (tundideks * tunniV) + 25);
						classParts[7] = classParts[7].replace("Academician: ",
								"");
						g.drawString(classParts[7], 15 + (seitsmeks * 6),
								10 + (tundideks * tunniV) + 40);
					}

					nextL = scanner.nextLine();

				}
			}

		}
		g.setColor(Color.white);
		for (int i = 10; i < mode.getWidth() - 330; i = i + seitsmeks) {
			g.drawLine(i, 10, i, mode.getHeight() - 30);
		}
		repaint();

	}

}