package tektor.minecraft.talldoors.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.client.TallDoorsClientProxy;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.services.MosaicIconRegistry;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MosaicBlock extends BlockContainer {

	public MosaicBlock(int par1) {
		super(par1, Material.rock);
		setHardness(4.2F);
		setResistance(5.0F);
		setUnlocalizedName("mosaic");
		setCreativeTab(TallDoorsBase.tabTallDoors);
		this.setLightValue(0);
		this.setLightOpacity(1000);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
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

	public void registerIcons(IconRegister par1IconRegister) {
		MosaicIconRegistry.register(par1IconRegister);
	}

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
