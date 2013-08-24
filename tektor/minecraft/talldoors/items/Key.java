package tektor.minecraft.talldoors.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Key extends Item{
private Icon[] icon = new Icon[1];

public Key(int par1) {
	super(par1);
	this.setMaxDamage(0);
	this.setHasSubtypes(true);
	this.setMaxStackSize(1);
	this.setCreativeTab(CreativeTabs.tabDecorations);
}

@Override
@SideOnly(Side.CLIENT)
public Icon getIconFromDamage(int par1) {
	return icon[par1];
}

@Override
@SideOnly(Side.CLIENT)
public void registerIcons(IconRegister par1IconRegister) {
	icon[0] = par1IconRegister.registerIcon("tallDoors:ironKey");
}

@Override
public String getUnlocalizedName(ItemStack stack) {
	switch (stack.getItemDamage()) {
	case 0:
		return "ironKey";
	default:
		return "??";
	}

}

@Override
public String getItemDisplayName(ItemStack par1ItemStack) {
	switch (par1ItemStack.getItemDamage()) {
	case 0:
		return "Iron Key";
	default:
		return "??";
	}
}

@SideOnly(Side.CLIENT)
@Override
public void getSubItems(int par1, CreativeTabs tab, List subItems) {

	subItems.add(new ItemStack(this, 1, 0));
}

public void setKeyCode(ItemStack stack, int code)
{
	if(stack.stackTagCompound == null)
	{
		stack.stackTagCompound = new NBTTagCompound();
	}
	stack.stackTagCompound.setInteger("code", code);
}

public int getKeyCode(ItemStack stack)
{
	if(stack.stackTagCompound != null) return stack.stackTagCompound.getInteger("code");
	else return -1;
}

}