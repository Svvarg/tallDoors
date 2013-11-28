package tektor.minecraft.talldoors.items;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MosaicTool extends Item{

	public MosaicTool(int par1) {
		super(par1);
		this.setMaxDamage(50);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName("tallDoors:mosaicTool");
		this.setUnlocalizedName("mosaicTool");
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if (!world.isRemote) {
			if (world.getBlockId(x, y, z) == TallDoorsBase.mosaic.blockID) {
				//stack.damageItem(1, player);
				return false;
			}
		}
		return false;
	}

}
