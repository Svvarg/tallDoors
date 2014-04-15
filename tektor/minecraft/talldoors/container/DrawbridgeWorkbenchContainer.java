package tektor.minecraft.talldoors.container;

import org.apache.commons.lang3.StringUtils;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.items.DrawbridgePlacer;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DrawbridgeWorkbenchContainer extends AbstractWorkbenchContainer {


	public DrawbridgeWorkbenchContainer(InventoryPlayer inventoryPlayer,
			DrawbridgeWorkbenchTileEntity te) {
		super(inventoryPlayer,te);
	}

	public void produce(int x, int y, int z, int s) {
		if (this.outputSlot.getStackInSlot(0) == null) {
			ItemStack[] in = inv.mainInventory;
			int planksFound = 0;
			int ironFound = 0;
			for (int i = 0; i < 32; i++) {
				if (in[i] != null && in[i].getItem().equals(Blocks.planks)) {
					planksFound = planksFound + in[i].stackSize;
				} else if (in[i] != null
						&& in[i].getItem().equals(Items.iron_ingot)) {
					ironFound = ironFound + in[i].stackSize;
				}

			}
			int planksNeed = x * y;
			int ironNeed = x + y;
			if (planksNeed <= planksFound && ironNeed <= ironFound) {
				ItemStack plank = new ItemStack(Blocks.planks, planksNeed);
				ItemStack iron = new ItemStack(Items.iron_ingot, ironNeed);
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && in[i].getItem().equals(Blocks.planks)) {
						ItemStack ret = inv.decrStackSize(i, plank.stackSize);
						plank.stackSize = plank.stackSize - ret.stackSize;
					} else if (in[i] != null
							&& in[i].getItem().equals(Items.iron_ingot)) {
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
