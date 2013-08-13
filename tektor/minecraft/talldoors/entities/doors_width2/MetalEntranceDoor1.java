package tektor.minecraft.talldoors.entities.doors_width2;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MetalEntranceDoor1 extends AbstractDoorWidth2{

	public MetalEntranceDoor1(World par1World) {
		super(par1World);
		this.setSize(2f, 4f);
	}

	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 9);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 8);
		return ret;
	}

}
