package tektor.minecraft.talldoors.entities;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class AbstractLockable extends Entity {

	public int pos;
	public boolean locked;
	public String keyCode = "-1";

	public AbstractLockable(World par1World) {
		super(par1World);
		locked = false;
	}

	@Override
	public boolean interactFirst(EntityPlayer player) {

		if (!this.worldObj.isRemote) {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.destructionHammer.itemID) {
				if (!this.locked) {
					func_110128_b(player);
					player.inventory.getCurrentItem().damageItem(1, player);
				} else {
					player.addChatMessage("You can't destroy locked doors");
				}
				return true;
			} else if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.key.itemID) {
				if (!locked
						&& player.inventory.getCurrentItem().stackTagCompound != null
						&& player.inventory.getCurrentItem().getItemDamage() == 1) {
					this.keyCode = player.inventory.getCurrentItem().stackTagCompound
							.getString("keyCode");
					locked = true;
					player.addChatMessage("Locked the door with the key "
							+ keyCode);
					return true;
				} else if (!keyCode.equals("-1")
						&& keyCode
								.equals(player.inventory.getCurrentItem().stackTagCompound
										.getString("keyCode"))
						&& player.inventory.getCurrentItem().getItemDamage() == 1) {
					this.keyCode = "-1";
					locked = false;
					player.addChatMessage("Unlocked this door.");
					return true;

				}

			}
			if (!locked) {
				if (pos == 0) {
					pos = 1;
					worldObj.playSoundAtEntity(this, "random.door_open", 1.0f,
							1.0f);
				}

				else {
					pos = 0;
					worldObj.playSoundAtEntity(this, "random.door_close", 1.0f,
							1.0f);
				}
			} else {
				if (checkKey(player)) {
					if (pos == 0) {
						pos = 1;
						worldObj.playSoundAtEntity(this, "random.door_open",
								1.0f, 1.0f);
					}

					else {
						pos = 0;
						worldObj.playSoundAtEntity(this, "random.door_close",
								1.0f, 1.0f);
					}
				} else {
					player.addChatMessage("This door is locked, you need the right key in your inventory.");
				}
			}

		} else {
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.destructionHammer.itemID) {
				player.swingItem();
			}
		}
		this.dataWatcher.updateObject(30, pos);
		setBoundsAt(posX, posY, posZ);

		return true;

	}

	private boolean checkKey(EntityPlayer player) {
		for (ItemStack stack : player.inventory.mainInventory) {
			if (stack != null
					&& stack.itemID == TallDoorsBase.key.itemID
					&& stack.stackTagCompound.getString("keyCode").equals(
							keyCode)) {
				return true;
			}
		}
		return false;
	}

	public abstract void func_110128_b(Entity player);

	public abstract void setBoundsAt(double posX, double posY, double posZ);

	protected void writeEntityToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("locked", locked);
		nbt.setString("keyCode", keyCode);
	}

	protected void readEntityFromNBT(NBTTagCompound nbt) {
		locked = nbt.getBoolean("locked");
		keyCode = nbt.getString("keyCode");
	}

}
