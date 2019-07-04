package com.sportcenter.view;

import com.sportcenter.activity.Activity;
import com.sportcenter.activity.ActivityAction;
import com.sportcenter.activity.ActivityEnum;
import com.sportcenter.resourse.Resource;
import com.sportcenter.resourse.ResourceAction;
import com.sportcenter.stuff.TrainerAction;

//класс отвечающий за меню активности
//GeneratingMenuInterface - интерфейс для создания меню. Имеет единственый метод createMenu
//ActionsMenuInterface - интерфейс для действий, которые присутствуют во всех меню showAllExistingItem, deleteItem, selectWhatToDo
public class ActivityMenu implements GeneratingMenuInterface, ActionsMenuInterface {

	public ActivityMenu() {
		createMenu(); //метод по созданию меню
	}


	//метод по выбору активности
	private String selectActivity() {
		System.out.println("Select activity type:");
		System.out.println("1. Basketball");
		System.out.println("2. Swimming");
		System.out.println("3. Tennis");
		System.out.println("4. Volleyball");
		System.out.println("5. Yoga");
		System.out.println("6. Zumba");
		System.out.println("7. Gym");
		switch (MainMenu.getAnswer()) {
		case 1:
			return "Basketball";
		case 2:
			return "Swimming";
		case 3:
			return "Tennis";
		case 4:
			return "Volleyball";
		case 5:
			return "Yoga";
		case 6:
			return "Zumba";
		case 7:
			return "Gym";
		}
		return null;
	}
	// выбираем тип тренировки с тренером или без
	private String selectActivityWithOrNoTrainer() {
		System.out.println("Do you whant activity with trainer? ");
		System.out.println("1. Yes");
		System.out.println("2. No");
		switch (MainMenu.getAnswer()) {
		case 1:
			return "Yes";
		case 2:
			return "No";
		}
		return "No";
	}
	//метод возвращает id-тренера, в качестве аргумента получает тип тренировки
	private int selectTrainer(String training) {
		System.out.println("Select trainer ");
		TrainerAction trainerAction = new TrainerAction();
		int i = 1;
		for(String trainer : trainerAction.getListAllTrainerNameBytraining(training)) { // метод getListAllTrainerNameBytraining - возвращает список фамилий тренеров взависимости от типа тренеровок
			System.out.println(i+ " " + trainer);
			i++;
		}
		return trainerAction.fingTrainerIdByLastName(trainerAction.getListAllTrainerNameBytraining(training).get(MainMenu.getAnswer() - 1));//fingTrainerIdByLastName-возвращает id тренера по его фамилии
																																			//trainerAction.getListAllTrainerNameBytraining(training).get(MainMenu.getAnswer() - 1) - достаем фамилию тренера из списка фамилий тренеров
	}
	// возвращаем id ресурса
	private int selectResource() {
		System.out.println("Select resources ");
		ResourceAction resourceAction = new ResourceAction();
		int i = 1;
		for(Resource resource : resourceAction.getListAllResource()) { // возвращаем доступные ресурсы. Метод getListAllResource-возвращает лист всех ресурсов, что есть в json
			System.out.println(i+ " " + resource);
			i++;
		}
		return Integer.parseInt(resourceAction.getListAllResource().get(MainMenu.getAnswer() - 1).getIdKey());// из листа доступных ресурсов(resourceAction.getListAllResource())
																												//берем номер ответа - 1(лист, как и массив индексируется с 0). Получаем id объекта ресурс и приводим его к int
	}

	// выбираем продолжительность тренировки
	public double selectTrainingDuration() {

		System.out.println("Select training duration ");
		System.out.println("1. 1 hour");
		System.out.println("2. 2 hour");
		switch (MainMenu.getAnswer()) {
		case 1:
			return 1.0;
		case 2:
			return 2.0;
		}
		return 0;
	}
	//меню выбора действий
	@Override
	public void selectWhatToDo(){


	}
	//вывод всех существующих активностей
	@Override
	public void showAllExistingItem() {
		ActivityAction activityAction = new ActivityAction();
		int i = 1;
		for (Activity activity : activityAction.getListAllItem()) {//бегаем по листу активностей
			System.out.println(i +". " + activity);
			i++;
		}
	}
	//удаление активности
	@Override
	public void deleteItem() {
		System.out.println("What activities do you prefer to delete?");
		showAllExistingItem();
		ActivityAction activityAction = new ActivityAction();
		activityAction.removeItemById(activityAction.getListAllItem().get(MainMenu.getAnswer() - 1 ).getIdKey());//activityAction.getListAllItem() -лист всех возможных активностей
																												//activityAction.getListAllItem().get(MainMenu.getAnswer() - 1 ) - возвращаем объект типа активность; MainMenu.getAnswer() - 1 - так ка нумирация с 0, то от ответа надо отнять 1 
																												//activityAction.getListAllItem().get(MainMenu.getAnswer() - 1 ).getIdKey() -получаем id объекта
		
	}
	//метод по созданию меню
	@Override
	public void createMenu() {
		//меню выбора действий
		selectWhatToDo();
		//обработка ответа на вопрос выше
		switch (MainMenu.getAnswer()) {
		case 1:
			ActivityEnum typeActivity = ActivityEnum.valueOf(selectActivity()); //выбираем активность
		
			if(typeActivity != null) {//проверка на то, что мы выбрали хоть один из вариантов активности, если нет, выходим из метода
				int resourceId = selectResource(); //возвращаем id ресурса
				Boolean withTrainer = selectActivityWithOrNoTrainer().equals("Yes") ? true : false;  // конструкция заменяющая if. Если метод selectActivityWithOrNoTraine вернул Yes, тогда true, иначе false. Завела переменую для более легко читаемого кода
				ActivityAction activityAction = new ActivityAction();
				//действия зависящие с тренером или без
				if(withTrainer) {
					int trainerID = selectTrainer(typeActivity.toString());//метод возвращает id-тренера, в качестве аргумента получает тип тренировки
					Activity activity = new Activity(typeActivity, trainerID, selectTrainingDuration(), resourceId);// создаем активность с тренером .selectTrainingDuration - выбираем продолжительность тренировки
					activityAction.addNewItem(activity);//записываем активность в файл
					//Activity activity = new Activity()
				}else {
					Activity activity = new Activity(typeActivity, selectTrainingDuration(), resourceId);// создаем активность без тренером .selectTrainingDuration - выбираем продолжительность тренировки
					activityAction.addNewItem(activity);//записываем активность в файл
				}
			}
				
			break;
		case 2:
			showAllExistingItem();//выводит все существующие активности
			break;
		case 3:
			deleteItem();//удаление активности
			break;
		default:
			System.out.println("you enter incorrect number");
		}
		
	}
}
