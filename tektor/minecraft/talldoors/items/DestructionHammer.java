package tektor.minecraft.talldoors.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DestructionHammer extends Item {

	public DestructionHammer() {
		super();
		this.setMaxDamage(60);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setTextureName("tallDoors:destructionHammer");
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "destructionHammer";
		default:
			return "??";
		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Destruction Hammer";
		default:
			return "??";
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
	}

	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if (!world.isRemote) {
			if (world.getBlock(x, y, z).equals(Blocks.wooden_door)
					|| world.getBlock(x, y, z).equals(Blocks.iron_door)) {
				world.func_147480_a(x, y, z, true);
				stack.damageItem(1, player);
				return true;
			}
		}
		return false;
	}

}