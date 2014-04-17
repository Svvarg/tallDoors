package tektor.minecraft.talldoors.services;

import java.util.HashMap;

import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;

public class DoorPartRegistry {

	public static HashMap<String,AbstractDoorPartType> registeredParts = new HashMap<String,AbstractDoorPartType>();
	public static int nextInt;	
	public static boolean registerDoorPart(String key, AbstractDoorPartType part)
	{
		if(!registeredParts.keySet().contains(key))
		{
			registeredParts.put(key,part);
			return true;
		}
		else
		{
			System.err.println("The key " + key + "is already in use!");
			return false;
		}	
	}
	
	public static AbstractDoorPartType getPartForIndex(String index)
	{
		return registeredParts.get(index);
	}
	
	public static void initialize()
	{
		for(AbstractDoorPartType type : registeredParts.values())
		{
			type.initialize();
		}
	}
	
}
