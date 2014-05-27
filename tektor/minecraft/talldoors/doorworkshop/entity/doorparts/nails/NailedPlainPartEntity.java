package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.nails;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class NailedPlainPartEntity extends Abstract2TextureDoorPart{

	public NailedPlainPartEntity(World par1World, double posX,
			int heightPosition, double posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	public NailedPlainPartEntity(World world)
	{
		super(world);
	}

}
