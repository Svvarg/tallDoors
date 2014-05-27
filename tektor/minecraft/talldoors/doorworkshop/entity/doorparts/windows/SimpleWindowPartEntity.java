package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import net.minecraft.world.World;

public class SimpleWindowPartEntity extends AbstractDoorPart{

	public SimpleWindowPartEntity(World par1World) {
		super(par1World);
		}

	public SimpleWindowPartEntity(World par1World, double posX, int heightPosition, double posZ,
			int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
}
