package tektor.minecraft.talldoors.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
                        //replaced Blocks.planks -> blocks.fence
                        //Blocks.iron_ingot -> TallDoorsBase.luiviteIngot         
			for (int i = 0; i < 32; i++) {
				if (in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.fence)) {
					planksFound = planksFound + in[i].stackSize;
				} else if (in[i] != null
						&& in[i].getItem().equals(TallDoorsBase.luiviteIngot)) {
					ironFound = ironFound + in[i].stackSize;
				}
				else if(in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.wool)) {
					woolFound = woolFound + in[i].stackSize;
				}
			}
			int planksNeed = 2*(x+y+z)*2;
			int woolNeed = 2*(s+y+z);
			int ironNeed = x + y + z;
			if (planksNeed <= planksFound && ironNeed <= ironFound && woolNeed <= woolFound) {
				ItemStack plank = new ItemStack(Blocks.fence, planksNeed);
				ItemStack iron = new ItemStack(TallDoorsBase.luiviteIngot, ironNeed);
				ItemStack wool = new ItemStack(Blocks.wool, woolNeed);
				for (int i = 0; i < 32; i++) {
					if (in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.fence)) {
						ItemStack ret = inv.decrStackSize(i, plank.stackSize);
						plank.stackSize = plank.stackSize - ret.stackSize;
					} else if (in[i] != null
							&& in[i].getItem().equals(TallDoorsBase.luiviteIngot)) {
						ItemStack ret = inv.decrStackSize(i, iron.stackSize);
						iron.stackSize = iron.stackSize - ret.stackSize;
					}else if (in[i] != null && (in[i].getItem() instanceof ItemBlock) && ((ItemBlock)in[i].getItem()).field_150939_a.equals(Blocks.wool)) {
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
