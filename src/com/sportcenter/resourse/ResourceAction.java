package com.sportcenter.resourse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sportcenter.file.ReadWriteJsonFileAbstr;
import com.sportcenter.file.ReadWriteJsonFileInterface;
import com.sportcenter.generic.HelperGenericClass;
import com.sportcenter.generic.ItemGenericInterface;
import com.sportcenter.resourse.Resource.ResourceType;
import com.sportcenter.stuff.Trainer;

public class ResourceAction extends HelperGenericClass<Resource> implements ItemGenericInterface<Resource>{

	private final String fileName = "json\\resources.json";

	@Override
	protected String getFileName() {
		// TODO Auto-generated method stub
		return fileName;
	}


//	public Collection<ReadWriteJsonFileInterface> getAllResourcesInfo() {
//		return data.values();
//	}
	
	//возвращаем лист всех доступных ресурсов
	public List<Resource> getListAllResource(){
		List<Resource> resourcesName = new ArrayList<>();
		for (ReadWriteJsonFileInterface rwfi : data.values()) { //бегаем по значениям map.
			Resource resource  = (Resource) rwfi;//приводим ReadWriteJsonFileInterface к Resource
			resourcesName.add(resource);//добавляем в лист
		}
		return resourcesName;
	}
	
	
	public String getResourceByID(String id){ 
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Resource resource  = (Resource) rwfi;
			if (resource.getIdKey().equals(id))
			{
				return resource.toString();
			}
		}
		return "";
	}

	@Override
	public void addNewItem(Resource item) {
		while (true) {
			Resource val = (Resource) this.data.putIfAbsent(item.getIdKey(), item);
			if (val != null)
				item.changeIdKey();
			else
				break;
		}
		saveFile();
		
	}

	@Override
	public void removeItemById(String id) {
		Resource resource = (Resource) this.data.get(id);
		if (resource != null) {
			this.data.remove(id);
			saveFile();
		} else
			System.out.println("Error delete resource" + id);
		
	}

	@Override
	public String getAllInfoAboutItemById(String id) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Resource resource  = (Resource) rwfi;
			if (resource.getIdKey().equals(id))
			{
				return resource.toString();
			}
		}
		return null;
	}

	@Override
	public Resource getItemById(String id) {
		for (ReadWriteJsonFileInterface rwfi : data.values()) {
			Resource resource  = (Resource) rwfi;
			if (resource.getIdKey().equals(id))
			{
				return resource;
			}
		}
		
		return null;
	}
}
