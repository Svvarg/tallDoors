package tektor.minecraft.talldoors.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import tektor.minecraft.chalith.container.RestrictingSlot;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;

public class MachineWorkbenchContainer extends AbstractWorkbenchContainer {

	public MachineWorkbenchContainer(InventoryPlayer inventoryPlayer,
			DrawbridgeWorkbenchTileEntity te) {
		super(inventoryPlayer,te);
	}

	public void produce(int x, int y, int z, int s) {
		if (this.outputSlot.getStackInSlot(0) == null) {
			ItemStack[] in = inv.mainInventory;
			int planksFound = 0;
			int ironFound = 0;
			int woolFound = 0;
			for (int i = 0; i < 32; i++) {
				if (in[i] != null && in[i].itemID == Block.planks.blockID) {
					planksFound = planksFound + in[i].stackSize;
				} else if (in[i] != null
						&& in[i].itemID == Item.ingotIron.itemID) {
					ironFound = ironFound + in[i].stackSize;
				}
				else if(in[i] != null
						&& in[i].itemID == Block.cloth.blockID) {
					woolFound = woolFound + in[i].stackSize;
				}
			}
			int planksNeed = 2*(x+y+z);
			int woolNeed = 2*(s+y+z);
			int ironNeed = x + y + z;
			if (planksNeed <= planksFound && ironNeed <= ironFound && woolNeed <= woolFound) {
				ItemStack plank = new ItemStack(Block.planks, planksNeed);
				ItemStack iron = new ItemStack(Item.ingotIron, ironNeed);
				ItemStack wool = new ItemStack(Block.cloth, woolNeed);
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && in[i].itemID == Block.planks.blockID) {
						ItemStack ret = inv.decrStackSize(i, plank.stackSize);
						plank.stackSize = plank.stackSize - ret.stackSize;
					} else if (in[i] != null
							&& in[i].itemID == Item.ingotIron.itemID) {
						ItemStack ret = inv.decrStackSize(i, iron.stackSize);
						iron.stackSize = iron.stackSize - ret.stackSize;
					}else if (in[i] != null
							&& in[i].itemID == Block.cloth.blockID) {
						ItemStack ret = inv.decrStackSize(i, wool.stackSize);
						wool.stackSize = wool.stackSize - ret.stackSize;
					}

					if (plank.stackSize == 0 && iron.stackSize == 0 && wool.stackSize == 0)
						break;

				}
				this.outputSlot.setInventorySlotContents(0, new ItemStack(
						TallDoorsBase.drawbridge, 1, 1));
				this.outputSlot.getStackInSlot(0).stackTagCompound = new NBTTagCompound();
				this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger("width", x);
				this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger("depth", y);
				this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger("height", z);
				this.outputSlot.getStackInSlot(0).stackTagCompound.setInteger("spool", s);
			}
		}
	}

}
