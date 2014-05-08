package tektor.minecraft.talldoors.doorworkshop.gui;

import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.container.RestrictingSlot;
import tektor.minecraft.talldoors.doorworkshop.ModularDoorPlacer;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import tektor.minecraft.talldoors.doorworkshop.util.DeleteAllInventory;
import tektor.minecraft.talldoors.doorworkshop.util.PositionItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ModuleAssemblerContainer extends Container{

	protected ModuleAssemblerTileEntity ent;
	IInventory outputSlot = new InventoryCraftResult();
	InventoryPlayer inv;
	int x,y;

	public ModuleAssemblerContainer(InventoryPlayer inventoryPlayer,
			ModuleAssemblerTileEntity e) {
		ent = e;
		e.container = this;
		inv = inventoryPlayer;
		ItemStack[] slot2 = new ItemStack[0];
		addSlotToContainer(new RestrictingSlot(outputSlot, 1, 276, 154, slot2,
				null, false));
		
		bindPlayerInventory(inventoryPlayer);
		bindFieldInventory(ent);
	}
	
	private void bindFieldInventory(ModuleAssemblerTileEntity field2) {
		for(int i = 0; i < 8; i++)
		{
			for(int k = 0; k < 13; k++)
			{
				ItemStack[] slot = new ItemStack[1];
				slot[0] = new ItemStack(TallDoorsBase.doorModule,1,0);
				addSlotToContainer(new RestrictingSlot(field2,i*13+k,12+k*18,6+i*18,slot,null,false));
			}
		}
		
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
		return true;
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

	public void produce(String[][] constructionPlan, boolean left) {
		if (this.outputSlot.getStackInSlot(0) == null) {
			this.outputSlot.setInventorySlotContents(0, new ItemStack(TallDoorsBase.modularDoorPlacer,1,0));
			this.outputSlot.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
			final byte[] bytes = SerializationUtils.serialize(constructionPlan);
			this.outputSlot.getStackInSlot(0).stackTagCompound.setByteArray("constructionPlan", bytes);
			this.outputSlot.getStackInSlot(0).stackTagCompound.setBoolean("left", left);
		}
	}


}
