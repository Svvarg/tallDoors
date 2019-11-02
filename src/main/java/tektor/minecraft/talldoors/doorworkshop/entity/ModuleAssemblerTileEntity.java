package tektor.minecraft.talldoors.doorworkshop.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.gui.ModuleAssemblerContainer;
import tektor.minecraft.talldoors.doorworkshop.util.PositionItemStack;

public class ModuleAssemblerTileEntity extends TileEntity implements IInventory {
	public ModuleAssemblerContainer container;

	ItemStack[] inv = new ItemStack[104];
	List<PositionItemStack> stacks;
	int a, b;

	public ModuleAssemblerTileEntity() {
		stacks = new CopyOnWriteArrayList<PositionItemStack>();
		blockMetadata = 0;
		this.a = 0;
		this.b = 0;
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
		
	}

	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setInteger("meta", blockMetadata);
		par1.setInteger("a", a);
		par1.setInteger("b", b);

//		NBTTagList tagList0 = new NBTTagList();
//		for (int i = 0; i < this.stacks.size(); ++i) {
//			if (this.stacks.get(i) != null) {
//				NBTTagCompound ntc3 = new NBTTagCompound();
//				ntc3.setInteger("x", stacks.get(i).x);
//				ntc3.setInteger("y", stacks.get(i).y);
//				stacks.get(i).stack.writeToNBT(ntc3);
//				tagList0.appendTag(ntc3);
//			}
//		}
//		par1.setTag("stacks", tagList0);
//
//		NBTTagList tagList = new NBTTagList();
//		for (int i = 0; i < this.inv.length; ++i) {
//			if (this.inv[i] != null) {
//				NBTTagCompound ntc3 = new NBTTagCompound();
//				ntc3.setByte("slot", (byte) i);
//				this.inv[i].writeToNBT(ntc3);
//				tagList.appendTag(ntc3);
//			}
//		}
//		par1.setTag("items", tagList);

	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		blockMetadata = par1.getInteger("meta");
		this.a = par1.getInteger("a");
		this.b = par1.getInteger("b");

//		this.stacks.clear();
//		NBTTagList var1 = (NBTTagList) par1.getTag("stacks");
//		for (int i = 0; i < var1.tagCount(); ++i) {
//			NBTTagCompound ntc3 = (NBTTagCompound) var1.getCompoundTagAt(i);
//			this.stacks.add(new PositionItemStack(ntc3.getInteger("x"), ntc3
//					.getInteger("y"), ItemStack.loadItemStackFromNBT(ntc3)));
//
//		}
//
//		NBTTagList var2 = (NBTTagList) par1.getTag("items");
//		for (int i = 0; i < var2.tagCount(); ++i) {
//			NBTTagCompound ntc3 = (NBTTagCompound) var2.getCompoundTagAt(i);
//			byte slot = ntc3.getByte("slot");
//			if (slot >= 0 && slot < this.inv.length) {
//				this.inv[slot] = ItemStack.loadItemStackFromNBT(ntc3);
//			}
//		}
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		NBTTagCompound tag = pkt.func_148857_g();
		this.readFromNBT(tag);
	}

	@Override
	public int getSizeInventory() {
		return 104;
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
	public void setInventorySlotContents(int i, ItemStack stack) {
		inv[i] = stack;
		this.changeStacks(a + (i % 13) - 6,
				(int) (b + (Math.ceil(i / 13))) - 3, stack);
	}
	
	public void setInventorySlotContentsWithoutCheck(int i, ItemStack stack)
	{
		inv[i] = stack;
	}

	@Override
	public String getInventoryName() {
		return "talldoors.moduleAssembler";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 105;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return (itemstack.getItem().equals(TallDoorsBase.doorModule));
	}

	/**
	 * rearranges the itemstacks inside the container
	 */
	public void rearrange(int x, int y) {
		if (!this.worldObj.isRemote) {
			this.a = x;
			this.b = y;
			deleteAll();
			for (PositionItemStack pis : stacks) {
				if (pis.y > (y - 4) && pis.y < (y + 5) && pis.x > (x - 7)
						&& pis.x < (x + 7)) {
					this.setInventorySlotContentsWithoutCheck(45 + (pis.y - y) * 13
							+ (pis.x - x), pis.stack);
				}
			}
			this.container.detectAndSendChanges();
		}

	}

	public void changeStacks(int i, int d, ItemStack stack) {
		if (!this.worldObj.isRemote) {
			if (stack != null) {
				if(stack.stackSize != 0)
				{
				stacks.add(new PositionItemStack(i, d, stack.copy()));
				}
				else
				{
					for(PositionItemStack st : stacks)
					{
						if(st.x == i && st.y == d)
						{
							stacks.remove(st);
						}
					}
				}
			}
			else
			{
				for(PositionItemStack st : stacks)
				{
					if(st.x == i && st.y == d)
					{
						stacks.remove(st);
					}
				}
			}
		}

	}

	public void deleteAll() {
		for (int i = 0; i < this.getSizeInventory(); i++) {
			this.setInventorySlotContentsWithoutCheck(i, null);
		}
	}

	public void produce(int dSizeX, int dSizeY, boolean left) {
		List<PositionItemStack> prio4 = new ArrayList<PositionItemStack>();
		List<PositionItemStack> prio3 = new ArrayList<PositionItemStack>();
		List<PositionItemStack> prio2 = new ArrayList<PositionItemStack>();
		List<PositionItemStack> prio1 = new ArrayList<PositionItemStack>();

		int negX = (int) (Math.ceil(dSizeX / 2D)) - 1;
		int posX = (int) (Math.floor(dSizeX / 2D));
		int negY = (int) (Math.ceil(dSizeY / 2D) - 1);
		int posY = (int) (Math.floor(dSizeY / 2D));
		
		for(PositionItemStack st: stacks)
		{
			if (st.x < posX+1 && st.x > -negX-1 && st.y < posY+1 && st.y > -negY-1) {
				switch (st.stack.stackTagCompound.getInteger("priority")) {
				case 1: {
					prio1.add(st);
					break;
				}
				case 2: {
					prio2.add(st);
					break;
				}
				case 3: {
					prio3.add(st);
					break;
				}
				case 4:
					prio4.add(st);
					break;
				}
			}
		}
		String[][] result = new String[dSizeX][dSizeY];
		String[][] result2 = new String[dSizeX][dSizeY];
		String[][] result3 = new String[dSizeX][dSizeY];
		String[][] result4 = new String[dSizeX][dSizeY];
		for(int i = 0; i < result.length; i++)
		{
			for(int k = 0; k < result[0].length; k++)
			{
				if(result[i][k] == null)
				{
					result[i][k] = new String();
					result2[i][k] = new String();
					result3[i][k] = new String();
					result4[i][k] = new String();
				}
			}
		}
		buildForList(dSizeY, prio4, negX, negY, result, result2, result3, result4);
		buildForList(dSizeY, prio3, negX, negY, result, result2, result3, result4);
		buildForList(dSizeY, prio2, negX, negY, result, result2, result3, result4);
		buildForList(dSizeY, prio1, negX, negY, result, result2, result3, result4);
		
		for(int i = 0; i < result.length; i++)
		{
			for(int k = 0; k < result[0].length; k++)
			{
				if(result[i][k] == null)
				{
					result[i][k] = "plain";
					result2[i][k] = "plain";
					result4[i][k] = "plain";
				}
			}
		}
		this.container.produce(result,result2,result3,result4,left);
		this.stacks.clear();
		this.deleteAll();
		
	}

	private void buildForList(int dSizeY, List<PositionItemStack> prio4,
			int negX, int negY, String[][] result, String[][] result2, String[][] result3, String[][] result4) {
		for(PositionItemStack st : prio4)
		{
			String r = st.stack.stackTagCompound.getString("moduleType");
			if(r.equals("full"))
			{
				for(int i = 0; i < result.length; i++)
				{
					for(int k = 0; k < result[0].length; k++)
					{
						NBTTagCompound tag =  st.stack.stackTagCompound;
						result[i][k] = tag.getString("chosen");
						result2[i][k] = tag.getString("texture1");
						result3[i][k] = tag.getString("texture2");
						result4[i][k] = tag.getString("sideTexture");
					}
				}
				
			}
			else if(r.equals("horiz."))
			{
				int line = (dSizeY-1)-(st.y + negY);
				for(int i = 0; i < result.length; i++)
				{
					NBTTagCompound tag =  st.stack.stackTagCompound;
					result[i][line] = tag.getString("chosen");
					result2[i][line] = tag.getString("texture1");
					result3[i][line] = tag.getString("texture2");
					result4[i][line] = tag.getString("sideTexture");
				}
			}
			else if(r.equals("vertical"))
			{
				int column = st.x + negX;
				for(int i = 0; i < dSizeY; i++)
				{
					NBTTagCompound tag =  st.stack.stackTagCompound;
					result[column][i]= tag.getString("chosen");
					result2[column][i]= tag.getString("texture1");
					result3[column][i]= tag.getString("texture2");
					result4[column][i]= tag.getString("sideTexture");
				}
			}
			else if (r.equals("single"))
			{
				int column = st.x + negX;
				int line = (dSizeY-1)-(st.y + negY);
				NBTTagCompound tag =  st.stack.stackTagCompound;
				result[column][line] = tag.getString("chosen");
				result2[column][line] = tag.getString("texture1");
				result3[column][line] = tag.getString("texture2");
				result4[column][line] = tag.getString("sideTexture");
			}
			
		}
	}
}
