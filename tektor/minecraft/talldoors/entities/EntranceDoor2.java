package tektor.minecraft.talldoors.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
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
