package tektor.minecraft.talldoors.doorworkshop.util;

import net.minecraft.inventory.InventoryBasic;

public class DeleteAllInventory extends InventoryBasic{

	public DeleteAllInventory(String par1Str, boolean par2, int par3) {
		super(par1Str, par2, par3);
	}
	
	public void deleteAll()
	{
		for(int i = 0; i < this.getSizeInventory(); i++)
		{
			this.setInventorySlotContents(i, null);
		}
	}

}
