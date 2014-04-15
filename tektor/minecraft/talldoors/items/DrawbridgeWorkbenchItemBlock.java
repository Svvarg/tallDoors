package tektor.minecraft.talldoors.items;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class DrawbridgeWorkbenchItemBlock extends ItemBlock{

	public DrawbridgeWorkbenchItemBlock() {
		super(TallDoorsBase.drawbridgeWorkbench);
		setHasSubtypes(true);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		String name = "";
		
		switch(stack.getItemDamage())
		{
		case 0: name = "Drawbridge Workbench";break;
		case 1: name = "Machine Workbench";break;
		}
		return name;
		
	}
	
	@Override
	public int getMetadata(int met)
	{
		return met;
	}

}