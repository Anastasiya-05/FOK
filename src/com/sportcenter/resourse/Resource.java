package com.sportcenter.resourse;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.sportcenter.file.ReadWriteJsonFileInterface;

public class Resource implements ReadWriteJsonFileInterface {

	private int idResource;
	// private String hall; //название зала
	private ResourceType hall; // название зала
	private int maxpeople; // максимальная вместимость людей
	// private double area; // площадь помещения

	// Конструктор класса SportCenterResource
	public Resource() {
	}

	public Resource(ResourceType hall, int maxpeople) {// , double area) {
		this.hall = hall;
		this.maxpeople = maxpeople;
		// this.area = area;
		this.idResource = this.hashCode();
	}

	// Сеттеры и геттеры для переменных
	public ResourceType getName() {
		return hall;
	}

	public void setName(ResourceType hall) {
		this.hall = hall;
	}

	public int getMaxpeople() {
		return maxpeople;
	}

	public void setMaxpeople(int maxpeople) {
		this.maxpeople = maxpeople;
	}
	
	public void changeIdKey() {
		this.idResource++;
	}

//	public double getArea() {
//		return area;
//	}
//
//	public void setArea(double area) {
//		this.area = area;
//	}

	/// !!! Добавить метод для вывода всей информации

	public enum ResourceType {
		Pool, Gym, AerobicsRoom, SportsRoom
	}
	/*
	 * public static void main(String args[]) { Resourse pool = new Resourse();
	 * Resourse gym = new Resourse(); Resourse aerobicsRoom = new Resourse();
	 * Resourse sportsRoom = new Resourse(); pool.setName("Бассейн:");
	 * pool.setMaxpeople(60); pool.setArea(200); System.out.println(pool.getName());
	 * System.out.println("Макс.вместимость - " + pool.getMaxpeople() + " человек");
	 * System.out.println("Площадь - " + pool.getArea() + " кв.м");
	 * System.out.println(" "); gym.setName("Тренажёрный зал:");
	 * gym.setMaxpeople(70); gym.setArea(300); System.out.println(gym.getName());
	 * System.out.println("Макс.вместимость - " + gym.getMaxpeople() + " человек");
	 * System.out.println("Площадь - " + gym.getArea() + " кв.м");
	 * System.out.println(" "); aerobicsRoom.setName("Зал аэробики:");
	 * aerobicsRoom.setMaxpeople(40); aerobicsRoom.setArea(130.5);
	 * System.out.println(aerobicsRoom.getName());
	 * System.out.println("Макс.вместимость - " + aerobicsRoom.getMaxpeople() +
	 * " человек"); System.out.println("Площадь - " + aerobicsRoom.getArea() +
	 * " кв.м"); System.out.println(" ");
	 * sportsRoom.setName("Зал игровых видов спорта:");
	 * sportsRoom.setMaxpeople(100); sportsRoom.setArea(430.4);
	 * System.out.println(sportsRoom.getName());
	 * System.out.println("Макс.вместимость - " + sportsRoom.getMaxpeople() +
	 * " человек"); System.out.println("Площадь - " + sportsRoom.getArea() +
	 * " кв.м"); }
	 */

	@Override
	public List<String> getStringForJson() {
		List<String> list = new ArrayList<>();
		list.add(String.valueOf(this.hall));
		list.add(String.valueOf(this.maxpeople));
		return list;
	}

	@Override
	public void setJsonObject(String key, JSONArray obj) {

		this.hall = ResourceType.valueOf(obj.getString(0));
		this.maxpeople = obj.getInt(1);
		this.idResource = Integer.valueOf(key);

	}
	//возвращаем id ресурса типа стринг
	@Override
	public String getIdKey() {
		return String.valueOf(this.idResource);
	}

	@Override
	public String toString() {
		return "Resourse Name: " + hall + ", max people=" + maxpeople;
	}
	
	
}
