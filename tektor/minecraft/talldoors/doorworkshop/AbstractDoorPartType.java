package tektor.minecraft.talldoors.doorworkshop;

import java.util.List;

import net.minecraft.item.ItemStack;


public abstract class AbstractDoorPartType{

	public double depth;
	public static List<ItemStack> baseCost;
	public static List<ItemStack> costPerSize;
	
	public abstract void initialize(); 
	
}
