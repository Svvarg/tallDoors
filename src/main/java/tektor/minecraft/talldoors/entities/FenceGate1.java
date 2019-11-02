package tektor.minecraft.talldoors.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;

public class FenceGate1 extends AbstractLockable {

	public boolean left;
	public int orientation;

	public FenceGate1(World par1World) {
		super(par1World);
		this.setSize(2f, 1.5f);
		this.ignoreFrustumCheck = true;
		pos = 0;
		left = false;
	}

	@Override
	protected void entityInit() {

		this.dataWatcher.addObject(30, 0);
		this.dataWatcher.addObject(29, 0);
		this.dataWatcher.addObject(28,0);

	}

	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return null;
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	public void setOrientation(boolean setLeft, int var24) {
		left = setLeft;
		orientation = var24;
		if (left)
			this.dataWatcher.updateObject(29, 1);
		else
			this.dataWatcher.updateObject(29, 0);
		this.dataWatcher.updateObject(28, var24);
		
	}

	@Override
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			pos = this.dataWatcher.getWatchableObjectInt(30);
			left = this.dataWatcher.getWatchableObjectInt(29) == 1;
			orientation = this.dataWatcher.getWatchableObjectInt(28);
		}
		setBoundsAt(posX, posY, posZ);
	}

	public int func_82329_d() {
		return 32;
	}

	public int func_82330_g() {
		return 24;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;
			this.setDead();
			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}

		if (left)
			this.entityDropItem(new ItemStack(TallDoorsBase.doorPlacer, 1, 1),
					0.0F);
		else
			this.entityDropItem(new ItemStack(TallDoorsBase.doorPlacer, 1, 0),
					0.0F);
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		return super.interactFirst(player);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		pos = nbt.getInteger("pos");
		this.dataWatcher.updateObject(30, pos);
		left = nbt.getBoolean("left");
		if (left)
			this.dataWatcher.updateObject(29, 1);
		else
			this.dataWatcher.updateObject(29, 0);
		orientation = nbt.getInteger("orientation");
		this.dataWatcher.updateObject(28, orientation);

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("left", left);
		nbt.setInteger("pos", pos);
		nbt.setInteger("orientation", orientation);

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
			par5 = par5 + 0.5f;
			if (!left) {
				if (pos == 0) {
					this.boundingBox.setBounds(par1, par3
							- this.yOffset + this.ySize,
							par5-0.4D, par1 + f * 2D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 0.1D);
				} else {
					this.boundingBox.setBounds(par1, par3
							- this.yOffset + this.ySize,
							par5, par1 + 0.1D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f);
				}
			} else {
				if (pos == 0) {
					this.boundingBox.setBounds(par1 - 1D, par3
							- this.yOffset + this.ySize,
							par5-0.4D, par1 + f * 2D - 1D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 0.1D);
				} else {
					this.boundingBox.setBounds(par1 + 1D, par3
							- this.yOffset + this.ySize,
							par5, par1 + 0.9D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f);
				}
			}
		}
		else if(orientation == 1)
		{
			par1 = par1 - 0.5f;
			if (!left) {
				if (pos == 1) {
					this.boundingBox.setBounds(par1-1D, par3
							- this.yOffset + this.ySize,
							par5, par1 + f * 2D-1D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 0.1D);
				} else {
					this.boundingBox.setBounds(par1+0.9D, par3
							- this.yOffset + this.ySize,
							par5, par1 + 1.5D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f);
				}
			} else {
				if (pos == 1) {
					this.boundingBox.setBounds(par1 - 1D, par3
							- this.yOffset + this.ySize,
							par5+0.8f, par1 + f * 2D - 1D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 0.9D);
				} else {
					this.boundingBox.setBounds(par1 + 0.8D, par3
							- this.yOffset + this.ySize,
							par5-1D, par1 + 1.5D, par3 - this.yOffset
									+ this.ySize + f1, par5
									-1D+ 2D * f);
				}
			}
		}
		else if(orientation == 2)
		{
			par5 = par5 - 0.5f;
			if (!left) {
				if (pos == 0) {
					this.boundingBox.setBounds(par1-1D, par3
							- this.yOffset + this.ySize,
							par5+1D, par1 + f * 2D-1D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 1.5D);
				} else {
					this.boundingBox.setBounds(par1+0.9D, par3
							- this.yOffset + this.ySize,
							par5-1D, par1 + 1.0D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f-1D);
				}
			} else {
				if (pos == 0) {
					this.boundingBox.setBounds(par1, par3
							- this.yOffset + this.ySize,
							par5+1D, par1 + f * 2D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 1.5D);
				} else {
					this.boundingBox.setBounds(par1+0.2f, par3
							- this.yOffset + this.ySize,
							par5-1D, par1 +0.1D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f-1D);
				}
			}

		}
		else if(orientation == 3)
		{
			par1 = par1 + 0.5f;
			if (!left) {
				if (pos == 1) {
					this.boundingBox.setBounds(par1, par3
							- this.yOffset + this.ySize,
							par5+0.9D, par1 + f * 2D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 + 1.0D);
				} else {
					this.boundingBox.setBounds(par1-0.4D, par3
							- this.yOffset + this.ySize,
							par5-1D, par1 + 0.2D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f-1D);
				}
			} else {
				if (pos == 1) {
					this.boundingBox.setBounds(par1, par3
							- this.yOffset + this.ySize,
							par5, par1 + f * 2D, par3
									- this.yOffset
									+ this.ySize + f1,
							par5 +0.2f);
				} else {
					this.boundingBox.setBounds(par1-0.4D, par3
							- this.yOffset + this.ySize,
							par5, par1+0.2D, par3 - this.yOffset
									+ this.ySize + f1, par5
									+ 2D * f);
				}
			}
		}
	}

}
