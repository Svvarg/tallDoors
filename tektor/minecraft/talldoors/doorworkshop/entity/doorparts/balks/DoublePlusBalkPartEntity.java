package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks;

import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;

public class DoublePlusBalkPartEntity extends AbstractDoorPart {

	public DoublePlusBalkPartEntity(World par1World, int posX,
			int heightPosition, int posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public DoublePlusBalkPartEntity(World world)
	{
		super(world);
	}

}
