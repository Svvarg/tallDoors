package tektor.minecraft.talldoors.doorworkshop.gui;

import java.util.LinkedList;
import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.RestrictingSlot;
import tektor.minecraft.talldoors.doorworkshop.DoorPartRegistry;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorModuleWorkbenchTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DoorModuleWorkbenchContainer extends Container {

	protected DoorModuleWorkbenchTileEntity ent;
	IInventory outputSlot = new InventoryCraftResult();
	InventoryPlayer inv;

	public DoorModuleWorkbenchContainer(InventoryPlayer inventoryPlayer,
			DoorModuleWorkbenchTileEntity e) {
		ent = e;
		e.container = this;
		inv = inventoryPlayer;
		ItemStack[] slot2 = new ItemStack[0];
		addSlotToContainer(new RestrictingSlot(outputSlot, 1, 225, 133, slot2,
				null, false));
		
		bindPlayerInventory(inventoryPlayer);
		
	}

	private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 165));
		}
		for(int i = 0; i < 2; i++)
		{
			for(int k = 0; k < 6; k++)
			{
				addSlotToContainer(new Slot(inventoryPlayer, i * 6 + k + 9, -30 + i * 18, 8 + k * 18));
				addSlotToContainer(new Slot(inventoryPlayer, i * 6 + k + 21, 252 + i * 18, 8 + k * 18));
			}
		}
		for(int i = 0; i < 3; i++)
		{
			addSlotToContainer(new Slot(inventoryPlayer, i + 33, 198 + i * 18, 164));
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

	public void produce(int priority, String chosen, String type, String texture1, String texture2) {
		if (this.outputSlot.getStackInSlot(0) == null) {
			ItemStack[] in = inv.mainInventory;
			List<ItemStack> debt = new LinkedList<ItemStack>();
			for(ItemStack i : DoorPartRegistry.getPartForIndex(chosen).baseCost)
			{
				debt.add(i.copy());
			}
			int[] found = new int[debt.size()];
			for (int i = 0; i < 32; i++) {
				for (int k = 0; k < debt.size(); k++) {
					if (in[i] != null) {
						if (debt.get(k).getItem() instanceof ItemBlock) {
							if (in[i].getItem() instanceof ItemBlock
									&& ((ItemBlock) in[i].getItem()).itemID
											 == (((ItemBlock) debt.get(k)
													.getItem()).itemID)) {
								found[k] = found[k] + in[i].stackSize;
							}
						} else {
							if (in[i].getItem().equals(debt.get(k).getItem())) {
								found[k] = found[k] + in[i].stackSize;
							}
						}
					}
				}
			}
			for (int k = 0; k < debt.size(); k++) {
				if (debt.get(k).stackSize > found[k]) {
					return;
				}
			}
			for (int i = 0; i < 32; i++) {
				for (int k = 0; k < debt.size(); k++) {
					if (in[i] != null) {
						if (debt.get(k).getItem() instanceof ItemBlock) {
							if (in[i].getItem() instanceof ItemBlock
									&& ((ItemBlock) in[i].getItem()).itemID
											== (((ItemBlock) debt.get(k)
													.getItem()).itemID)) {
								debt.get(k).stackSize = debt.get(k).stackSize - inv.decrStackSize(i, debt.get(k).stackSize).stackSize;
							}
						} else {
							if (in[i].getItem().equals(debt.get(k).getItem())) {
								debt.get(k).stackSize = debt.get(k).stackSize - inv.decrStackSize(i, debt.get(k).stackSize).stackSize;
								
							}
						}
					}
				}
			}
			this.outputSlot.setInventorySlotContents(0, new ItemStack(
					TallDoorsBase.doorModule, 1, 0));
			this.outputSlot.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
			this.outputSlot.getStackInSlot(0).stackTagCompound.setString("chosen", chosen);
			this.outputSlot.getStackInSlot(0).stackTagCompound.setString("moduleType", type);
			this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger(
					"priority", priority);
			this.outputSlot.getStackInSlot(0).stackTagCompound.setString("texture1", texture1);
			this.outputSlot.getStackInSlot(0).stackTagCompound.setString("texture2", texture2);

		}
	}
}
