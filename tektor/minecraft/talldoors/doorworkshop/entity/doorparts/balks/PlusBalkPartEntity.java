package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import net.minecraft.world.World;

public class PlusBalkPartEntity extends AbstractDoorPart{

	public PlusBalkPartEntity(World par1World, int posX, int heightPosition,
			int posZ, int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}

	public PlusBalkPartEntity(World world)
	{
		super(world);
	}
}
