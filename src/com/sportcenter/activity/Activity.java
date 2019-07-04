package com.sportcenter.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.sportcenter.file.ReadWriteJsonFileInterface;
import com.sportcenter.resourse.Resource;
import com.sportcenter.resourse.ResourceAction;
import com.sportcenter.stuff.Trainer;
import com.sportcenter.stuff.TrainerAction;

public class Activity implements ReadWriteJsonFileInterface {

	private ActivityEnum activityName; // Наименование тренировки (потом отдавать в timetable (нужен метод))
	private Integer trainerId; // id тренера
	private double trainingDuration; // Продолжительность тренировки
	// private int numPeople; // не требуется это поле, так как в ресурсах есть
	// максимальное вместимость людей
	// private int numOfClassesWeek; // это все должно быть в timetable
	// private String dayOfWeek; // это все должно быть в timetable
	// private String time; // это все должно быть в timetable
	// private String formOfTraining; // переменная с тренером , без тренера
	private Integer resourceID; // id где проходит трренеровка

	protected int id;

	public Activity() {
	}

	public Activity(ActivityEnum activityName, Integer trainerId, double trainingDuration, Integer resourceID) {
		this.activityName = activityName;
		this.trainerId = trainerId;
		this.trainingDuration = trainingDuration;
		this.resourceID = resourceID;
		this.id = this.hashCode();
	}

	public Activity(ActivityEnum activityName, double trainingDuration, int resourceID) {
		this.activityName = activityName;
		this.trainerId = 0;
		this.trainingDuration = trainingDuration;

		this.resourceID = resourceID;
		this.id = this.hashCode();
	}


	public void showListActivity() {
		// написать метод список тренировок

	}

	public void showScheduleActivity() {
		// сделать метод для выведения день недели время наименование тренировки
	}

	public ActivityEnum getActivityName() {
		return activityName;
	}

	public void setActivityName(ActivityEnum activityName) {
		this.activityName = activityName;
	}

	public double getTrainingDuration() {
		return trainingDuration;
	}

	public void setTrainingDuration(float trainingDuration) {
		this.trainingDuration = trainingDuration;
	}

	public void setTrainingDuration(double trainingDuration) {
		this.trainingDuration = trainingDuration;
	}

	public int getTrainerId() {
		return trainerId;
	}


	public int getResourceID() {
		return resourceID;
	}
	
	

	public int getId() {
		return id;
	}

	

	@Override
	public List<String> getStringForJson() {
		List<String> list = new ArrayList<>();
		list.add(String.valueOf(this.activityName));
		list.add(String.valueOf(this.trainerId));
		list.add(String.valueOf(this.trainingDuration));
		list.add(String.valueOf(this.resourceID));
		return list;
	}

	@Override
	public void setJsonObject(String key, JSONArray obj) {
		this.activityName = ActivityEnum.valueOf(obj.getString(0));
		this.trainerId = obj.getInt(1);
		this.trainingDuration = obj.getDouble(2);
		this.resourceID = obj.getInt(3);

		this.id = Integer.valueOf(key);

	}

	@Override
	public String getIdKey() {
		// TODO Auto-generated method stub
		return String.valueOf(this.id);
	}

	public void changeIdKey() {
		this.id++;
	}

	@Override
	public String toString() {
		TrainerAction tr = new TrainerAction();
		ResourceAction rs = new ResourceAction();
		return activityName + ", trainer name:" + tr.getTrainerFIO(trainerId.toString()) + ", training duration ="
				+ trainingDuration + ", " + rs.getAllInfoAboutItemById(resourceID.toString()) + "]";
	}




//	public String getFormOfTraining() {
//		return formOfTraining;
//	}
//
//	public void setFormOfTraining(String formOfTraining) {
//		this.formOfTraining = formOfTraining;
//	}
	
	
}