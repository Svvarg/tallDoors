package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class HorHalfedGlassWindowPartEntity extends Abstract2TextureDoorPart{

	public HorHalfedGlassWindowPartEntity(World par1World, int posX,
			int heightPosition, int posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public HorHalfedGlassWindowPartEntity(World world)
	{
		super(world);
	}

}
