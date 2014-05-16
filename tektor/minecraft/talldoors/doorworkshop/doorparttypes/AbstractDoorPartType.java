package tektor.minecraft.talldoors.doorworkshop.doorparttypes;

import java.util.LinkedList;
import java.util.List;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public abstract class AbstractDoorPartType{

	public float depth;
	public List<ItemStack> baseCost;
	public List<ItemStack> costPerSize;
	public Class<?> entityClass;
	public int textureCount;
	
	public abstract void initialize(); 
	
	public abstract AbstractDoorPart getNewEntity(World world, int posX, int heightPosition, int posZ, int heightSize, int orientation);
	
	public List<String> getCostAsString() {
		List<String> a = new LinkedList<String>();
		for(ItemStack i : baseCost)
		{
			a.add(i.getDisplayName() + " " + i.stackSize);
		}
		return a;
	}
}
