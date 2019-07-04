package com.sportcenter.stuff;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sportcenter.activity.ActivityEnum;
import com.sportcenter.file.ReadWriteJsonFileAbstr;
import com.sportcenter.file.ReadWriteJsonFileInterface;
import com.sportcenter.generic.HelperGenericClass;
import com.sportcenter.generic.ItemGenericInterface;
import com.sportcenter.resourse.Resource;


public class TrainerAction extends HelperGenericClass<Trainer> implements ItemGenericInterface<Trainer>{
	
	//информация по тренерам
	
	private final String fileName = "json\\trainers.json";

	public TrainerAction() {
		super();
	}

	@Override
	protected String getFileName() {
		return fileName;
	}
	
	
//	public  Collection<ReadWriteJsonFileInterface> getAllTrainerInfo() {
//		return data.values();
//	}
	
	
	public String getTrainerFIO(String id){  // метод получения ФИО (методы получения других данных по тренеру аналогичны, менятся будут только типы возвращаемые)
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Trainer trainer = (Trainer) rwfi;
			if (trainer.getIdKey().equals(id))
			{
				return trainer.getFirstName() + " " + trainer.getLastName() + " " + trainer.getPatronymic();
			}
		}
		return "";
	}
	
	
	
//	public List<Trainer> getListAllTrainer(){
//		List<Trainer> trainerList = new ArrayList<>();
//		for (ReadWriteJsonFileInterface rwfi : data.values()) {
//			Trainer trainer  = (Trainer) rwfi;
//			trainerList.add(trainer);
//		}
//		return trainerList;
//	}
	//метод возвращает список фамилий тренеров у которых training(активности, которые ведут тренера) совпадает с активностью тренера
	public List<String> getListAllTrainerNameBytraining(String training){
		List<String> trainerList = new ArrayList<>();
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Trainer trainer  = (Trainer) rwfi;
			ActivityEnum[] trainingMass = trainer.getTraining(); //массив активностей тренера
			for (ActivityEnum activity : trainingMass) {
				if(activity.toString().equals(training)) //если совпала активность тренера, а с активностью переданной в метод, то заносим фамилию тренера в список
					trainerList.add(trainer.getLastName());
			}
	
		}
		return trainerList;
	}
	// возвращает список фамилий тренеров взависимости от типа тренеровок
	public int fingTrainerIdByLastName(String lastName){
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Trainer trainer  = (Trainer) rwfi;
			if(trainer.getLastName().equals(lastName))
				return Integer.parseInt(trainer.getIdKey());
		}
		return 0;
	}

	@Override
	public void addNewItem(Trainer item) {
		while (true){
			Trainer val = (Trainer) this.data.putIfAbsent(item.getIdKey(), item);
			if (val != null) item.changeIdKey();
			else break;
		}		
		saveFile();
		
	}

	@Override
	public void removeItemById(String id) {
		Trainer trainer = (Trainer) this.data.get(id);
		if (trainer != null) {
			this.data.remove(id);
			saveFile();
		} else
			System.out.println("Error delete trainer" + id);
		
	}

	@Override
	public String getAllInfoAboutItemById(String id) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Trainer trainer  = (Trainer) rwfi;
			if (trainer.getIdKey().equals(id))
			{
				return trainer.toString();
			}
		}
		return null;
	}

	@Override
	public Trainer getItemById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
