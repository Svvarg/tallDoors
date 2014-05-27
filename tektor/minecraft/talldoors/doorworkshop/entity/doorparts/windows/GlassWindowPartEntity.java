package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class GlassWindowPartEntity extends Abstract2TextureDoorPart {


	public GlassWindowPartEntity(World par1World, double posX, int heightPosition,
			double posZ, int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation,
				depth);
	}

	public GlassWindowPartEntity(World par1World) {
		super(par1World);
	}

	
}
