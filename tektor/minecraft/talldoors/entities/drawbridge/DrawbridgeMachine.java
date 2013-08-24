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

public class DrawbridgeMachine extends Entity {

	public int orientation; //28
	public DrawbridgeBase base;
	public boolean powered;
	private double mX, mY, mZ;
	public double width2; //23
	public double height2; //24
	public double lon; //25
	public double rotation; //26
	public double spool; //27

	public DrawbridgeMachine(World par1World) {
		super(par1World);
		this.setSize(1f, 1f);
		this.ignoreFrustumCheck = true;
		this.powered = false;
		mX = 0;
		mY = 0;
		mZ = 0;
		width2 = height2 = lon = rotation = spool = 0;
	}

	public void setStuff(double width, double height, double depth, double rot, double spoolsize)
	{
		width2 = width;
		this.dataWatcher.updateObject(23, (int)width2);
		height2 = height;
		this.dataWatcher.updateObject(24, (int)height2);
		lon = depth;
		this.dataWatcher.updateObject(25, (int)lon);
		rotation = rot;
		this.dataWatcher.updateObject(26, (int)rot);
		spool = spoolsize;
		this.dataWatcher.updateObject(27, (int)spool);
	}
	public void onUpdate() {
		if (this.worldObj.isRemote) {
			width2 = this.dataWatcher.getWatchableObjectInt(23);
			height2 = this.dataWatcher.getWatchableObjectInt(24);
			lon = this.dataWatcher.getWatchableObjectInt(25);
			rotation = this.dataWatcher.getWatchableObjectInt(26);
			spool = this.dataWatcher.getWatchableObjectInt(27);
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
							.getBoundingBox(mX - 1, mY - 1, mZ - 1, mX + 1,
									mY + 1, mZ + 1));
			base = list.isEmpty() ? null : list.get(0);
		}
		setBoundsAt(posX, posY, posZ);
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(28, 0);
		this.dataWatcher.addObject(23, 0);
		this.dataWatcher.addObject(24, 0);
		this.dataWatcher.addObject(25, 0);
		this.dataWatcher.addObject(26, 0);
		this.dataWatcher.addObject(27, 0);
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
		this.width2 = nbt.getDouble("width");
		this.dataWatcher.updateObject(23, (int)width2);
		this.height2 = nbt.getDouble("height");
		this.dataWatcher.updateObject(24, (int)height2);
		this.lon = nbt.getDouble("lon");
		this.dataWatcher.updateObject(25, (int)lon);
		this.rotation = nbt.getDouble("rotation");
		this.dataWatcher.updateObject(26, (int)rotation);
		this.spool = nbt.getDouble("spool");
		this.dataWatcher.updateObject(27, (int)spool);
		
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
		nbt.setDouble("width", width2);
		nbt.setDouble("height", height2);
		nbt.setDouble("lon", lon);
		nbt.setDouble("rotation", rotation);
		nbt.setDouble("spool", spool);
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
		
		if(this.orientation == 0)
		{
			
		}
		else if (this.orientation == 1)
		{
			
		}
		else if (this.orientation == 2)
		{
			
		}
		else if (this.orientation == 3)
		{
			
		}
	}

	@Override
	public boolean func_130002_c(EntityPlayer player) {

		if (!this.worldObj.isRemote) {
			ItemStack i = player.inventory.getCurrentItem();
			if (i != null
					&& i.itemID == TallDoorsBase.connector.itemID
					&& ((Connector) player.inventory.getCurrentItem().getItem()).base != null) {
				if ((((Connector) player.inventory.getCurrentItem().getItem()).base.posY
						+ ((Connector) player.inventory.getCurrentItem()
								.getItem()).base.lon - 1) < this.posY) {
					this.base = ((Connector) player.inventory.getCurrentItem()
							.getItem()).base;
					base.machine = this;
					base.setMachinePos(posX, posY, posZ);
					player.inventory.decrStackSize(
							player.inventory.currentItem, 1);
				} else {
					player.addChatMessage("A voice tells you: The Machine has to be placed higher.");
				}
			}
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.destructionHammer.itemID) {
				func_110128_b(player);
				player.inventory.getCurrentItem().damageItem(1, player);
				return true;
			}
		} else {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.destructionHammer.itemID) {
				player.swingItem();
			}
		}
		return true;
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
		this.entityDropItem(new ItemStack(TallDoorsBase.drawbridge, 1, 1), 0.0F);
	}
}
