package tektor.minecraft.talldoors.entities.trapdoors;

import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class TrapDoor extends Entity {

	public int width2 = 2;
	public int depth2 = 2;
	public int orientation;
	public boolean open;
	public int timer;

	public TrapDoor(World par1World) {
		super(par1World);
		this.setSize(2f, 0.125f);
		open = false;
		timer = 0;
	}

	@Override
	protected void entityInit() {

		this.dataWatcher.addObject(30, 0); // open
		this.dataWatcher.addObject(28, 0); // orientation

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
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			open = this.dataWatcher.getWatchableObjectInt(30) == 1;
			orientation = this.dataWatcher.getWatchableObjectInt(28);
		}
		if (!this.worldObj.isRemote) {
			if (!open) {
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0, 0.2, 0));
				if(!list.isEmpty())
				{
					open = true;
					this.dataWatcher.updateObject(30, 1);
					timer = 50;
				}
			}
			if (open) {
				if (timer > 0)
					timer--;
				else {
					open = false;
					this.dataWatcher.updateObject(30, 0);
				}
			}
		}
		setBoundsAt(posX, posY, posZ);
	}

	public int func_82329_d() {
		return 16;
	}

	public int func_82330_g() {
		return 32;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		return false;
	}

	public void func_110128_b(Entity par1Entity) {
		if (par1Entity instanceof EntityPlayer) {
			this.setDead();
			EntityPlayer entityplayer = (EntityPlayer) par1Entity;

			if (entityplayer.capabilities.isCreativeMode) {
				return;
			}
		}
		this.entityDropItem(new ItemStack(TallDoorsBase.trapDoor, 1, 0), 0.0F);
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {
		if (!this.worldObj.isRemote) {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().getItem().equals(TallDoorsBase.destructionHammer)) {

				func_110128_b(player);
				player.inventory.getCurrentItem().damageItem(1, player);
				return true;
			}
		}
		return false;
	}

	private boolean checkFree() {

		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		open = nbt.getBoolean("open");
		if (open)
			this.dataWatcher.updateObject(30, 1);
		else
			this.dataWatcher.updateObject(30, 0);
		orientation = nbt.getInteger("orientation");
		this.dataWatcher.updateObject(28, orientation);

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("open", open);
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
		float f1 = this.height;
		if (!open) {
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 + depth2);
			} else if (orientation == 1) {
				this.boundingBox.setBounds(par1-depth2+1, par3 - this.yOffset
						+ this.ySize, par5, par1+1, par3 - this.yOffset
						+ this.ySize + f1, par5 + width2);

			} else if (orientation == 2) {
				this.boundingBox.setBounds(par1-width2+1, par3 - this.yOffset
						+ this.ySize, par5-depth2+1, par1+1, par3 - this.yOffset
						+ this.ySize + f1, par5+1);
		
			} else if (orientation == 3) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize, par5-width2+1, par1+depth2, par3 - this.yOffset
						+ this.ySize + f1, par5+1);
			}
		} else {
			if (orientation == 0) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize + f1 - depth2, par5, par1 + width2, par3 - this.yOffset
						+ this.ySize + f1, par5 +f1);
			} else if (orientation == 1) {
				this.boundingBox.setBounds(par1-f1+1, par3 - this.yOffset
						+ this.ySize + f1 - depth2, par5, par1+1, par3 - this.yOffset
						+ this.ySize + f1, par5 +width2);
			} else if (orientation == 2) {
				this.boundingBox.setBounds(par1-width2+1, par3 - this.yOffset
						+ this.ySize + f1 -depth2, par5-f1+1, par1+1, par3 - this.yOffset
						+ this.ySize + f1, par5+1);
			} else if (orientation == 3) {
				this.boundingBox.setBounds(par1, par3 - this.yOffset
						+ this.ySize + f1 - depth2, par5-width2+1, par1+f1, par3 - this.yOffset
						+ this.ySize + f1, par5+1);
			}
		}
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
