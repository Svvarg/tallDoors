package tektor.minecraft.talldoors.doorworkshop.doorparttypes.windows;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.GlassWindowPartEntity;

public class GlassWindowPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(3);
		baseCost.add(new ItemStack(Block.planks,2,0));
		baseCost.add(new ItemStack(Block.glass,4,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Block.planks,1,0));
		entityClass = GlassWindowPartEntity.class;
		textureCount = 2;
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, int posX,
			int heightPosition, int posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new GlassWindowPartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}
}
