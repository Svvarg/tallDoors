package tektor.minecraft.talldoors.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.client.TallDoorsClientProxy;
import tektor.minecraft.talldoors.entities.tileentities.MosaicGlassTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;

public class MosaicGlass extends BlockContainer {

	public MosaicGlass(int par1) {
		super(par1, Material.rock);
		setHardness(4.2F);
		setResistance(5.0F);
		setUnlocalizedName("mosaicGlass");
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new MosaicGlassTileEntity();
	}

	// And this tell it that you can see through this block, and neighbor blocks
	// should be rendered.
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return -1;
    }

//	@Override
//	public int getRenderType() {
//
//		if (TallDoorsBase.proxy instanceof TallDoorsClientProxy) {
//			return TallDoorsClientProxy.mosaicRenderType;
//		} else
//			return 0;
//	}
//
//	@Override
//	public int getRenderBlockPass() {
//		return 1;
//	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return MosaicIconRegistry.getIcon("standard");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int metadata, float what, float these,
			float are) {
		if (world.isRemote) {
			return false;
		}
		if (player.inventory.getCurrentItem() != null
				&& player.inventory.getCurrentItem().itemID == TallDoorsBase.mosaicTool.itemID) {

			player.openGui(TallDoorsBase.instance, 2, world, x, y, z);
			return true;
		}

		return false;
	}

}
