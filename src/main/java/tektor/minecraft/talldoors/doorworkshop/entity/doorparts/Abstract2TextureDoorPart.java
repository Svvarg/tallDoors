package tektor.minecraft.talldoors.doorworkshop.entity.doorparts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class Abstract2TextureDoorPart extends AbstractDoorPart{


	public String texture2;
	
	public Abstract2TextureDoorPart(World par1World, int posX,
			int heightPosition, int posZ, int heightSize, int orientation,
			float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation, depth);
	}
	
	public Abstract2TextureDoorPart(World world)
	{
		super(world);
	}
	
	public void setTexture(int which, String txt) {
		if (which == 0)
			texture1 = txt;
		else
			texture2 = txt;
		this.dataWatcher.updateObject(24, texture1);
		this.dataWatcher.updateObject(23, texture2);
	}

	public void onUpdate() {
		super.onUpdate();

		if (this.worldObj.isRemote) {
			texture2 = this.dataWatcher.getWatchableObjectString(23);
		}
		if(!this.worldObj.isRemote)
		{
			if(this.texture2 == null || this.texture2.equals(""))
			{
				this.texture2 = "horizontalBalk";
				this.dataWatcher.updateObject(23, texture2);
			}
		}
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(23, "");
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.texture2 = nbt.getString("texture2");
		this.dataWatcher.updateObject(23, texture2);
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setString("texture2", texture2);
	}
}
