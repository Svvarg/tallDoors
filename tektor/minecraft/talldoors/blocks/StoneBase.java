package tektor.minecraft.talldoors.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import tektor.minecraft.talldoors.TallDoorsBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class StoneBase extends Block{

	private Icon[] icon = new Icon[1];
	
	public StoneBase(int blockID6) {
		super(blockID6,Material.rock);
		setHardness(3.2F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(TallDoorsBase.tabTallDoors);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("talldoors:iconoStone");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		return icon[par2];
	}

	@Override
	public int damageDropped(int par1) {
		return par1;
	}

}
