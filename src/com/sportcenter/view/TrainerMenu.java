package com.sportcenter.view;

import com.sportcenter.stuff.Trainer;
import com.sportcenter.stuff.TrainerAction;

public class TrainerMenu implements GeneratingMenuInterface, ActionsMenuInterface {

	public TrainerMenu() {
		createMenu();
	}
	
	@Override
	public void showAllExistingItem() {
		TrainerAction trainerAction = new TrainerAction();
		int i = 1;
		for (Trainer trainer : trainerAction.getListAllItem()) {//бегаем по листу тренеров
			System.out.println(i +". " + trainer);
			i++;
		}
		
	}

	//меню для создания ресурса
	private void createTrainerMenu() {
		//TODO
	}
	
	@Override
	public void deleteItem() {
		System.out.println("Which trainer do you prefer to delete?");
		showAllExistingItem();
		TrainerAction trainerAction = new TrainerAction();
		trainerAction.removeItemById(trainerAction.getListAllItem().get(MainMenu.getAnswer() - 1 ).getIdKey());//trainerAction.getListAllItem() -лист всех возможных тренеров
																												//trainerAction.getListAllItem().get(MainMenu.getAnswer() - 1 ) - возвращаем объект типа тренер(Trainer); MainMenu.getAnswer() - 1 - так как нумерация идет с 0, то от ответа надо отнять 1 
																												//trainerAction.getListAllItem().get(MainMenu.getAnswer() - 1 ).getIdKey() -получаем id объекта
		
	}

	@Override
	public void selectWhatToDo() {
		// TODO Auto-generated method stub
		System.out.println("1. create trainer new activity ");
		System.out.println("2. show all existing trainers");
		System.out.println("3. delete trainer");
		
	}

	@Override
	public void createMenu() {
		selectWhatToDo();//метод для начального выбора, что делаем с тренерами
		switch (MainMenu.getAnswer()) {// обрабатываем ответ пользователя
		case 1:
			createTrainerMenu();//меню для создания тренера
			break;
		case 2:
			showAllExistingItem();//выводит все существующие тренера
			break;
		case 3:
			deleteItem();//удаление тренера
			break;
		}
		
	}

}
