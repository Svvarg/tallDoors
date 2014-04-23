package tektor.minecraft.talldoors.doorworkshop.gui;

import tektor.minecraft.talldoors.container.RestrictingSlot;
import tektor.minecraft.talldoors.doorworkshop.ModuleAssemblerTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ModuleAssemblerContainer extends Container{

	protected ModuleAssemblerTileEntity ent;
	IInventory outputSlot = new InventoryCraftResult();
	InventoryPlayer inv;

	public ModuleAssemblerContainer(InventoryPlayer inventoryPlayer,
			ModuleAssemblerTileEntity e) {
		ent = e;
		e.container = this;
		inv = inventoryPlayer;
		ItemStack[] slot2 = new ItemStack[0];
		addSlotToContainer(new RestrictingSlot(outputSlot, 1, 225, 133, slot2,
				null, false));
		bindPlayerInventory(inventoryPlayer);
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
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return ent.isUseableByPlayer(entityplayer);
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


}
