package tektor.minecraft.talldoors.doorworkshop;

import java.util.HashMap;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;

public class DoorPartRegistry {

	public static HashMap<String,AbstractDoorPartType> registeredParts = new HashMap<String,AbstractDoorPartType>();
	public static HashMap<String,String> texturePaths = new HashMap<String,String>();
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
		texturePaths.put("glass", "minecraft:textures/blocks/glass.png");
		texturePaths.put("planks_oak", "minecraft:textures/blocks/planks_oak.png");
		texturePaths.put("planks_spruce", "minecraft:textures/blocks/planks_spruce.png");
		texturePaths.put("planks_acacia", "minecraft:textures/blocks/planks_acacia.png");
		texturePaths.put("planks_big_oak", "minecraft:textures/blocks/planks_big_oak.png");
		texturePaths.put("planks_birch", "minecraft:textures/blocks/planks_birch.png");
		texturePaths.put("planks_jungle", "minecraft:textures/blocks/planks_jungle.png");
		
	}
	
}
