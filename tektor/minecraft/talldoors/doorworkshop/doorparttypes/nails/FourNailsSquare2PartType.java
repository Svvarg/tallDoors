package tektor.minecraft.talldoors.doorworkshop.doorparttypes.nails;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.doorparttypes.AbstractDoorPartType;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.nails.FourNailsSquare2PartEntity;

public class FourNailsSquare2PartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		
		depth = 0.25f;
		baseCost = new ArrayList<ItemStack>(2);
		baseCost.add(new ItemStack(Blocks.planks,2,0));
		baseCost.add(new ItemStack(TallDoorsBase.luiviteIngot,3,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,1,0));
		entityClass = FourNailsSquare2PartEntity.class;
		textureCount = 2;
	}

	@Override
	public AbstractDoorPart getNewEntity(World world, double posX, int heightPosition, double posZ, int heightSize, int orientation) {
		AbstractDoorPart part = new FourNailsSquare2PartEntity(world, posX, heightPosition, posZ, heightSize, orientation,depth);
		return part;
	}
}
