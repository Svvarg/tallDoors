package tektor.minecraft.talldoors.doorworkshop.entity.doorparts.windows.watchmen;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.doorworkshop.entity.doorparts.Abstract2TextureDoorPart;

public class WatchmanWindowEntity extends Abstract2TextureDoorPart {

	public boolean watchmanWindow;

	public WatchmanWindowEntity(World par1World, int posX, int heightPosition,
			int posZ, int heightSize, int orientation, float depth) {
		super(par1World, posX, heightPosition, posZ, heightSize, orientation,
				depth);
		watchmanWindow = false;
		this.dataWatcher.updateObject(31, 0);
	}

	public WatchmanWindowEntity(World world) {
		super(world);
		watchmanWindow = false;
		this.dataWatcher.updateObject(31, 0);
	}

	public void setWatchmanWindowState(boolean state) {
		watchmanWindow = state;
		if (watchmanWindow)
			this.dataWatcher.updateObject(31, 1);
		else
			this.dataWatcher.updateObject(31, 0);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (this.worldObj.isRemote) {
			watchmanWindow = this.dataWatcher.getWatchableObjectInt(31) == 1;
		}
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(31, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.setWatchmanWindowState(nbt.getBoolean("watchmanWindow"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("watchmanWindow", watchmanWindow);
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote) {
			this.setWatchmanWindowState(!watchmanWindow);
		}
		return true;

	}

}
