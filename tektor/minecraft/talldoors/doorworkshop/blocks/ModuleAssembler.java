package tektor.minecraft.talldoors.doorworkshop.blocks;

import java.util.Random;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ModuleAssembler extends BlockContainer{

	private IIcon[] icon = new IIcon[3];
	public ModuleAssembler() {
		super(Material.iron);
		setHardness(4.2F);
		setResistance(5.0F);
		this.setBlockName("moduleAssemler");
		setCreativeTab(TallDoorsBase.tabTallDoors);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int dir, float what, float these, float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		if(!world.isRemote)player.openGui(TallDoorsBase.instance, 5, world, x, y, z);

		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister
				.registerIcon("talldoors:moduleAssemblyTop");
		icon[1] = par1IconRegister
				.registerIcon("talldoors:moduleAssemblySide");
		icon[2] = par1IconRegister
				.registerIcon("talldoors:drawbridgeWorkbenchBottom");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
			switch (par1) {
			case 0:
				return icon[2];
			case 1:
				return icon[0];
			case 2:
				return icon[1];
			case 3:
				return icon[1];
			case 4:
				return icon[1];
			case 5:
				return icon[1];
			}
		return icon[0];
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new ModuleAssemblerTileEntity();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5,
			int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z
						+ rz, new ItemStack(item.getItem(), item.stackSize,
						item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound(
							(NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}
}
