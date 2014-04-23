package tektor.minecraft.talldoors.doorworkshop.doorparttypes;

import java.util.ArrayList;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.doorparts.HorizontalBalkPartEntity;

public class HorizontalBalkPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(1);
		baseCost.add(new ItemStack(Blocks.planks,1,0));
		baseCost.add(new ItemStack(Items.iron_ingot,2,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,1,0));
		entityClass = HorizontalBalkPartEntity.class;
		
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, int posX,
			int heightPosition, int posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new HorizontalBalkPartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}

}
