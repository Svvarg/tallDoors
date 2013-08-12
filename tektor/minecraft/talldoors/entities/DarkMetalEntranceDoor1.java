package tektor.minecraft.talldoors.entities;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DarkMetalEntranceDoor1 extends AbstractDoorWidth2{

	public DarkMetalEntranceDoor1(World par1World) {
		super(par1World);
		this.setSize(2f, 4f);
	}

	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 11);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 10);
		return ret;
	}

}
