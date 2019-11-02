package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;

public class SimpleWindow2PartEntity extends AbstractDoorPart{

	public SimpleWindow2PartEntity(World par1World) {
		super(par1World);
		}

	public SimpleWindow2PartEntity(World par1World, int posX, int heightPosition, int posZ,
			int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
}
