package com.sportcenter.view;

import com.sportcenter.resourse.Resource;
import com.sportcenter.resourse.Resource.ResourceType;
import com.sportcenter.resourse.ResourceAction;


public class ResourceMenu implements GeneratingMenuInterface, ActionsMenuInterface {
	
	public ResourceMenu() {
		createMenu();
	}
	@Override
	//метод для начального выбора, что делаем с ресурсами
	public void selectWhatToDo() {
		System.out.println("Select what do you whant to do?");
		System.out.println("1. Add new resorce");
		System.out.println("2. Show all existing resource");
		System.out.println("3. Delete resource");
	}
	// метод который возвращет выбраный тип ресурса
	private ResourceType selectResourceType() {
		System.out.println("Select resource what do you whant to add");
		System.out.println("1. " + ResourceType.AerobicsRoom);
		System.out.println("2. " + ResourceType.Gym);
		System.out.println("3. " + ResourceType.Pool);
		System.out.println("4. " + ResourceType.SportsRoom);
		switch (MainMenu.getAnswer()) {
		case 1:
			return ResourceType.AerobicsRoom;
		case 2:
			return ResourceType.Gym;
		case 3:
			return ResourceType.Pool;
		case 4:
			return ResourceType.SportsRoom;
		}
		return null;
	}
	//метод для ввода вместимости зала
	private int selectMaxPeople() {
		System.out.println("Enter resource capacity:");
		return MainMenu.getAnswer();
	}
	
	//меню для создания ресурса
	private void createResourceMenu() {
		ResourceType resourceType = selectResourceType();// метод который возвращет выбраный тип ресурса
		int maxpeople = selectMaxPeople();//метод для ввода вместимости зала
		Resource resource = new Resource(resourceType, maxpeople);
		ResourceAction resourceAction = new ResourceAction();
		resourceAction.addNewItem(resource);//сохраняем новый ресурс
	}
	
	@Override
	public void showAllExistingItem() {
		ResourceAction resourceAction = new ResourceAction();
		int i = 1;
		for (Resource resource : resourceAction.getListAllItem()) {//бегаем по листу ресурсов
			System.out.println(i +". " + resource);
			i++;
		}
		
	}
	@Override
	public void deleteItem() {
		System.out.println("What resource do you prefer to delete?");
		showAllExistingItem();
		ResourceAction resourceAction = new ResourceAction();
		resourceAction.removeItemById(resourceAction.getListAllItem().get(MainMenu.getAnswer() - 1 ).getIdKey());//resourceAction.getListAllItem() -лист всех возможных активностей
																												//resourceAction.getListAllItem().get(MainMenu.getAnswer() - 1 ) - возвращаем объект типа активность; MainMenu.getAnswer() - 1 - так ка нумирация с 0, то от ответа надо отнять 1 
																												//resourceAction.getListAllItem().get(MainMenu.getAnswer() - 1 ).getIdKey() -получаем id объекта
		
	}

	@Override
	public void createMenu() {
		selectWhatToDo();//метод для начального выбора, что делаем с ресурсами
		switch (MainMenu.getAnswer()) {// обрабатываем ответ пользователя
		case 1:
			createResourceMenu();//меню для создания ресурса
			break;
		case 2:
			showAllExistingItem();//выводит все существующие ресурсы
			break;
		case 3:
			deleteItem();//удаление ресурса
			break;
		}
		
	}
	

}
