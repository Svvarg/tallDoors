package tektor.minecraft.talldoors.entities.drawbridge;

import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.items.Connector;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class DrawbridgeMachine extends Entity {

	public int orientation;
	public DrawbridgeBase base;
	public boolean powered;
	private double mX, mY, mZ;

	public DrawbridgeMachine(World par1World) {
		super(par1World);
		this.setSize(1f, 1f);
		this.ignoreFrustumCheck = true;
		this.powered = false;
		mX = 0;
		mY = 0;
		mZ = 0;
	}

	public void onUpdate() {
		if (this.worldObj.isRemote) {
			orientation = this.dataWatcher.getWatchableObjectInt(28);
		} else {
			if (worldObj.getBlockPowerInput((int) posX, (int) posY, (int) posZ) > 0
					&& powered == false) {
				if (base != null) {
					base.activate();
				}
				powered = true;
			} else if (worldObj.getBlockPowerInput((int) posX, (int) posY,
					(int) posZ) == 0) {
				powered = false;
			}
		}

		if (base == null) {
			List<DrawbridgeBase> list = (List<DrawbridgeBase>) worldObj
					.getEntitiesWithinAABB(DrawbridgeBase.class, boundingBox
							.getBoundingBox(mX - 1, mY - 1, mZ - 1, mX +1, mY + 1,
									mZ + 1));
			base = list.isEmpty() ? null : list.get(0);
		}
		setBoundsAt(posX, posY, posZ);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt) {
		this.setOrientation(nbt.getInteger("orientation"));
		this.powered = nbt.getBoolean("power");
		List<DrawbridgeBase> list = (List<DrawbridgeBase>) worldObj
				.getEntitiesWithinAABB(DrawbridgeBase.class, AxisAlignedBB
						.getBoundingBox(nbt.getDouble("mX") - 1,
								nbt.getDouble("mY") - 1,
								nbt.getDouble("mZ") - 1,
								nbt.getDouble("mx") + 1,
								nbt.getDouble("mY") + 1,
								nbt.getDouble("mZ") + 1));
		base = list.isEmpty() ? null : list.get(0);
		this.mX = nbt.getDouble("mX");
		this.mY = nbt.getDouble("mY");
		this.mZ = nbt.getDouble("mZ");
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setInteger("orientation", orientation);
		nbt.setBoolean("power", powered);
		if (base != null) {
			nbt.setDouble("mX", base.posX);
			nbt.setDouble("mY", base.posY);
			nbt.setDouble("mZ", base.posZ);
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

		this.boundingBox.setBounds(par1, par3 - this.yOffset + this.ySize,
				par5, par1 + width, par3 - this.yOffset + this.ySize + f1, par5
						+ width);
	}

	@Override
	public boolean func_130002_c(EntityPlayer player) {

		if (!this.worldObj.isRemote) {
			ItemStack i = player.inventory.getCurrentItem();
			if (i != null && i.itemID == TallDoorsBase.connector.itemID
					&& ((Connector) player.inventory.getCurrentItem().getItem()).base != null) {
				this.base = ((Connector) player.inventory.getCurrentItem()
						.getItem()).base;
				base.machine = this;
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
			}
		}
		return true;
	}
}
