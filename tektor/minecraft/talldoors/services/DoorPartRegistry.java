package tektor.minecraft.talldoors.services;

import java.util.HashMap;
import tektor.minecraft.talldoors.doorworkshop.AbstractDoorPartType;

public class DoorPartRegistry {

	public static HashMap<String,AbstractDoorPartType> registeredParts = new HashMap<String,AbstractDoorPartType>();
	public static int nextInt;
	
	public DoorPartRegistry()
	{
	}
	
	public static void registerDoorPart(String key, AbstractDoorPartType part)
	{
		registeredParts.put(key,part);	
	}
	
	public static AbstractDoorPartType getPartForIndex(String index)
	{
		return registeredParts.get(index);
	}
	
}
