package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.entities.EntranceDoor1;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DoorPlacer extends Item{

	private Icon[] icon = new Icon[2];

	public DoorPlacer(int par1) {
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
		icon[0] = par1IconRegister.registerIcon("tallDoors:rightentranceDoor1");
		icon[1] = par1IconRegister.registerIcon("tallDoors:leftentranceDoor1");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "rightentranceDoor1";
		case 1:
			return "leftentranceDoor1";
		default:
			return "??";
		}

	}

	@Override
	public String getItemDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Right Entrance Door";
		case 1:
			return "Left Entrance Door";
		default:
			return "??";
		}
	}

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {
			--par1ItemStack.stackSize;
			int var24 = MathHelper
					.floor_double((double) (par2EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			
			if (par1ItemStack.getItemDamage() == 0) {
				EntranceDoor1 door = new EntranceDoor1(par3World);
				door.setOrientation(false,var24);
				System.out.println(var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);
			}
			else if (par1ItemStack.getItemDamage() == 1) {
				EntranceDoor1 door = new EntranceDoor1(par3World);
				door.setOrientation(true,var24);
				System.out.println(var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);
				
			}

		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}
}
