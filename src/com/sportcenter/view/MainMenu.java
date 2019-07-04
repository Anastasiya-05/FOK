package com.sportcenter.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class MainMenu extends JFrame implements GeneratingMenuInterface{
	JFrame jFrame;
	JLabel jLabel;
	private JButton btActivity;
	private JButton btTimetable;
	private JButton btResource;
	private JButton btTrainer;


	//считываем ответ от пользователя с консоли
	protected static int getAnswer() {
		Scanner in = new Scanner(System.in);
		int answer = in.nextInt();
		return answer;
	}


	MainMenu() {}
		//главный метод в котором создается различные типы меню, в зависимости от ответа пользователя



	public static void main(String args[]) {
		// создать фрейм в потоке диспетчеризации событий
		SwingUtilities.invokeLater(() -> new MainMenu());
	}

	@Override
	public void createMenu() {

			GeneratingMenuInterface menu = null;
			jFrame = new JFrame("Welcome to our sport center.\r\n" +
					"We work from Monday to Sunday from 8 to 22.");
			jFrame.setLayout(new FlowLayout());
			jFrame.setSize(500, 300);
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			btActivity = new JButton("1. Work with Activity");
			btResource = new JButton("2. Work With Timetable");
			btTimetable = new JButton("3. Work with Resource");
			btTrainer = new JButton("4. Work with Trainer");
			btActivity.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent actionEvent) {
					jLabel.setText("Нажата кнопка \"Добавить\".");
				}
			});
			jFrame.add(btActivity);
			jFrame.add(btResource);
			jFrame.add(btTimetable);
			jFrame.add(btTrainer);
			jFrame.setVisible(true);


			System.out.println("");


			/*System.out.println("Welcome to our sport center.\r\n" +
					"We work from Monday to Sunday from 8 to 22.");
			System.out.println("Select act what do you want to do:");
			System.out.println("1. Work with Activity");
			System.out.println("2. Work With Timetable");
			System.out.println("3. Work with Resource");
			System.out.println("4. Work with Trainer");*/

			switch (getAnswer()) {
				case 1:
					menu = new ActivityMenu();//создаем меню активити
					break;
				case 2:
					menu = new TimetableMenu();//создаем меню расписания
					break;
				case 3:
					menu = new ResourceMenu();//создаем меню ресурса
					break;
				case 4:
					menu = new TrainerMenu();//создаем меню тренера
					break;
			}
			//return null;


	}
}

