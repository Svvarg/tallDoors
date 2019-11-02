package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.balks;

import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class HorizontalBalkPartEntity extends Abstract2TextureDoorPart{

	public HorizontalBalkPartEntity(World par1World) {
		super(par1World);
	}

	
	
	public HorizontalBalkPartEntity(World par1World, int posX, int heightPosition, int posZ,
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
