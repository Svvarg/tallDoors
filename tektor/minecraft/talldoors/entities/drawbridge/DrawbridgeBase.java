package tektor.minecraft.talldoors.entities.drawbridge;

import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.items.Connector;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class DrawbridgeBase extends Entity {

	public double rotation;
	public double lon = 7;
	public double width2 = 4;
	public double height2 = 0.125;
	public int orientation;
	boolean up, active;
	public DrawbridgeMachine machine;
	double mX;
	double mY;
	double mZ;

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

	public void setPars(int width3, int depth3) {
		width2 = width3;
		lon = depth3;
		this.dataWatcher.updateObject(22, (int) lon);
		this.dataWatcher.updateObject(23, (int) width2);
		this.setPosition(posX, posY, posZ);
	}

	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
			rotation = Double.parseDouble(this.dataWatcher
					.getWatchableObjectString(29));

			up = this.dataWatcher.getWatchableObjectInt(20) == 0 ? false : true;
			active = this.dataWatcher.getWatchableObjectInt(21) == 0 ? false
					: true;

			mX = this.dataWatcher.getWatchableObjectInt(25);
			mY = this.dataWatcher.getWatchableObjectInt(26);
			mZ = this.dataWatcher.getWatchableObjectInt(27);

			lon = this.dataWatcher.getWatchableObjectInt(22);
			width2 = this.dataWatcher.getWatchableObjectInt(23);
		}
		if (!this.worldObj.isRemote) {
			if (up && active) {
				if (rotation < 90) {
					rotation = rotation + 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					active = false;
					this.dataWatcher.updateObject(21, 0);
				}
			} else if (!up && active) {
				if (rotation > 0) {
					rotation = rotation - 0.4;
					this.dataWatcher.updateObject(29, "" + rotation);
				} else {
					active = false;
					this.dataWatcher.updateObject(21, 0);
				}
			}
		}
		if (machine == null) {
			List<DrawbridgeMachine> list = (List<DrawbridgeMachine>) worldObj
					.getEntitiesWithinAABB(DrawbridgeMachine.class, boundingBox
							.getBoundingBox(mX - 1, mY - 1, mZ - 1, mX + 1,
									mY + 1, mZ + 1));
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

		this.dataWatcher.addObject(20, 0);
		this.dataWatcher.addObject(21, 0);

		this.dataWatcher.addObject(22, 0);
		this.dataWatcher.addObject(23, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		height2 = nbt.getDouble("height");
		rotation = nbt.getDouble("rotation");
		this.dataWatcher.updateObject(29, "" + rotation);
		width2 = nbt.getDouble("width");
		lon = nbt.getDouble("lon");
		this.dataWatcher.updateObject(22, (int) lon);
		this.dataWatcher.updateObject(23, (int) width2);
		this.setOrientation(nbt.getInteger("orientation"));
		List<DrawbridgeMachine> list = (List<DrawbridgeMachine>) worldObj
				.getEntitiesWithinAABB(DrawbridgeMachine.class, boundingBox
						.getBoundingBox(nbt.getDouble("mX") - 1,
								nbt.getDouble("mY") - 1,
								nbt.getDouble("mZ") - 1,
								nbt.getDouble("mx") + 1,
								nbt.getDouble("mY") + 1,
								nbt.getDouble("mZ") + 1));
		machine = list.isEmpty() ? null : list.get(0);
		this.mX = nbt.getDouble("mX");
		this.mY = nbt.getDouble("mY");
		this.mZ = nbt.getDouble("mZ");
		this.dataWatcher.updateObject(25, (int) mX);
		this.dataWatcher.updateObject(26, (int) mY);
		this.dataWatcher.updateObject(27, (int) mZ);
		active = nbt.getBoolean("active");
		up = nbt.getBoolean("up");
		if (active)
			this.dataWatcher.updateObject(21, 1);
		else
			this.dataWatcher.updateObject(21, 0);
		if (up)
			this.dataWatcher.updateObject(20, 1);
		else
			this.dataWatcher.updateObject(20, 0);
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
		if (this.active == false && this.up == false) {
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 + lon);
			} else if (orientation == 1) {
				this.boundingBox.setBounds(par1 - lon + 1, par3 - this.yOffset
						+ this.ySize, par5, par1 + 1, par3 - this.yOffset
						+ this.ySize + f1, par5 + width2);
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

	public void activate() {
		if (!this.up) {
			up = true;
			active = true;
			this.dataWatcher.updateObject(21, 1);
			this.dataWatcher.updateObject(20, 1);
		} else {
			up = false;
			active = true;
			this.dataWatcher.updateObject(21, 1);
			this.dataWatcher.updateObject(20, 0);
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
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.connector.itemID) {
				((Connector) player.inventory.getCurrentItem().getItem()).base = this;
			}
		}
		return true;
	}

	public void setMachinePos(double posX, double posY, double posZ) {

		mX = posX;
		this.dataWatcher.updateObject(25, (int) mX);
		mY = posY;
		this.dataWatcher.updateObject(26, (int) mY);
		mZ = posZ;
		this.dataWatcher.updateObject(27, (int) mZ);

	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else {
			if (!this.isDead && !this.worldObj.isRemote
					&& par1DamageSource.getEntity() instanceof EntityPlayer) {
				this.setDead();
				this.setBeenAttacked();
				this.func_110128_b(par1DamageSource.getEntity());

			}

			return true;
		}
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;

			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}
		ItemStack drop = new ItemStack(TallDoorsBase.drawbridge, 1, 0);
		drop.stackTagCompound = new NBTTagCompound();
		drop.stackTagCompound.setInteger("width", (int)this.width2);
		drop.stackTagCompound.setInteger("depth", (int)this.lon);
		this.entityDropItem(drop, 0.0F);
	}

}
