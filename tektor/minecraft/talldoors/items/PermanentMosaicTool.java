package tektor.minecraft.talldoors.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PermanentMosaicTool extends Item {

	public PermanentMosaicTool() {
		super();
		this.setMaxDamage(50);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName("tallDoors:mosaicTool2");
		this.setUnlocalizedName("mosaicToolPermanent");
	}
	
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if (!world.isRemote) {
			if (world.getBlock(x, y, z).equals(TallDoorsBase.mosaic) || world.getBlock(x, y, z).equals(TallDoorsBase.mosaicGlass)) {
				if(stack.stackTagCompound == null)
				{
					player.openGui(TallDoorsBase.instance, 2, world, x, y, z);
				}
				else
				{
					MosaicTileEntity ent = (MosaicTileEntity) world.getTileEntity(x, y, z);
					if (ent.icon != stack.stackTagCompound.getString("chosen")) {
						ent.icon = stack.stackTagCompound.getString("chosen");
						world.markBlockForUpdate(x, y, z);
						stack.damageItem(1, player);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if (par1ItemStack.stackTagCompound != null) {
				par3List.add("Texture: "
						+ par1ItemStack.stackTagCompound.getString("chosen"));
			
		}

	}


}
