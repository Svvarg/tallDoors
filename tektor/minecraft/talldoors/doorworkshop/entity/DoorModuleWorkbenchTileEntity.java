package tektor.minecraft.talldoors.doorworkshop.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import tektor.minecraft.talldoors.doorworkshop.gui.DoorModuleWorkbenchContainer;

public class DoorModuleWorkbenchTileEntity extends TileEntity implements IInventory{

	public DoorModuleWorkbenchContainer container;

	ItemStack[] inv = new ItemStack[1];

	public DoorModuleWorkbenchTileEntity() {

	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("meta", blockMetadata);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		blockMetadata = par1.getInteger("meta");
	}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound tag = pkt.data;
		this.readFromNBT(tag);
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack stack = getStackInSlot(i);
		if (stack != null) {
			if (stack.stackSize <= j) {
				setInventorySlotContents(i, null);
			} else {
				stack = stack.splitStack(j);
				if (stack.stackSize == 0) {
					setInventorySlotContents(i, null);
				}
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack stack = getStackInSlot(i);
		if (stack != null) {
			setInventorySlotContents(i, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		inv[i] = itemstack;
	}

	@Override
	public String getInvName() {
		return "talldoors.doorWorkshop";
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 128;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	public void produce(int priority, String chosen, String type,String texture1, String texture2) {
		container.produce(priority, chosen, type,texture1,texture2);
		
	}

}
