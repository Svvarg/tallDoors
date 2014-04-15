package tektor.minecraft.talldoors.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.client.TallDoorsClientProxy;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MosaicBlock extends BlockContainer {

	public MosaicBlock() {
		super(Material.rock);
		setHardness(4.2F);
		setResistance(5.0F);
		setBlockName("mosaic");
		setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(0);
		this.setLightOpacity(1000);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new MosaicTileEntity();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return true;
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i,
			int j, int k, int l) {
		return false;
	}

	// And this tell it that you can see through this block, and neighbor blocks
	// should be rendered.
	public boolean isOpaqueCube() {
		return true;
	}

	@Override
	public int getRenderType() {

		if (TallDoorsBase.proxy instanceof TallDoorsClientProxy) {
			return TallDoorsClientProxy.mosaicRenderType;
		} else
			return 0;
	}

	@Override
	public boolean canRenderInPass(int pass) {
		// Set the static var in the client proxy
		TallDoorsClientProxy.renderPass = pass;
		// the block can render in both passes, so return true always
		return true;
	}

	@Override
	public int getRenderBlockPass() {
		return 1;
	}

	public void registerIcons(IIconRegister par1IconRegister) {
		MosaicIconRegistry.register(par1IconRegister);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
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
				&& player.inventory.getCurrentItem().getItem().equals(TallDoorsBase.mosaicTool)) {

			player.openGui(TallDoorsBase.instance, 2, world, x, y, z);
			return true;
		}

		return false;
	}

}
