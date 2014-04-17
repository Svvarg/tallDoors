package tektor.minecraft.talldoors.doorworkshop.doorparttypes;

import java.util.ArrayList;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.*;
import tektor.minecraft.talldoors.doorworkshop.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.doorparts.PlainDoorPartEntity;

public class PlainDoorPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		
		depth = 0.05;
		baseCost = new ArrayList<ItemStack>(1);
		baseCost.add(new ItemStack(Blocks.planks,1,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,1,0));
		entityClass = PlainDoorPartEntity.class;
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, int posX, int heightPosition, int posZ, int heightSize, int orientation, String texture) {
		AbstractDoorPart part = new PlainDoorPartEntity(world, posX, heightPosition, posZ, heightSize, orientation, texture);
		return part;
	}
}
