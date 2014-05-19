package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.AbstractDoorPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DoubleHorizontalBalkPartEntity extends Abstract2TextureDoorPart{
	
	public DoubleHorizontalBalkPartEntity(World par1World) {
		super(par1World);
		this.ignoreFrustumCheck = true;
	}

	
	
	public DoubleHorizontalBalkPartEntity(World par1World, int posX, int heightPosition, int posZ,
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
