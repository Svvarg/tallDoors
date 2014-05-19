package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.drawbridge.EntityConnector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class Connector extends Item {

	private Icon[] icon = new Icon[1];
	private int uses;
	private int x;
	private int y;
	private int z;
	public DrawbridgeBase base;

	public Connector(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		setCreativeTab(TallDoorsBase.tabTallDoors);
		this.uses = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("tallDoors:connector");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "Connector";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Connector";
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
