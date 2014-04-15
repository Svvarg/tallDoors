package tektor.minecraft.talldoors.blocks;

import java.util.Random;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.KeyRedstoneLockTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class KeyRedstoneLock extends BlockContainer {

	public boolean active;

	private Icon[] icon = new Icon[2];

	public KeyRedstoneLock() {
		super(Material.rock);
		setHardness(4.2F);
		setResistance(5.0F);
		setUnlocalizedName("keyRedstoneLock");
		setCreativeTab(CreativeTabs.tabRedstone);
		active = false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("tallDoors:woodenLock");
		icon[1] = Block.planks.getIcon(0, 0);
	}

	public Icon getIcon(int par1, int par2) {

		switch (par1) {
		case 0:
			return icon[0];
		case 1:
			return icon[0];
		case 2:
			return icon[0];
		case 3:
			return icon[0];
		case 4:
			return icon[0];
		case 5:
			return icon[0];
		}
		return icon[0];
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new KeyRedstoneLockTileEntity();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int metadata, float what, float these,
			float are) {
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;

		}
		if (!world.isRemote) {
			KeyRedstoneLockTileEntity ent = (KeyRedstoneLockTileEntity) tileEntity;
			if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.key.itemID
					&& ent.keycode.equals("-1")) {

				if (player.inventory.getCurrentItem().stackTagCompound != null
						&& player.inventory.getCurrentItem().getItemDamage() == 1) {

					ent.keycode = player.inventory.getCurrentItem().stackTagCompound
							.getString("keyCode");
					player.addChatMessage("Locked this with the key "
							+ ent.keycode);
				}
			} else if (player.inventory.getCurrentItem() != null
					&& player.inventory.getCurrentItem().itemID == TallDoorsBase.key.itemID
					&& player.inventory.getCurrentItem().stackTagCompound != null
					&& ent.keycode
							.equals(player.inventory.getCurrentItem().stackTagCompound
									.getString("keyCode"))
					&& player.inventory.getCurrentItem().getItemDamage() == 1) {
				ent.keycode = "-1";
				player.addChatMessage("Unlocked this.");
			} else if (checkKey(player, ent)) {
				world.setBlockMetadataWithNotify(x, y, z,
						world.getBlockMetadata(x, y, z) + 8, 3);
				world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D,
						(double) z + 0.5D, "random.click", 0.3F, 0.6F);
				this.func_82536_d(world, x, y, z, 15);
				world.scheduleBlockUpdate(x, y, z, this.blockID,
						this.tickRate(world));
			}

		}
		return true;
	}

	private boolean checkKey(EntityPlayer player, KeyRedstoneLockTileEntity ent) {
		for (ItemStack stack : player.inventory.mainInventory) {

			if (stack != null
					&& stack.itemID == TallDoorsBase.key.itemID
					&& stack.stackTagCompound.getString("keyCode").equals(
							ent.keycode)) {

				return true;
			}
		}
		return false;
	}

	private void func_82536_d(World par1World, int par2, int par3, int par4,
			int par5) {
		par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4,
				this.blockID);
		par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4,
				this.blockID);
		par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1,
				this.blockID);
		par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1,
				this.blockID);
		par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4,
				this.blockID);
		par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4,
				this.blockID);

	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4,
			Random par5Random) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4,
				par1World.getBlockMetadata(par2, par3, par4) - 8, 3);
		func_82536_d(par1World, par2, par3, par4, 0);
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		return this.isProvidingWeakPower(par1IBlockAccess, par2, par3, par4,
				par5);
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		if (par1IBlockAccess.getBlockMetadata(par2, par3, par4) > 7) {
			return 15;
		} else {
			return 0;
		}
	}

	@Override
	public boolean canConnectRedstone(IBlockAccess par1IBlockAccess, int par2,
			int par3, int par4, int par5) {
		return false;
	}

	@Override
	public boolean canProvidePower() {
		return true;
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int par2, int par3,
			int par4, int par5) {
		if (this.active) {
			par1World.notifyBlocksOfNeighborChange(par2 + 1, par3, par4,
					this.blockID);
			par1World.notifyBlocksOfNeighborChange(par2 - 1, par3, par4,
					this.blockID);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4 + 1,
					this.blockID);
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4 - 1,
					this.blockID);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4,
					this.blockID);
			par1World.notifyBlocksOfNeighborChange(par2, par3 + 1, par4,
					this.blockID);
		}

		super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World par1World) {
		return 20;
	}
}
