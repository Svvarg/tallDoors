package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;

public class GlassWindowPartEntity extends AbstractDoorPart{

	public GlassWindowPartEntity(World par1World, int posX, int heightPosition,
			int posZ, int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public GlassWindowPartEntity(World par1World)
	{
		super(par1World);
	}

}
