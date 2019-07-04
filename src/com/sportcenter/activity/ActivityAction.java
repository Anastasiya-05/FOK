package com.sportcenter.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sportcenter.file.ReadWriteJsonFileAbstr;
import com.sportcenter.file.ReadWriteJsonFileInterface;
import com.sportcenter.generic.HelperGenericClass;
import com.sportcenter.generic.ItemGenericInterface;
import com.sportcenter.timetable.Timetable.DayEnum;



public class ActivityAction extends HelperGenericClass<Activity> implements ItemGenericInterface<Activity>{
	
	private final String fileName = "json\\activity.json";

	@Override
	protected String getFileName() {
		return fileName;
	}

	
//	public  Collection<ReadWriteJsonFileInterface> getAllActivityInfo() {
//		return data.values();
//	}
	//возвращает название всех существующих активностей
	public List<ActivityEnum> getTitleAllExistingActivity(){
		List<ActivityEnum> titleActivity = new ArrayList<>();
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Activity activity  = (Activity) rwfi;
			titleActivity.add(activity.getActivityName());
		}
		return titleActivity;
	}
	
	
	public int getIdByActivityName(ActivityEnum activityEnum) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Activity activity  = (Activity) rwfi;
			if (activity.getActivityName().equals(activityEnum))
			{
				return activity.getId();
			}
		}
		return 0;
	}
	
	
	
	public double getDuration(String id) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Activity activity  = (Activity) rwfi;
			if (activity.getIdKey().equals(id))
			{
				return activity.getTrainingDuration();
			}
		}
		return 0;
	}

	//возвращам объект по его id
	@Override
	public void addNewItem(Activity activity) {
		while (true){
			Activity val = (Activity) this.data.putIfAbsent(activity.getIdKey(), activity);//создаем объект активность. this.data.putIfAbsent(activity.getIdKey(), activity) - добавляем в hashMap, который находится в классе ReadWriteJsonFileAbstr
			if (val != null) activity.changeIdKey();
			else break;
		}		
		saveFile();//сохраняем в json
		
	}


	@Override
	public void removeItemById(String id) { // удаление активити по id
		Activity activity = (Activity) this.data.get(id);
		if (activity != null) {
			this.data.remove(id);
			saveFile();
			System.out.println("Delete successfully");
		} else
			System.out.println("Error delete trainer" + id);
		
	}


	@Override
	public String getAllInfoAboutItemById(String id) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Activity activity  = (Activity) rwfi;
			if (activity.getIdKey().equals(id))
			{
				return activity.toString();
			}
		}
		return "";
	}


	@Override
	public Activity getItemById(String id) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Activity activity  = (Activity) rwfi;
			if (activity.getIdKey().equals(id))
			{
				return activity;
			}
		}
		return null;
	}
}
