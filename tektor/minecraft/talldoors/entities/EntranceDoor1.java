package tektor.minecraft.talldoors.entities;

import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntranceDoor1 extends Entity {
	
	public int pos;

	public EntranceDoor1(World par1World) {
		super(par1World);
		this.setSize(0.5f, 4f);
		this.ignoreFrustumCheck = true;
		pos = 0;
	}

	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(30, 0);

	}
	
	@Override
	public AxisAlignedBB getBoundingBox() {
		return this.boundingBox;
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity) {
		return null;
	}
	
	public boolean canBeCollidedWith() {
		return true;
	}
	
	@Override
	public void onUpdate()
	{
		if(this.worldObj.isRemote)
		{
			pos = this.dataWatcher.getWatchableObjectInt(30);
		}
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		if (this.isEntityInvulnerable()) {
			return false;
		} else {
			if (!this.isDead && !this.worldObj.isRemote) {
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

		this.entityDropItem(new ItemStack(TallDoorsBase.doorPlacer, 1, 0), 0.0F);
	}
	
	public boolean func_130002_c(EntityPlayer player) {

		if(pos == 0) pos = 1;
		else pos = 0;

		this.dataWatcher.updateObject(30, pos);
		return true;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

}
