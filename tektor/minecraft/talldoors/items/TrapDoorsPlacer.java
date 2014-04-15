package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.entities.trapdoors.TrapDoor;
import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class TrapDoorsPlacer extends Item {

	private IIcon[] icon = new IIcon[1];

	public TrapDoorsPlacer() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("tallDoors:trapDoorsPlacer");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "trapDoorsPlacer";
		default:
			return "??";
		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Trap Door";
		default:
			return "??";
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {
			if (par1ItemStack.getItemDamage() == 0) {
				if (par7 == 0 || par7 == 1)
					return false;
				--par1ItemStack.stackSize;
				TrapDoor stand = new TrapDoor(par3World);

				switch (par7) {
				case 2: {
					stand.setOrientation(2);
					stand.setLocationAndAngles(par4, par5+0.875, par6 - 1, 0.0F, 0.0F);
					break;
				}
				case 3: {
					stand.setOrientation(0);
					stand.setLocationAndAngles(par4, par5+0.875, par6 + 1, 0.0F, 0.0F);
					break;
				}
				case 4: {
					stand.setOrientation(1);
					stand.setLocationAndAngles(par4 - 1, par5+0.875, par6, 0.0F, 0.0F);
					break;
				}
				case 5: {
					stand.setOrientation(3);
					stand.setLocationAndAngles(par4 + 1, par5+0.875, par6, 0.0F, 0.0F);
					break;
				}
				}
				par3World.spawnEntityInWorld(stand);
			}

		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
	}
	

}
