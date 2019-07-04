package com.sportcenter.view;


import com.sportcenter.activity.ActivityAction;
import com.sportcenter.activity.ActivityEnum;
import com.sportcenter.timetable.Timetable;
import com.sportcenter.timetable.Timetable.DayEnum;
import com.sportcenter.timetable.Timetable.MonthEnum;
import com.sportcenter.timetable.TimetableAction;
import com.sportcenter.view.ActionsMenuInterface;
import com.sportcenter.view.GeneratingMenuInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimetableMenu implements GeneratingMenuInterface, ActionsMenuInterface {

	ArrayList<Integer> allTimeAvailable = new ArrayList<>();
	final DayEnum[] day = {  DayEnum.Mo, DayEnum.Tu, DayEnum.We, DayEnum.Th, DayEnum.Fr, DayEnum.Sa };

	//блок инициализации(выполняется до запуска конструктора)
	{
		for (int i = 8; i <= 22; i++) {
			allTimeAvailable.add(i);
		}
	};

	public TimetableMenu() {
		createMenu();
	}
	@Override
	public void selectWhatToDo() {
		System.out.println("What do you want to see in timetable?");
		System.out.println("1. Show timetable");
		System.out.println("2. Add new activity in timetable");
	}
	//метод возвращает id-активности
	private int selectActivity() {
		System.out.println("Select activity what do you want to add in timetable");
		ActivityAction activityAction = new ActivityAction();
		int i = 1;
		//TimetableAction timetableAction = new TimetableAction();
		for (ActivityEnum activity : activityAction.getTitleAllExistingActivity()) {
			System.out.println(i + ". " + activity.toString());
			i++;
		}
		return activityAction
				.getIdByActivityName(activityAction.getTitleAllExistingActivity().get(MainMenu.getAnswer() - 1));
	}

	private int selectActivityByExistingActivity() {
		System.out.println("Select activity");
		ActivityAction activityAction = new ActivityAction();
		int i = 1;
		TimetableAction timetableAction = new TimetableAction();
		for(Integer activityId :timetableAction.getAllExistingActivityId()){
			System.out.println(i + ". " + activityAction.getItemById(activityId.toString()).getActivityName());
			i++;
		}
		return timetableAction.getAllExistingActivityId().get(MainMenu.getAnswer() - 1);
	}
	//метод который предоставляет выбор дня недели
	private DayEnum selectDayInWeek() {
		System.out.println("Choose a day of the week");
		System.out.println("1. " + DayEnum.Mo);
		System.out.println("2. " + DayEnum.Tu);
		System.out.println("3. " + DayEnum.We);
		System.out.println("4. " + DayEnum.Th);
		System.out.println("5. " + DayEnum.Fr);
		System.out.println("6. " + DayEnum.Sa);
		System.out.println("7. " + DayEnum.Su);
		switch (MainMenu.getAnswer()) {
		case 1:
			return DayEnum.Mo;
		case 2:
			return DayEnum.Tu;
		case 3:
			return DayEnum.We;
		case 4:
			return DayEnum.Th;
		case 5:
			return DayEnum.Fr;
		case 6:
			return DayEnum.Sa;
		case 7:
			return DayEnum.Su;
		}
		return null;
	}
	//метод выбора месяца
	private MonthEnum selectMonth() {
		System.out.println("Choose month");
		System.out.println("1. " + MonthEnum.Jan);
		System.out.println("2. " + MonthEnum.Feb);
		System.out.println("3. " + MonthEnum.Mar);
		System.out.println("4. " + MonthEnum.Apr);
		System.out.println("5. " + MonthEnum.May);
		System.out.println("6. " + MonthEnum.Jun);
		System.out.println("7. " + MonthEnum.Jul);
		System.out.println("8. " + MonthEnum.Aug);
		System.out.println("9. " + MonthEnum.Sept);
		System.out.println("10. " + MonthEnum.Oct);
		System.out.println("11. " + MonthEnum.Nov);
		System.out.println("12. " + MonthEnum.Dec);
		switch (MainMenu.getAnswer()) {
		case 1:
			return MonthEnum.Jan;
		case 2:
			return MonthEnum.Feb;
		case 3:
			return MonthEnum.Mar;
		case 4:
			return MonthEnum.Apr;
		case 5:
			return MonthEnum.May;
		case 6:
			return MonthEnum.Jun;
		case 7:
			return MonthEnum.Jul;
		case 8:
			return MonthEnum.Aug;
		case 9:
			return MonthEnum.Sept;
		case 10:
			return MonthEnum.Oct;
		case 11:
			return MonthEnum.Nov;
		case 12:
			return MonthEnum.Dec;
		}
		return null;
	}
	//метод, который предоставляет выбор времени и возвращает, то что мы выбрали
	private Integer selectTime(MonthEnum month, DayEnum dayEnum, Integer idActivity) {
		System.out.println("Select time to activity");
		TimetableAction timetableAction = new TimetableAction();
		List<Integer> busyTime = timetableAction.getListTime(month, dayEnum, idActivity); //timetableAction.getListTime - метод, который возвращет время которое занято
		if (busyTime != null) { // если список с занятым временем не пуст, то удаляем из списка все возможного времени, то время которое занято
			allTimeAvailable.removeAll(busyTime);
		}
		//бегоем по листу
		int i = 1;
		for (Integer time : allTimeAvailable) {
			System.out.println(i + ". " + time);
			i++;
		}
		return allTimeAvailable.get(MainMenu.getAnswer() - 1);// возвращаем из списка время которое выбрали.

	}
	//метод по созданию нового расписания и записи его в файл
	private void createNewTimetable() {
		int activityId = selectActivity(); //метод возвращает id-активности
		DayEnum dayInWeek = selectDayInWeek();//выброный день недели
		MonthEnum month = selectMonth();//выброный месяц
		int time = selectTime(month, dayInWeek, activityId); //выбраное время
		Timetable timetable = new Timetable(activityId, dayInWeek, month, time);
		TimetableAction timetableAction = new TimetableAction();
		timetableAction.addNewItem(timetable); // сохраняем расписание в json

	}


	private List<Timetable> seachTimetableByActivity(List<Timetable> timetableList, Integer activityId){
		List<Timetable> timetableActivitiy = new ArrayList<>();
		for (Timetable timetable : timetableList) {
			if(timetable.getIdActivity().equals(activityId)) { // если активити id совпадат с активити id расписания из списка, то заносим в список
				timetableActivitiy.add(timetable);
			}
		}
		return timetableActivitiy;
	}


	private HashMap<Integer, DayEnum> getDayAndTimeByActivity(List<Timetable> timetableListActivity){
		HashMap<Integer, DayEnum> dayAndTime = new HashMap<>();
		for (Timetable timetable : timetableListActivity) {
			dayAndTime.put(timetable.getTime(), timetable.getDay());
		}
		return dayAndTime;
	}

	//метод, который возвращает первые две буквы от переданной ему строки
	private String getTwoSymbolsInActivityName(ActivityEnum activityEnum) {
		return activityEnum.toString().substring(0, 2);
	}
	//выбраный месяц в зависимости от активити
	private MonthEnum selectMonthByCoosenActivity(Integer activityId) {
		TimetableAction timetableAction = new TimetableAction();
		timetableAction.getTimetableListByActivityID(activityId);
		int i = 1;
		for(Timetable month: timetableAction.getTimetableListByActivityID(activityId)) {
			System.out.println(i + ". " + month.getMonth());
		}
		return timetableAction.getTimetableListByActivityID(activityId).get(MainMenu.getAnswer() - 1).getMonth();
	}

	private void showActivityOnChosenMonth() {

		Integer activityId = selectActivityByExistingActivity(); //id - активити
		MonthEnum month = selectMonthByCoosenActivity(activityId); //выбраный месяц в зависимости от активити
		TimetableAction timetableAction = new TimetableAction();
		List<Timetable> timetableListActivity = seachTimetableByActivity(timetableAction.getTimetableListByMonth(month), activityId);//seachTimetableByActivity- возвращает список расписаний для активности;timetableAction.getTimetableListByMonth(month)-ве расписание по заданному месяцу
		ActivityAction action = new ActivityAction();

		Map<Integer, DayEnum> dayAndTime = getDayAndTimeByActivity(timetableListActivity); //getDayAndTimeByActivity-возвращает Map с временем и датой(где ключ-время, значение-дата) Пример.(8, Mo)-Понедельник в 8

		System.out.println("			" + month + "\n");

		//вывод дней недели в строчку
		for (int j = 0; j < day.length; j++) {
			if (day[j] != null) {
				System.out.print("     " + day[j]);
			}
		}
		System.out.println("");
		//проходим по листу допустимого времени (с 8 - 22)
		for (Integer time : allTimeAvailable) {
			System.out.println();
			System.out.print(time);
			//проходим по массиму дней недели
			for (int j = 0; j < day.length; j++) {
				// бегаем по мапу
				for (Map.Entry<Integer, DayEnum> entry : dayAndTime.entrySet()) {
					//сравниваем, чтобы ключ совпал с временем и значения совпало с днем недели
					if(day[j].equals(entry.getValue()) && time.equals(entry.getKey())  ) {
						//отображаем только первые две буквы названия активности
						System.out.print(getTwoSymbolsInActivityName(action.getItemById(activityId.toString()).getActivityName()) + "  ");//getTwoSymbolsInActivityName - метод, который возвращает первые две буквы от переданной ему строки;action.getItemById(activityId.toString()) - возвращаем Activity по id; action.getItemById(activityId.toString()).getActivityName()) - возвращаем имя активности
					}else {
						System.out.print("    ");
					}
				}
			}


		}

	}

	private void showTimetableActivity() {
		System.out.println("1. Do you want to see activity timetable on chosen month?");
//		System.out.println("2. Do you want to see activity timetable for the whole period?");
//		System.out.println("3. Show activity for the entire period when she met in the schedule");
		switch (MainMenu.getAnswer()) {
		case 1:
			// выбираем расписание в зависимости от выбраного месяца
			showActivityOnChosenMonth();
			break;
		case 2:
			// TO DO
			break;
		case 3:
			// TO DO
			break;
		}
	}

	private void showTimetable() {

		System.out.println("1. Do you want to see timetable on selected activity? ");
		//System.out.println("2. Do you want to see all timetable ?");

		switch (MainMenu.getAnswer()) {
		case 1:
			showTimetableActivity();
			
			break;
		case 2:
			// TODO
			break;
		}
	}

	@Override
	public void createMenu() {
		//вопрос
		selectWhatToDo();
		//обработка ответа на вопрос 
		switch (MainMenu.getAnswer()) {
		case 1:
			//меню для отображения расписания
			showTimetable();
			break;
		case 2:
			createNewTimetable();
			break;
		}

	}
	@Override
	public void showAllExistingItem() {
		// TODO Auto-generated method stub
		//TODO
		
	}
	@Override
	public void deleteItem() {
		// TODO Auto-generated method stub
		
	}
}
