package tektor.minecraft.talldoors.entities.doors_width2;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;

public class EntranceDoor2 extends AbstractDoorWidth2 {

	public EntranceDoor2(World par1World) {
		super(par1World);
		this.setSize(2f, 5f);
	}

	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 3);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 2);
		return ret;
	}
}
