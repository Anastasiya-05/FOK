package com.sportcenter.stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;


import com.sportcenter.file.ReadWriteJsonFileInterface;

//суперкласс Stuff

public class Stuff implements ReadWriteJsonFileInterface{

	protected String lastName; // Фамилия сотрудника
	protected String firstName; // Имя сотрудника
	protected String patronymic; // Отчество сотрудника
	protected String position; // должность
	protected int phoneNumber; // телефон
	protected int workingHours; // количество рабочих часов в месяц
	protected float salary; // стоимость одного рабочего часа
	
	protected int id;

	public Stuff() {
	}

	public Stuff(String lastName, String firstName, String patronymic, String position, int phoneNumber,
			int workingHours, float salary) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.position = position;
		this.phoneNumber = phoneNumber;
		this.workingHours = workingHours;
		this.salary = salary;
		
		this.id = this.hashCode();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

//	public void showAllStaffData() {
//	}

	@Override
	public List<String> getStringForJson() {
		
		List<String> list = new ArrayList<>();
		list.add(this.lastName);
		list.add(this.firstName);
		list.add(this.patronymic);
		list.add(this.position);
		list.add(String.valueOf(this.phoneNumber));
		list.add(String.valueOf(this.workingHours));
		list.add(String.valueOf(this.salary));
		return list;
		
		
	}

	@Override
	public void setJsonObject(String key, JSONArray obj) {
		// TODO Auto-generated method stub
		this.lastName = obj.getString(0);
		this.firstName =  obj.getString(1);
		this.patronymic = obj.getString(2);
		this.position = obj.getString(3);
		this.phoneNumber = obj.getInt(4);
		this.workingHours = obj.getInt(5);
		this.salary = obj.getFloat(6);
		
		this.id = Integer.valueOf(key);
		
	}

	@Override
	public String getIdKey() {
		// TODO Auto-generated method stub
		return String.valueOf(this.id);
	}

	@Override
	public String toString() {
		return "Stuff [lastName=" + lastName + ", firstName=" + firstName + ", patronymic=" + patronymic + ", position="
				+ position + ", phoneNumber=" + phoneNumber + ", workingHours=" + workingHours + ", salary=" + salary
				+ ", id=" + id + "]";
	}
	
	

	
}
