package tektor.minecraft.talldoors.entities.drawbridge;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class DrawbridgeBase extends Entity{
	
	public double rotation;
	public double lon = 7;
	public double width2 = 4;
	public double height2 = 0.125;
	public int orientation;
	boolean up;

	public DrawbridgeBase(World par1World) {
		super(par1World);
		rotation = 0;
		this.setSize(2f, 0.0625f);
		this.ignoreFrustumCheck = true;
		up = true;
	}
	
	public void onUpdate()
	{
		if(this.worldObj.isRemote)
		{
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			rotation = Double.parseDouble(this.dataWatcher.getWatchableObjectString(29));
		}
		if (!this.worldObj.isRemote) {
			if (up) {
				if (rotation < 90) {
					rotation = rotation + 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					up = false;
				}
			} else {
				if (rotation > 0) {
					rotation = rotation - 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					up = true;
				}
			}
		}
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);	
		this.dataWatcher.addObject(29, "");
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		height2 = nbt.getDouble("height");
		rotation = nbt.getDouble("rotation");
		this.dataWatcher.updateObject(29, ""+ rotation);
		width2 = nbt.getDouble("width");
		lon = nbt.getDouble("lon");
		this.setOrientation(nbt.getInteger("orientation"));
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setDouble("height", height2);
		nbt.setDouble("rotation", rotation);
		nbt.setDouble("width", width2);
		nbt.setDouble("lon", lon);
		nbt.setInteger("orientation", orientation);
		
	}
	
	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return this.boundingBox;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}
	
	public void setOrientation( int var24) {
		
		orientation = var24;
		this.dataWatcher.updateObject(28, var24);

	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
	}

	@Override
	public void setPositionAndRotation2(double par1, double par3, double par5,
			float par7, float par8, int par9) {
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

}
