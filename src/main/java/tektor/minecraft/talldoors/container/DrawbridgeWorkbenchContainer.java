package tektor.minecraft.talldoors.container;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemBlock;
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
                            // block.planks replaced by Blocks.fence
                            // Items.iron_ingot replaced by TallDoorsBase.luiviteIngot  
//				if (in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.planks)) {
				if (in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.fence)) {
					planksFound = planksFound + in[i].stackSize;
				} else if (in[i] != null
//						&& in[i].getItem().equals(Items.iron_ingot)) {
                                                && in[i].getItem().equals(TallDoorsBase.luiviteIngot)) {
					ironFound = ironFound + in[i].stackSize;
				}

			}
			int planksNeed = x * y * 2;
			int ironNeed = x + y;
			if (planksNeed <= planksFound && ironNeed <= ironFound) {
				ItemStack plank = new ItemStack(Blocks.fence, planksNeed);
				ItemStack iron = new ItemStack(TallDoorsBase.luiviteIngot, ironNeed);
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.fence)) {
						ItemStack ret = inv.decrStackSize(i, plank.stackSize);
						plank.stackSize = plank.stackSize - ret.stackSize;
					} else if (in[i] != null
							&& in[i].getItem().equals(TallDoorsBase.luiviteIngot)) {
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
