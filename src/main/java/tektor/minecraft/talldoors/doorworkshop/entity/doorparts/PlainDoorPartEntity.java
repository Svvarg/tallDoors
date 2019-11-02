package tektor.minecraft.talldoors.doorworkshop.entity.doorparts;

import net.minecraft.world.World;

public class PlainDoorPartEntity extends AbstractDoorPart{
	
	public PlainDoorPartEntity(World par1World) {
		super(par1World);
	}

	public PlainDoorPartEntity(World par1World, int posX, int heightPosition, int posZ,
			int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}

}
