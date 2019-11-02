package tektor.minecraft.talldoors.doorworkshop.util;

import net.minecraft.item.ItemStack;

public class PositionItemStack {
	
	public int x,y;
	public ItemStack stack;
	
	public PositionItemStack(int x, int y, ItemStack stack)
	{
		this.x = x;
		this.y = y;
		this.stack = stack;
	}

}
