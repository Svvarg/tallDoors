package tektor.minecraft.talldoors.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class StoneBaseItemBlock extends ItemBlock{

	public StoneBaseItemBlock(Block p_i45328_1_) {
		super(p_i45328_1_);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = "";

		switch (stack.getItemDamage()) {
		case 0:
			name = "iconoStone";
			break;
		}
		return name;

	}

	public int getMetadata(int met) {
		return met;
	}


}
