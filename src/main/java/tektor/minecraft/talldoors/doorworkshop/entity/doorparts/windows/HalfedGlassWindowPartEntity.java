package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class HalfedGlassWindowPartEntity extends Abstract2TextureDoorPart {

	public HalfedGlassWindowPartEntity(World par1World, int posX, int heightPosition,
			int posZ, int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public HalfedGlassWindowPartEntity(World world)
	{
		super(world);
	}

}
