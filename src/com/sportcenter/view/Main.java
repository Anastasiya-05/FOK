package com.sportcenter.view;

public class Main {

	

	public static void main(String[] args) {
//		
//		TrainerAction trainerAction = new TrainerAction();
//		System.out.println(trainerAction.getListAllItem());
		
		//MainMenu - главный класс, который содержит в себе статический метод selectMenu. Данный метод возвращает объект типа GeneratingMenuInterface. 
		//Любой класс меню должен имплементировать этот интерфейс, кроме MainMenu
		while(true) {

			MainMenu mm=new MainMenu();
			mm.createMenu();
		}

	}
}
