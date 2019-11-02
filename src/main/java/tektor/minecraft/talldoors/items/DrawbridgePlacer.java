package tektor.minecraft.talldoors.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeMachine;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DrawbridgePlacer extends Item {
	private IIcon[] icon = new IIcon[2];

	public DrawbridgePlacer() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(TallDoorsBase.tabTallDoors);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("tallDoors:drawbridgeBase");
		icon[1] = par1IconRegister.registerIcon("tallDoors:drawbridgeMachine");
	}

	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "drawbridgeBase";
		case 1:
			return "drawbridgeMachine";
		}
		return "??";
	}

	public String getItemDisplayName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "Drawbridge Placer";
		case 1:
			return "Drawbridge Machine";
		}
		return "??";

	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {

			if (par1ItemStack.getItemDamage() == 0) {
				int var24 = MathHelper
						.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
				DrawbridgeBase base = new DrawbridgeBase(par3World);
				base.setPosition(par4, par5 + 1, par6);
				base.setOrientation(var24);
				if (par1ItemStack.stackTagCompound != null) {
					base.setPars(
							par1ItemStack.stackTagCompound.getInteger("width"),
							par1ItemStack.stackTagCompound.getInteger("depth"));
				} else {
					base.setPars(4, 7);
				}
				par3World.spawnEntityInWorld(base);
			} else {
				int var24 = MathHelper
						.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
				DrawbridgeMachine base = new DrawbridgeMachine(par3World);
				base.setOrientation(var24);
				if (par1ItemStack.stackTagCompound != null) {
					base.setStuff(
							par1ItemStack.stackTagCompound.getInteger("width"),
							par1ItemStack.stackTagCompound.getInteger("height"),
							par1ItemStack.stackTagCompound.getInteger("depth"),
							0,
							par1ItemStack.stackTagCompound.getInteger("spool"));
				}
				else
				{
					base.setStuff(4, 1, 1, 0, 1);
				}
				base.setPosition(par4, par5 + 1, par6);
				par3World.spawnEntityInWorld(base);
			}
			--par1ItemStack.stackSize;
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}

	public void setSize(ItemStack stack, int width, int depth) {
		stack.stackTagCompound.setInteger("width", width);
		stack.stackTagCompound.setInteger("depth", depth);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.stackTagCompound != null) {
			par3List.add("Width:"
					+ par1ItemStack.stackTagCompound.getInteger("width"));
			par3List.add("Depth:"
					+ par1ItemStack.stackTagCompound.getInteger("depth"));
			if(par1ItemStack.getItemDamage() == 1)
			{
				par3List.add("Height:"
						+ par1ItemStack.stackTagCompound.getInteger("height"));
				par3List.add("Spool:"
						+ par1ItemStack.stackTagCompound.getInteger("spool"));
			}
		}
		
	}

}
