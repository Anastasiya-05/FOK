package com.sportcenter.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sportcenter.resourse.Resource;
import com.sportcenter.stuff.Trainer;

//абстрактный класс, который записывает в json и открывает файл с джейсоном
//абстрактный, чтобы не передовать имя файла в методы
public abstract class ReadWriteJsonFileAbstr {
	
	protected HashMap<String, ReadWriteJsonFileInterface> data;	
	
	protected abstract String getFileName();
//	
//	
	public ReadWriteJsonFileAbstr() {	
		this.data = new HashMap<>();
		openFile();
	}

	//создаем объект класса который наследует
	protected ReadWriteJsonFileInterface getInstanceDate()
	{
		String name = this.getClass().getName();//получаем имя класса, который наследует данный абстрактный класс
		name = name.substring(0, name.indexOf("Action"));// обрубаем в этом имени актион		
		try {
			ReadWriteJsonFileInterface dat =  (ReadWriteJsonFileInterface) Class.forName(name).newInstance(); //создаем объект класса, который наследовал данный абстрактный класс, только без Action в конце
			return dat;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
//	
//	открытия файла и считывания информации, которая была уже записана
	public void openFile(){
		JSONObject object = null;
		try {
			String txtfile = "";
			FileInputStream inputStream = new FileInputStream(getFileName());
			try {
			    txtfile = IOUtils.toString(inputStream);//считали всю информацию из файла в строку
			    inputStream.close();
			} catch (IOException e){
				e.printStackTrace();
			}
			object = new JSONObject(txtfile);// создали json объект из строки
			
			for(String key : object.keySet()){ // бегаем по json объекту
				JSONArray arr = (JSONArray) object.get(key);
				ReadWriteJsonFileInterface dat = getInstanceDate(); //создаем объект класса который наследует
				dat.setJsonObject(key, arr);//извлекаем данные из json				
				this.data.put(dat.getIdKey(), dat);//закидываем map
			}			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	//запись в файл
	public void saveFile(){
		JSONObject jsonobj = new JSONObject();	// моздаем json объект
		for(ReadWriteJsonFileInterface dat : this.data.values()){ // бегаем по значениям map
			jsonobj.put(dat.getIdKey(), dat.getStringForJson()); //заполняем json объект значениями из map
		} 
		//запись в файл
		FileWriter fw;
		try {
			fw = new FileWriter(getFileName(), false); 
			fw.write(jsonobj.toString());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}
	

}
