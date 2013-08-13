package tektor.minecraft.talldoors.items;

import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.drawbridge.DrawbridgeBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DrawbridgeBasePlacer extends Item {

	public DrawbridgeBasePlacer(int par1) {
		super(par1);
		this.func_111206_d("talldoors:drawbridge");
		this.setMaxDamage(0);
		this.setHasSubtypes(false);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public String getUnlocalizedName(ItemStack stack) {
		return "drawbridgebase";
	}

	public String getItemDisplayName(ItemStack par1ItemStack) {
		return "Drawbridge Placer";
	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {

			int var24 = MathHelper
					.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

			DrawbridgeBase base = new DrawbridgeBase(par3World);
			base.setOrientation(var24);
			base.setPosition(par4, par5 + 1, par6);

			par3World.spawnEntityInWorld(base);

			--par1ItemStack.stackSize;
		}
		return true;
	}

}
