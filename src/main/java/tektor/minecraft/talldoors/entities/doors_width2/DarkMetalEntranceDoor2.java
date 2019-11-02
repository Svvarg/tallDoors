package tektor.minecraft.talldoors.entities.doors_width2;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;

public class DarkMetalEntranceDoor2 extends AbstractDoorWidth2{

	public DarkMetalEntranceDoor2(World par1World) {
		super(par1World);
		this.setSize(2f, 5f);
	}

	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 13);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 12);
		return ret;
	}

}
