package tektor.minecraft.talldoors.doorworkshop.doorparts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PlainDoorPartEntity extends AbstractDoorPart{
	
	public PlainDoorPartEntity(World par1World) {
		super(par1World);

		this.ignoreFrustumCheck = true;
	}

	public PlainDoorPartEntity(World par1World, int posX, int heightPosition, int posZ,
			int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public void onUpdate() {
		super.onUpdate();
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		
	}

}
