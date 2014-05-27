package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.watchmen;

import net.minecraft.world.World;

public class SmallWatchmanWindowEntity extends WatchmanWindowEntity {

	public SmallWatchmanWindowEntity(World par1World, double posX,
			int heightPosition, double posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public SmallWatchmanWindowEntity(World world)
	{
		super(world);
	}

}
