package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.entities.EntranceDoor1;
import tektor.minecraft.talldoors.entities.EntranceDoor2;
import tektor.minecraft.talldoors.entities.EntranceDoor3;
import tektor.minecraft.talldoors.entities.FenceGate1;
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

public class DoorPlacer extends Item {

	private Icon[] icon = new Icon[8];

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
		icon[2] = par1IconRegister.registerIcon("tallDoors:rightentranceDoor2");
		icon[3] = par1IconRegister.registerIcon("tallDoors:leftentranceDoor2");
		icon[4] = par1IconRegister.registerIcon("tallDoors:rightentranceDoor3");
		icon[5] = par1IconRegister.registerIcon("tallDoors:leftentranceDoor3");
		icon[6] = par1IconRegister.registerIcon("tallDoors:fenceGate1");
		icon[7] = par1IconRegister.registerIcon("tallDoors:fenceGate1");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "rightentranceDoor1";
		case 1:
			return "leftentranceDoor1";
		case 2:
			return "rightentranceDoor2";
		case 3:
			return "leftentranceDoor2";
		case 4:
			return "rightentranceDoor3";
		case 5:
			return "leftentranceDoor3";
		case 6:
			return "rightFenceGate1";
		case 7:
			return "leftFenceGate1";
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
		case 2:
			return "Right Entrance Door Size 5";
		case 3:
			return "Left Entrance Door Size 5";
		case 4:
			return "Right Entrance Door Size 6";
		case 5:
			return "Left Entrance Door Size 6";
		case 6:
			return "Right Fence Door";
		case 7:
			return "Left Fence Door";
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
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);
			} else if (par1ItemStack.getItemDamage() == 1) {
				EntranceDoor1 door = new EntranceDoor1(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 2) {
				EntranceDoor2 door = new EntranceDoor2(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 3) {
				EntranceDoor2 door = new EntranceDoor2(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 4) {
				EntranceDoor3 door = new EntranceDoor3(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 5) {
				EntranceDoor3 door = new EntranceDoor3(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 6) {
				FenceGate1 door = new FenceGate1(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 7) {
				FenceGate1 door = new FenceGate1(par3World);
				door.setOrientation(true, var24);
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
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
		subItems.add(new ItemStack(this, 1, 4));
		subItems.add(new ItemStack(this, 1, 5));
		subItems.add(new ItemStack(this, 1, 6));
		subItems.add(new ItemStack(this, 1, 7));
	}
}
