package tektor.minecraft.talldoors.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class OreBaseItemBlock extends ItemBlock {

	public OreBaseItemBlock(int p_i45328_1_) {
		super(p_i45328_1_);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = "";

		switch (stack.getItemDamage()) {
		case 0:
			name = "luiviteOre";
			break;
		}
		return name;

	}

	public int getMetadata(int met) {
		return met;
	}

}
