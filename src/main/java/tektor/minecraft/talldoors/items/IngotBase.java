package tektor.minecraft.talldoors.items;

import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class IngotBase extends Item {

	private IIcon[] icon = new IIcon[1];

	public IngotBase() {
		super();
		setMaxStackSize(64);
		setCreativeTab(TallDoorsBase.tabTallDoors);
		setHasSubtypes(false);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {
		int id = stack.getItemDamage();
		return icon[id];
	}

	@Override
	public IIcon getIconFromDamage(int par1) {
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
	public void registerIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("talldoors:luiviteIngot");
	}

}
