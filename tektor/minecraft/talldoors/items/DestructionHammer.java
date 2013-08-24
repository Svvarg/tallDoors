package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class DestructionHammer extends Item{

	public DestructionHammer(int par1) {
		super(par1);
		this.setMaxDamage(60);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.func_111206_d("tallDoors:destructionHammer");
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
	public String getItemDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Destruction Hammer";
		default:
			return "??";
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
	}

}