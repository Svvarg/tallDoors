package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.nails;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class FourNailsSquare2PartEntity extends Abstract2TextureDoorPart{

	public FourNailsSquare2PartEntity(World par1World, double posX,
			int heightPosition, double posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public FourNailsSquare2PartEntity(World world)
	{
		super(world);
	}

}
