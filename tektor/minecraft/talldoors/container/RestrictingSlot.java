package tektor.minecraft.talldoors.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class RestrictingSlot extends Slot{

	private boolean lockable = false;
	private ItemStack[] items;
	private Container container;
	
	public RestrictingSlot(IInventory ent, int i, int j, int k,
			ItemStack[] slot2, Container object, boolean b) {
		super(ent, i, j, k);
		items = slot2;
		lockable = b;
		container = object;
	}

	@Override
	   public boolean isItemValid(ItemStack itemstack) {
	      for(ItemStack item: items)
	      {
	    	  if(item.getItem().equals(itemstack.getItem()) && item.getItemDamage() == itemstack.getItemDamage()) return true;
	      }
	      return false;
	   }
	
	@Override
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
		
    }
		
}
