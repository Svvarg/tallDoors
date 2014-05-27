package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class GlassWindow2PartEntity extends Abstract2TextureDoorPart{


	public GlassWindow2PartEntity(World par1World, double posX,
			int heightPosition, double posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public GlassWindow2PartEntity(World world)
	{
		super(world);
	}

}
