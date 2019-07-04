package com.sportcenter.stuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.sportcenter.activity.ActivityEnum;
import com.sportcenter.file.ReadWriteJsonFileInterface;

public class Trainer extends Stuff {

	private ActivityEnum[] training; // название тренировки Йога, Аква...
	private int experience; // опыт работы тренером
	private String[] achievements; // заслуги, звания

	public Trainer() {
	}

	public Trainer(String lastName, String firstName, String patronymic, String position, int phoneNumber,
			int workingHours, float salary, ActivityEnum[] training, int experience, String[] achievements) {
		super(lastName, firstName, patronymic, position, phoneNumber, workingHours, salary);
		this.training = training;
		this.experience = experience;
		this.achievements = achievements;
		

	}

	public ActivityEnum[] getTraining() {
		return training;
	}

	public void setTraining(ActivityEnum[] training) {
		this.training = training;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String[] getAchievements() {
		return achievements;
	}

	public void setAchievements(String[] achievements) {
		this.achievements = achievements;
	}

	public void changeIdKey() {
		this.id++;
	}

	
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
		list.add(Arrays.toString(this.training));
		list.add(String.valueOf(this.experience));
		list.add(Arrays.toString(this.achievements));
		return list;
	}

	@Override
	public void setJsonObject(String key, JSONArray obj) {
		this.lastName = obj.getString(0);
		this.firstName = obj.getString(1);
		this.patronymic = obj.getString(2);
		this.position = obj.getString(3);
		this.phoneNumber = obj.getInt(4);
		this.workingHours = obj.getInt(5);
		this.salary = obj.getFloat(6);
		String[] activites = obj.getString(7).substring(1, obj.getString(7).length() - 1).split(", ");
		this.training = new ActivityEnum[activites.length];
		int i = 0;
		for (String activity : activites) {
			this.training[i] = ActivityEnum.valueOf(activity);
			i++;
		}
		this.experience = obj.getInt(8);
		this.achievements = obj.getString(9).substring(1, obj.getString(9).length() - 1).split(", ");

		this.id = Integer.valueOf(key);

	}


	@Override
	public String toString() {
		return "Trainer [training=" + Arrays.toString(training) + ", experience=" + experience + ", achievements="
				+ Arrays.toString(achievements) + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", patronymic=" + patronymic + ", position=" + position + ", phoneNumber=" + phoneNumber
				+ ", workingHours=" + workingHours + ", salary=" + salary + ", id=" + id + "]";
	}

//	// Перенести в другой класс типа controller
//	public void showAllStaffData() { // преопределяемый метод выводит информауию о тренере
//		System.out.println("ФИО тренера: " + getFullName());
//		System.out.println("Должность: " + position);
//		System.out.println("Номер телефона  " + phoneNumber);
//		System.out.println("Количество отработанных часов за месяц  " + workingHours);
//		System.out.println("Стоимость одного часа работы (руб) " + salary);
//		System.out.println("Список тренировок  " + getTrainingList());
//		System.out.println("Опыт (кол-во лет  )" + experience);
//		System.out.println("Заслугии, звания  " + getAchievementsList());
//	}
//
//	private String getFullName() { // метод получения ФИО
//		return lastName + " " + firstName + " " + patronymic;
//	}
//
//	private String getTrainingList() { // метод получения списка тренировок
//		StringBuilder trainingList = new StringBuilder();
//		for (ActivityEnum tr : training) {
//			trainingList.append(tr);
//			trainingList.append(", ");
//		}
//		return trainingList.toString();
//	}
//
//	private String getAchievementsList() { // метод получения списка званий и заслуг тренера
//		StringBuilder achievementsList = new StringBuilder();
//		for (String ach : achievements) {
//			achievementsList.append(ach);
//			achievementsList.append(", ");
//		}
//		return achievementsList.toString();
//	}

}
