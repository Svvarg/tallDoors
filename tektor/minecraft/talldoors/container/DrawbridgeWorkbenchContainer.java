package tektor.minecraft.talldoors.container;

import org.apache.commons.lang3.StringUtils;

import tektor.minecraft.chalith.ChalithBase;
import tektor.minecraft.chalith.container.ChalithWorkbenchContainerInner;
import tektor.minecraft.chalith.container.RestrictingSlot;
import tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.items.DrawbridgePlacer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DrawbridgeWorkbenchContainer extends Container {

	IInventory outputSlot = new InventoryCraftResult();

	protected DrawbridgeWorkbenchTileEntity tileEntity;
	InventoryPlayer inv;

	public DrawbridgeWorkbenchContainer(InventoryPlayer inventoryPlayer,
			DrawbridgeWorkbenchTileEntity te) {
		tileEntity = te;
		te.container = this;
		inv = inventoryPlayer;

		// restrict slot 3
		ItemStack[] slot3 = new ItemStack[0];
		addSlotToContainer(new RestrictingSlot(this.outputSlot, 0, 142, 56,
				slot3, null));
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
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

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {

	}

	public void produce(int x, int y) {
		if (this.outputSlot.getStackInSlot(0) == null) {
			ItemStack[] in = inv.mainInventory;
			int planksFound = 0;
			int ironFound = 0;
			for (int i = 0; i < 32; i++) {
				if (in[i] != null && in[i].itemID == Block.planks.blockID) {
					planksFound = planksFound + in[i].stackSize;
				} else if (in[i] != null
						&& in[i].itemID == Item.ingotIron.itemID) {
					ironFound = ironFound + in[i].stackSize;
				}

			}
			int planksNeed = x * y;
			int ironNeed = x + y;
			if (planksNeed <= planksFound && ironNeed <= ironFound) {
				ItemStack plank = new ItemStack(Block.planks, planksNeed);
				ItemStack iron = new ItemStack(Item.ingotIron, ironNeed);
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && in[i].itemID == Block.planks.blockID) {
						ItemStack ret = inv.decrStackSize(i, plank.stackSize);
						plank.stackSize = plank.stackSize - ret.stackSize;
					} else if (in[i] != null
							&& in[i].itemID == Item.ingotIron.itemID) {
						ItemStack ret = inv.decrStackSize(i, iron.stackSize);
						iron.stackSize = iron.stackSize - ret.stackSize;
					}
					if (plank.stackSize == 0 && iron.stackSize == 0)
						break;

				}
				this.outputSlot.setInventorySlotContents(0, new ItemStack(
						TallDoorsBase.drawbridge, 1, 0));
				this.outputSlot.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
				this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger("width", x);
				this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger("depth", y);
			}
		}
	}

}
