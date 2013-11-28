package tektor.minecraft.talldoors.entities;

import java.util.List;

import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class FakeEntity extends Entity {

	public double lon = 1;
	public double width2 = 1;
	public double height2 = 0.125;
	public int orientation;
	boolean up, active;
	public Entity master;

	public FakeEntity(World par1World) {
		super(par1World);
		up = false;
		active = false;
	}

	public void setWidthDepth(int width, int depth3) {
		width2 = width;
		this.dataWatcher.updateObject(23, (int) width2);
		lon = depth3;
		this.dataWatcher.updateObject(24, (int) lon);
		this.setPosition(posX, posY, posZ);
	}

	public void setUpActive(boolean u, boolean a) {
		up = u;
		active = a;
		if (up)
			this.dataWatcher.updateObject(20, 1);
		else
			this.dataWatcher.updateObject(20, 0);
		if (active)
			this.dataWatcher.updateObject(21, 1);
		else
			this.dataWatcher.updateObject(21, 0);
		
	}

	public void onUpdate() {
		if (this.worldObj.isRemote) {
			width2 = this.dataWatcher.getWatchableObjectInt(23);
			lon = this.dataWatcher.getWatchableObjectInt(24);
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			up = this.dataWatcher.getWatchableObjectInt(20) == 1;
			active = this.dataWatcher.getWatchableObjectInt(21) == 1;
		}

		this.setPosition(posX, posY, posZ);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(20, 0);
		this.dataWatcher.addObject(21, 0);

		this.dataWatcher.addObject(23, 0);
		this.dataWatcher.addObject(24, 0);
		this.dataWatcher.addObject(28, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		width2 = nbt.getDouble("width");
		this.dataWatcher.updateObject(23, (int) width2);
		lon = nbt.getDouble("lon");
		this.dataWatcher.updateObject(24, (int) lon);
		orientation = nbt.getInteger("orientation");
		this.dataWatcher.updateObject(28, orientation);
		up = nbt.getBoolean("up");
		if (up)
			this.dataWatcher.updateObject(20, 1);
		else
			this.dataWatcher.updateObject(20, 0);
		active = nbt.getBoolean("active");
		if (active)
			this.dataWatcher.updateObject(21, 1);
		else
			this.dataWatcher.updateObject(21, 0);

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setDouble("width", width2);
		nbt.setDouble("lon", lon);
		nbt.setInteger("orientation", orientation);
		nbt.setBoolean("up", up);
		nbt.setBoolean("active", active);

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

	public void setOrientation(int var24) {

		orientation = var24;
		this.dataWatcher.updateObject(28, var24);

	}

	@Override
	public void setPositionAndRotation2(double par1, double par3, double par5,
			float par7, float par8, int par9) {
		this.setPosition(par1, par3, par5);
		this.setRotation(par7, par8);
	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		setBoundsAt(par1, par3, par5);

	}

	public void setBoundsAt(double par1, double par3, double par5) {
		float f = this.width / 2.0F;
		double f1 = this.height2;
		if (this.active == false && this.up == false) {
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 + lon);
			} else if (orientation == 1) {
				this.boundingBox.setBB(AxisAlignedBB.getBoundingBox(par1 - lon
						+ 1, par3 - this.yOffset + this.ySize, par5, par1 + 1,
						par3 - this.yOffset + this.ySize + f1, par5 + width2));
			} else if (orientation == 2) {
				this.boundingBox.setBounds(par1 - width2 + 1, par3
						- this.yOffset + this.ySize, par5 - lon + 1, par1 + 1,
						par3 - this.yOffset + this.ySize + f1, par5 + 1);
			} else if (orientation == 3) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5 - width2 + 1, par1 + lon, par3
						- this.yOffset + this.ySize + f1, par5 + 1);
			}
		} else {
			f1 = (float) lon;
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 + 0.125f);
			} else if (orientation == 1) {
				this.boundingBox.setBounds(par1 - 0.125f + 1, par3
						- this.yOffset + this.ySize, par5, par1 + 1, par3
						- this.yOffset + this.ySize + f1, par5 + width2);
			} else if (orientation == 2) {
				this.boundingBox.setBounds(par1 - width2 + 1, par3
						- this.yOffset + this.ySize, par5 - 0.125f + 1,
						par1 + 1, par3 - this.yOffset + this.ySize + f1,
						par5 + 1);
			} else if (orientation == 3) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5 - width2 + 1, par1 + 0.125f, par3
						- this.yOffset + this.ySize + f1, par5 + 1);
			}
		}
	}

	public int func_82329_d() {
		return 64;
	}

	public int func_82330_g() {
		return 2;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
	}

	public boolean interactFirst(EntityPlayer player) {

		if(master != null)master.interactFirst(player);
		return true;

	}

}
