package tektor.minecraft.talldoors.entities.drawbridge;

import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.items.Connector;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class DrawbridgeBase extends Entity {

	public double rotation;
	public double lon = 7;
	public double width2 = 4;
	public double height2 = 0.125;
	public int orientation;
	boolean up, active;
	public DrawbridgeMachine machine;
	private double mX, mY, mZ;

	public DrawbridgeBase(World par1World) {
		super(par1World);
		rotation = 0;
		this.setSize(7f, 0.125f);
		this.ignoreFrustumCheck = true;
		up = false;
		active = false;
		mX = 0;
		mY = 0;
		mZ = 0;
	}

	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			rotation = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(29));
			mX = this.dataWatcher.getWatchableObjectInt(25);
			mY = this.dataWatcher.getWatchableObjectInt(26);
			mZ = this.dataWatcher.getWatchableObjectInt(27);
		}
		if (!this.worldObj.isRemote) {
			if (up && active) {
				if (rotation < 90) {
					rotation = rotation + 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					active = false;
				}
			} else if (!up && active) {
				if (rotation > 0) {
					rotation = rotation - 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					active = false;
				}
			}
		}
		if(machine == null)
		{
			List<DrawbridgeMachine> list = (List<DrawbridgeMachine>) worldObj
					.getEntitiesWithinAABB(DrawbridgeMachine.class, boundingBox
							.getBoundingBox(mX - 1, mY - 1, mZ - 1, mX + 1, mY + 1,
									mZ + 1));
			machine = list.isEmpty() ? null : list.get(0);
		}
		setBoundsAt(posX, posY, posZ);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);
		this.dataWatcher.addObject(29, "" + 0);
		this.dataWatcher.addObject(25, 0);
		this.dataWatcher.addObject(26, 0);
		this.dataWatcher.addObject(27, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		height2 = nbt.getDouble("height");
		rotation = nbt.getDouble("rotation");
		this.dataWatcher.updateObject(29, "" + rotation);
		width2 = nbt.getDouble("width");
		lon = nbt.getDouble("lon");
		this.setOrientation(nbt.getInteger("orientation"));
		List<DrawbridgeMachine> list = (List<DrawbridgeMachine>) worldObj.getEntitiesWithinAABB(
				DrawbridgeMachine.class,
				boundingBox.getBoundingBox(nbt.getDouble("mX") - 1,
						nbt.getDouble("mY") - 1, nbt.getDouble("mZ") - 1,
						nbt.getDouble("mx") + 1, nbt.getDouble("mY") + 1,
						nbt.getDouble("mZ") + 1));
		machine = list.isEmpty() ? null : list.get(0);
		this.mX = nbt.getDouble("mX");
		this.mY = nbt.getDouble("mY");
		this.mZ = nbt.getDouble("mZ");
		this.dataWatcher.updateObject(25,(int) mX);
		this.dataWatcher.updateObject(26,(int) mY);
		this.dataWatcher.updateObject(27,(int) mZ);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setDouble("height", height2);
		nbt.setDouble("rotation", rotation);
		nbt.setDouble("width", width2);
		nbt.setDouble("lon", lon);
		nbt.setInteger("orientation", orientation);
		if (machine != null) {
			nbt.setDouble("mX", machine.posX);
			nbt.setDouble("mY", machine.posY);
			nbt.setDouble("mZ", machine.posZ);
		}
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
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		
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
		float f1 = this.height;
		if (orientation == 0) {
			this.boundingBox.setBounds(par1, par3 - this.yOffset + this.ySize,
					par5, par1 + width2, par3 - this.yOffset + this.ySize + f1,
					par5 + lon);
		} else if (orientation == 1) {
			this.boundingBox.setBounds(par1-lon +1 , par3 - this.yOffset + this.ySize,
					par5, par1 +1, par3 - this.yOffset + this.ySize + f1,
					par5+width2);
		} else if (orientation == 2) {
			this.boundingBox.setBounds(par1- width2 + 1, par3 - this.yOffset + this.ySize,
					par5- lon + 1, par1 +1 , par3 - this.yOffset + this.ySize + f1,
					par5 +1);
		} else if (orientation == 3) {
			this.boundingBox.setBounds(par1, par3 - this.yOffset + this.ySize,
					par5- width2 +1, par1 + lon, par3 - this.yOffset + this.ySize + f1,
					par5 + 1 );
		}
	}

	public void activate() {
		if (!this.up) {
			up = true;
			active = true;
		} else {
			up = false;
			active = true;
		}
		System.out.println("activated");
	}

	public int func_82329_d() {
		return 64;
	}

	public int func_82330_g() {
		return 2;
	}
	
	@Override
	public boolean func_130002_c(EntityPlayer player) {

		
		if (!this.worldObj.isRemote) {
			if( player.inventory.getCurrentItem() != null &&player.inventory.getCurrentItem().itemID == TallDoorsBase.connector.itemID)
			{
				((Connector)player.inventory.getCurrentItem().getItem()).base = this;
			}
		}
		return true;
	}

}
