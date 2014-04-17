package tektor.minecraft.talldoors.doorworkshop;

import java.util.ArrayList;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class PlainDoorPartType extends AbstractDoorPartType{

	@Override
	public void initialize() {
		
		depth = 0.05;
		baseCost = new ArrayList<ItemStack>(1);
		baseCost.add(new ItemStack(Blocks.planks,1,0));
		costPerSize = new ArrayList<ItemStack>(1);
		costPerSize.add(new ItemStack(Blocks.planks,1,0));
	}
}
