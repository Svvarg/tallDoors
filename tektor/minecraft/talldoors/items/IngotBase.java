package tektor.minecraft.talldoors.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import tektor.minecraft.talldoors.TallDoorsBase;

public class IngotBase extends Item {

	private Icon[] icon = new Icon[1];

	public IngotBase(int itemID10) {
		super(itemID10);
		setMaxStackSize(64);
		setCreativeTab(TallDoorsBase.tabTallDoors);
		setHasSubtypes(false);
	}

	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		int id = stack.getItemDamage();
		return icon[id];
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "luiviteIngot";
		default:
			return "??";
		}

	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("talldoors:luiviteIngot");
	}

}
