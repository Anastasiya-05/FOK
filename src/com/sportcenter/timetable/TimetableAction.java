package com.sportcenter.timetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sportcenter.activity.ActivityAction;
import com.sportcenter.file.ReadWriteJsonFileAbstr;
import com.sportcenter.file.ReadWriteJsonFileInterface;
import com.sportcenter.generic.HelperGenericClass;
import com.sportcenter.generic.ItemGenericInterface;

import com.sportcenter.timetable.Timetable.DayEnum;
import com.sportcenter.timetable.Timetable.MonthEnum;

public class TimetableAction extends HelperGenericClass<Timetable> implements ItemGenericInterface<Timetable>{
	
	
	private final String fileName = "json\\timetable.json";

	@Override
	protected String getFileName() {
		// TODO Auto-generated method stub
		return fileName;
	}
	
	
	public TimetableAction() {
		super();
	}

//	
//	public  Collection<ReadWriteJsonFileInterface> getAllTrainerInfo() {
//		return data.values();
//	}
	
	public  List<Integer> getAllExistingActivityId(){
		List<Integer> activityId = new ArrayList<>();

		for (ReadWriteJsonFileInterface rwfi: data.values()) {
			Timetable timetable = (Timetable) rwfi;
			activityId.add(timetable.getIdActivity());
		}
		return activityId;
	}
	
	//метод, который возвращет время которое занято
	public List<Integer> getListTime(MonthEnum month, DayEnum dayEnum, Integer idActivity){
		List<Integer> time = new ArrayList<>(); 
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Timetable timetable = (Timetable) rwfi;
			if(month.equals(timetable.getMonth()) && dayEnum.equals(timetable.getDay()) && idActivity.equals(timetable.getIdActivity())) { // проверяем, что если месяц, день и активность совпадают, то заносим это время 
				time.add(timetable.getTime());
				ActivityAction activityAction = new ActivityAction();
				if(activityAction.getDuration(timetable.getIdActivity().toString()) == 2) { //если продолжительность тренеровки равно 2, то заносим время начала тренировки + 1 в список
					time.add(timetable.getTime() + 1);
				}
					
			}
		}
		return time;
	}
	
	//возвращает все расписание на заданный месяц
	public List<Timetable> getTimetableListByMonth(MonthEnum month){
		List<Timetable> timetables = new ArrayList<>();
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Timetable timetable = (Timetable) rwfi;
			if(timetable.getMonth().equals(month)) {
				timetables.add(timetable);
			}
		}
		return timetables;
	}
	//возвращет лист расписаний на заданный активности id
	public List<Timetable> getTimetableListByActivityID(Integer activityId){
		List<Timetable> timetables = new ArrayList<>();
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Timetable timetable = (Timetable) rwfi;
			if(timetable.getIdActivity().equals(activityId)) {
				timetables.add(timetable);
			}
		}
		return timetables;
	}


	@Override
	public void addNewItem(Timetable item) {
		while (true){
			Timetable val = (Timetable) this.data.putIfAbsent(item.getIdKey(), item);
			if (val != null) item.changeIdKey();
			else break;
		}		
		saveFile();
		
	}


	@Override
	public void removeItemById(String id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getAllInfoAboutItemById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Timetable getItemById(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
