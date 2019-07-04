package com.sportcenter.file;

import java.util.List;

import org.json.JSONArray;
//методы для подготовки объекта к записи в файл и извлечения
public interface ReadWriteJsonFileInterface {
	
	//Возвращает лист заполненый полями класса.подготовка класса для записи в файл - прсто добавляем поля класса в лист(кроме поля id)
	List<String> getStringForJson();
	//извлекаем данные из json
	void setJsonObject(String key, JSONArray obj);
	//возвращаем id ресурса типа стринг
	String getIdKey();
	
}
