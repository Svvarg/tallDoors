package tektor.minecraft.talldoors.entities;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntranceDoor1 extends AbstractDoorWidth2 {


	public EntranceDoor1(World par1World) {
		super(par1World);
		this.setSize(2f, 4f);
	}
	@Override
	public ItemStack getDrop() {
		ItemStack ret;
		if (left)
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 1);
		else
			ret = new ItemStack(TallDoorsBase.doorPlacer, 1, 0);
		return ret;
	}
	
	
}
