package tektor.minecraft.talldoors.items;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MosaicTool extends Item{

	public MosaicTool() {
		super();
		this.setMaxDamage(1460);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(TallDoorsBase.tabTallDoors);
		this.setTextureName("tallDoors:mosaicTool");
		this.setUnlocalizedName("mosaicTool");
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if (!world.isRemote) {
			if (world.getBlock(x, y, z).equals(TallDoorsBase.mosaic) || world.getBlock(x, y, z).equals(TallDoorsBase.mosaicGlass) ) {
				//stack.damageItem(1, player);
				return false;
			}
		}
		return false;
	}

}
