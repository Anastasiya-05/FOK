package com.sportcenter.timetable;

import com.sportcenter.activity.*;
import com.sportcenter.file.ReadWriteJsonFileInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;

public class Timetable implements ReadWriteJsonFileInterface{
	
	private int idActivity;
	private DayEnum day;// день недели
	private Integer time;// время тренировки
	private MonthEnum month;
	private Integer idTimetable;// id расписани
	
	public Timetable() {
		
	}
	
	public Timetable(Integer idActivity,  DayEnum day, MonthEnum month, Integer time) {
		this.idActivity = idActivity;
		this.day = day;
		this.time = time;
		this.month = month;
		this.idTimetable = this.hashCode();
	}
	
	
	
	
	public Integer getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(Integer idActivity) {
		this.idActivity = idActivity;
	}

	

	public DayEnum getDay() {
		return day;
	}

	public void setDay(DayEnum day) {
		this.day = day;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
	
	public void changeIdKey() {
		this.idTimetable++;
	}
	
	

	public MonthEnum getMonth() {
		return month;
	}

	public void setMonth(MonthEnum month) {
		this.month = month;
	}

	@Override
	public List<String> getStringForJson() {
		List<String> list = new ArrayList<>();
		list.add(String.valueOf(this.idActivity));
		list.add(String.valueOf(this.day));
		list.add(String.valueOf(this.time));
		list.add(String.valueOf(this.month));
		list.add(String.valueOf(this.idTimetable));
		return list;
	}
	@Override
	public void setJsonObject(String key, JSONArray obj) {
		this.idActivity = obj.getInt(0);
		this.day = DayEnum.valueOf(obj.getString(1));
		this.time = obj.getInt(2);
		this.month = MonthEnum.valueOf(obj.getString(3));
		this.idTimetable = Integer.valueOf(key);
		
	}
	@Override
	public String getIdKey() {
		return String.valueOf(this.idTimetable);
	}
	
	
	

	public enum DayEnum {
		Mo, Tu, We, Th, Fr, Sa, Su
		
	}

	public enum MonthEnum {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sept, Oct, Nov, Dec
		
	}

}