package tektor.minecraft.talldoors.entities.doors_width2;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;

public class MetalEntranceDoor3 extends AbstractDoorWidth2{

	public MetalEntranceDoor3(World par1World) {
		super(par1World);
		this.setSize(2f, 6f);
	}

	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 19);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 18);
		return ret;
	}
}
