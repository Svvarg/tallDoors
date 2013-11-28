package tektor.minecraft.talldoors.container;

import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

public class MosaicGuiContainer extends Container {
	protected MosaicTileEntity tileEntity;
	public MosaicGuiContainer(InventoryPlayer inventoryPlayer,
			MosaicTileEntity te) {
		tileEntity = te;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
		par1EntityPlayer.inventory.getCurrentItem().damageItem(1, par1EntityPlayer);
    }

}
