package tektor.minecraft.talldoors.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;

public class EntranceDoor3 extends AbstractDoorWidth2 {

	public EntranceDoor3(World par1World) {
		super(par1World);
		this.setSize(2f, 6f);
	}

	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 5);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 4);
		return ret;
	}

	
}
