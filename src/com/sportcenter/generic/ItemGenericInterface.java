package com.sportcenter.generic;

import java.util.List;



//интерфейс обобщений
public interface ItemGenericInterface<T> {
	//сохранения элемента (активити, тренер и т.д.)
	public void addNewItem(T item);
	//удален элемента (активити, тренер и т.д.)
	public void removeItemById(String id);
	//получение информации элемента (активити, тренер и т.д.)
	public String getAllInfoAboutItemById(String id); 
	//возвращам объект по его id
	public T getItemById(String id);
	
	
}
