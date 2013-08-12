package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.entities.EntityConnector;
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

public class Connector extends Item {

	private Icon[] icon = new Icon[1];
	private int uses;
	private int x;
	private int y;
	private int z;

	public Connector(int par1) {
		super(par1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
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

	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {
			if (this.uses == 1) {

				if (!par3World.getBlockMaterial(par4, par5, par6).isSolid()) {
					return false;
				} else {
					if (par7 == 0) {
						--par5;
					}

					if (par7 == 1) {
						++par5;
					}

					if (par7 == 2) {
						--par6;
					}

					if (par7 == 3) {
						++par6;
					}

					if (par7 == 4) {
						--par4;
					}

					if (par7 == 5) {
						++par4;
					}

					--par1ItemStack.stackSize;

					EntityConnector con = new EntityConnector(par3World);
					con.setPosition(x, y, z);
					con.setEnd(par4,par5,par6);
					par3World.spawnEntityInWorld(con);
				}
			} else {

				if (!par3World.getBlockMaterial(par4, par5, par6).isSolid()) {
					return false;
				} else {
					if (par7 == 0) {
						--par5;
					}

					if (par7 == 1) {
						++par5;
					}

					if (par7 == 2) {
						--par6;
					}

					if (par7 == 3) {
						++par6;
					}

					if (par7 == 4) {
						--par4;
					}

					if (par7 == 5) {
						++par4;
					}
					this.uses = 1;
					this.x = par4;
					this.y = par5;
					this.z = par6;

				}
			}

		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(int par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
	}

}
