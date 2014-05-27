package tektor.minecraft.talldoors.doorworkshop.entity.doorparts;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class NullPartEntity extends AbstractDoorPart{

	public NullPartEntity(World par1World) {
		super(par1World);
	}
	public NullPartEntity(World par1World, double posX, int heightPosition, double posZ,
			int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	/*
	@Override
	public void setBoundsAt(double x, double y, double z)
	{
		
	}*/
	@Override
	public AxisAlignedBB getBoundingBox() {
		return null;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return null;
	}

	@Override
	public boolean canBeCollidedWith() {
		return false;
	}

}
