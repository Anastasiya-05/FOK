package com.sportcenter.generic;

import java.util.ArrayList;
import java.util.List;

import com.sportcenter.file.ReadWriteJsonFileAbstr;
import com.sportcenter.file.ReadWriteJsonFileInterface;

public abstract class HelperGenericClass<T> extends ReadWriteJsonFileAbstr{
	
	//возвращает лист объектов, которые есть в файле
	public List<T> getListAllItem(){
		List<T> itemList = new ArrayList<>();
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			T item  = (T) rwfi;
			itemList.add(item);
		}
		return itemList;
	}

}
