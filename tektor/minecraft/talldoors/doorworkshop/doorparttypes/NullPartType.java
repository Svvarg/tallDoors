package tektor.minecraft.talldoors.doorworkshop.doorparttypes;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.NullPartEntity;

public class NullPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(1);
		baseCost.add(new ItemStack(TallDoorsBase.luiviteIngot,1,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,2,0));
		entityClass = NullPartEntity.class;
		textureCount = 0;
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, double posX,
			int heightPosition, double posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new NullPartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}

}
