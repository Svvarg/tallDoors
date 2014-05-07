package tektor.minecraft.talldoors.blocks;

import java.util.List;
import java.util.Random;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class DrawbridgeWorkbench extends BlockContainer{

	private IIcon[] icon = new IIcon[5];
	public DrawbridgeWorkbench() {
		super(Material.wood);
		setHardness(4.2F);
        setResistance(5.0F);
        this.setBlockName("drawbridgeWorkplace");
		setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("talldoors:drawbridgeWorkbenchTop");
		icon[1] = par1IconRegister.registerIcon("talldoors:drawbridgeWorkbenchSide");
		icon[2] = par1IconRegister.registerIcon("talldoors:drawbridgeWorkbenchBottom");
		icon[3] = par1IconRegister.registerIcon("talldoors:machineWorkbenchTop");
		icon[4] = par1IconRegister.registerIcon("talldoors:machineWorkbenchSide");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1,
	           int par2)
	{
		if(par2 == 0)
		{
			switch(par1){
			case 0: return icon[2];
			case 1:return icon[0];
			case 2:return icon[1];
			case 3:return icon[1];
			case 4:return icon[1];
			case 5:return icon[1];
			}
		}
		else if(par2 == 1)
		{
			switch(par1){
			case 0: return icon[2];
			case 1:return icon[3];
			case 2:return icon[4];
			case 3:return icon[4];
			case 4:return icon[4];
			case 5:return icon[4];
			}
		}
		return icon[0];
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                    EntityPlayer player, int dir, float what, float these, float are) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            int metadata = world.getBlockMetadata(x, y, z);
            if (tileEntity == null || player.isSneaking()) {
                    return false;
            }
            if (metadata == 0) {
				player.openGui(TallDoorsBase.instance, 0, world, x, y, z);
			}
            else if (metadata == 1)
            {
            	player.openGui(TallDoorsBase.instance, 1, world, x, y, z);
            }
			return true;
    }
	
	@Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }

    private void dropItems(World world, int x, int y, int z){
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

                            EntityItem entityItem = new EntityItem(world,
                                            x + rx, y + ry, z + rz,
                                            new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

                            if (item.hasTagCompound()) {
                                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
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
    
    @Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new DrawbridgeWorkbenchTileEntity();
	}

}