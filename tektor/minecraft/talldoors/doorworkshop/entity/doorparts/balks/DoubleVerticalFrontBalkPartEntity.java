package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;

public class DoubleVerticalFrontBalkPartEntity extends Abstract2TextureDoorPart {

	public DoubleVerticalFrontBalkPartEntity(World par1World, int posX,
			int heightPosition, int posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	public DoubleVerticalFrontBalkPartEntity(World world)
	{
		super(world);
	}

}
