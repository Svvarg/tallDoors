package tektor.minecraft.talldoors.entities.workbenches;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.KeyMakerGuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class KeyMaker extends Entity {

	public KeyMakerGuiContainer container;

	public KeyMaker(World par1World) {
		super(par1World);
		this.setSize(1, 1);
		this.ignoreFrustumCheck = true;
		this.preventEntitySpawning = true;
		this.noClip = true;
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
	public void applyEntityCollision(Entity par1Entity) {
	}

	@Override
	public float getCollisionBorderSize() {
		return 0.0F;
	}

	@Override
	protected void entityInit() {
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {

		if (player.inventory.getCurrentItem() != null
				&& player.inventory.getCurrentItem().getItem().equals(TallDoorsBase.destructionHammer)) {
			if (!this.worldObj.isRemote) {

				func_110128_b(player);
				player.inventory.getCurrentItem().damageItem(1, player);
				return true;
			}
		} else {
			player.openGui(TallDoorsBase.instance, 3, worldObj, (int) posX,
					(int) posY, (int) posZ);
			return true;
		}
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
		this.entityDropItem(new ItemStack(TallDoorsBase.keyMakerPlacer, 1, 0),
				0.0F);
	}

	public int func_82329_d() {
		return 16;
	}

	public int func_82330_g() {
		return 16;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(double par1, double par3, double par5) {
		this.posX = par1;
		this.posY = par3;
		this.posZ = par5;
		float f = this.width / 2.0F;
		float f1 = this.height;
		this.boundingBox.setBounds(par1 - f + 0.5D, par3 - this.yOffset
				+ this.ySize, par5 - f + 0.5D, par1 + f + 0.5D, par3
				- this.yOffset + this.ySize + f1, par5 + f + 0.5D);

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

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(posX + 0.5, posY + 0.5, posZ + 0.5) < 64;
	}

	public void pressKey(String chosen, boolean b) {
		this.container.pressKey(chosen,b);
		
	}

}
