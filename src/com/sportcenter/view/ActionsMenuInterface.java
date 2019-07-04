package com.sportcenter.view;
//интерфейс для действий, которые присутствуют во всех меню
public interface ActionsMenuInterface {
	//вывод всех существующих
	void showAllExistingItem();
	//удаление 
	void deleteItem();
	//выбор что делать
	void selectWhatToDo();
	
}
