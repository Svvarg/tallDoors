package tektor.minecraft.talldoors.container;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class KeyMakerGuiContainer extends Container{

	protected KeyMaker ent;
	IInventory outputSlot = new InventoryCraftResult();
	InventoryPlayer inv;

	public KeyMakerGuiContainer(InventoryPlayer inventoryPlayer, KeyMaker e) {
		ent = e;
		e.container = this;
		inv = inventoryPlayer;
		ItemStack[] slot2 = new ItemStack[0];
		addSlotToContainer(new RestrictingSlot(outputSlot, 1, 78, 42, slot2, null, false));
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return ent.isUseableByPlayer(entityplayer);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
						8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks
		if (slotObject != null) {
			ItemStack stackInSlot = slotObject.getStack();
			if (stackInSlot != null) {
				stack = stackInSlot.copy();
				// merges the item into player inventory since its in the
				// tileEntity
				if (slot < 1) {
					if (!this.mergeItemStack(stackInSlot, 1, 33, true)) {
						return null;
					}
				}
				// places it into the tileEntity is possible since its in the
				// player
				// inventory
				else if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
					return null;
				}
				if (stackInSlot.stackSize == 0) {
					slotObject.putStack(null);
				} else {
					slotObject.onSlotChanged();
				}
				if (stackInSlot.stackSize == stack.stackSize) {
					return null;
				}
				slotObject.onPickupFromSlot(player, stackInSlot);
			}
		}

		return stack;
	}

	@Override
	protected boolean mergeItemStack(ItemStack par1ItemStack, int par2,
			int par3, boolean par4) {
		boolean success = false;
		for (int i = par2; i < par3; i++) {
			if (!this.getSlot(i).isItemValid(par1ItemStack)) {
				continue;
			}
			success = success
					|| super.mergeItemStack(par1ItemStack, i, i + 1, par4);
		}
		return success;

	}

	public void pressKey(String chosen, boolean b) {
		if (this.outputSlot.getStackInSlot(0) == null) {
			if (!b) {
				ItemStack[] in = inv.mainInventory;
				int ironFound = 0;
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && in[i].getItem().equals(Items.iron_ingot)) {
						ironFound = ironFound + in[i].stackSize;
					}

				}
				int ironNeed = 5;
				if (ironNeed <= ironFound) {
					ItemStack iron = new ItemStack(Items.iron_ingot, ironNeed);
					for (int i = 0; i < 32; i++) {
						if (in[i] != null
								&& in[i].getItem().equals(Items.iron_ingot)) {
							ItemStack ret = inv
									.decrStackSize(i, iron.stackSize);
							iron.stackSize = iron.stackSize - ret.stackSize;
						}
						if (iron.stackSize == 0)
							break;

					}
					this.outputSlot.setInventorySlotContents(0, new ItemStack(
							TallDoorsBase.key, 1, 0));
					this.outputSlot.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
					this.outputSlot.getStackInSlot(0).stackTagCompound
							.setString("keyCode", chosen);
				}
			}
			else
			{
				ItemStack[] in = inv.mainInventory;
				int ironFound = 0;
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && in[i].getItem().equals(Items.gold_ingot)) {
						ironFound = ironFound + in[i].stackSize;
					}

				}
				int ironNeed = 5;
				if (ironNeed <= ironFound) {
					ItemStack iron = new ItemStack(Items.gold_ingot, ironNeed);
					for (int i = 0; i < 32; i++) {
						if (in[i] != null
								&& in[i].getItem().equals(Items.gold_ingot)) {
							ItemStack ret = inv
									.decrStackSize(i, iron.stackSize);
							iron.stackSize = iron.stackSize - ret.stackSize;
						}
						if (iron.stackSize == 0)
							break;

					}
					this.outputSlot.setInventorySlotContents(0, new ItemStack(
							TallDoorsBase.key, 1, 1));
					this.outputSlot.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
					this.outputSlot.getStackInSlot(0).stackTagCompound
							.setString("keyCode", chosen);
				}
			}
		}
		
	}
	
}
