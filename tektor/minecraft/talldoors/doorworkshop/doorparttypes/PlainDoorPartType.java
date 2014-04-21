package tektor.minecraft.talldoors.doorworkshop.doorparttypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.*;
import tektor.minecraft.talldoors.doorworkshop.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.doorparts.PlainDoorPartEntity;

public class PlainDoorPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(1);
		baseCost.add(new ItemStack(Blocks.planks,1,0));
		baseCost.add(new ItemStack(Items.iron_ingot,2,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,1,0));
		entityClass = PlainDoorPartEntity.class;
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, int posX, int heightPosition, int posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new PlainDoorPartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}

	@Override
	public List<String> getCostAsString() {
		List<String> a = new LinkedList();
		for(ItemStack i : baseCost)
		{
			a.add(i.getDisplayName() + " " + i.stackSize);
		}
		return a;
	}
}
