package tektor.minecraft.talldoors.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tektor.minecraft.talldoors.TallDoorsBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class StoneBase extends Block{

	private IIcon[] icon = new IIcon[1];
	
	public StoneBase() {
		super(Material.rock);
		setHardness(3.2F);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(TallDoorsBase.tabTallDoors);
		this.setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("talldoors:iconoStone");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int par1, int par2) {
		return icon[par2];
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

}
