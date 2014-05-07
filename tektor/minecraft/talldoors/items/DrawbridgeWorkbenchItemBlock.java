package tektor.minecraft.talldoors.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class DrawbridgeWorkbenchItemBlock extends ItemBlock{

	public DrawbridgeWorkbenchItemBlock(Block block) {
		super(block);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name = "";
		
		switch(stack.getItemDamage())
		{
		case 0: name = "drawbridge";break;
		case 1: name = "machine";break;
		}
		return name;
		
	}
	
	@Override
	public int getMetadata(int met)
	{
		return met;
	}

}