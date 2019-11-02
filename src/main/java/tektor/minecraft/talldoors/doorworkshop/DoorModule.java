package tektor.minecraft.talldoors.doorworkshop;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class DoorModule extends Item {

	private IIcon[] icon = new IIcon[9];

	public DoorModule() {
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName("doorModule");
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer,
			List par3List, boolean par4) {
		if (stack.stackTagCompound != null) {
			par3List.add("Module: "
					+ stack.stackTagCompound.getString("chosen"));
			par3List.add("Type: "
					+ stack.stackTagCompound.getString("moduleType"));
			par3List.add("Priority: "
					+ stack.stackTagCompound.getInteger("priority"));
			par3List.add("Texture1: "
					+ stack.stackTagCompound.getString("texture1"));
			par3List.add("Texture2: "
					+ stack.stackTagCompound.getString("texture2"));

		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("tallDoors:doorModule");
		icon[1] = par1IconRegister.registerIcon("tallDoors:doorModule1");
		icon[2] = par1IconRegister.registerIcon("tallDoors:doorModule2");
		icon[3] = par1IconRegister.registerIcon("tallDoors:doorModule3");
		icon[4] = par1IconRegister.registerIcon("tallDoors:doorModule4");
		icon[5] = par1IconRegister.registerIcon("tallDoors:doorModuleS");
		icon[6] = par1IconRegister.registerIcon("tallDoors:doorModuleH");
		icon[7] = par1IconRegister.registerIcon("tallDoors:doorModuleV");
		icon[8] = par1IconRegister.registerIcon("tallDoors:doorModuleF");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int renderpass) {
		switch (renderpass) {
		case 0: {
			return icon[0];
		}
		case 1: {
			return icon[stack.stackTagCompound.getInteger("priority")];
		}
		case 2: {
			if (stack.stackTagCompound.getString("moduleType").equals("single")) {
				return icon[5];
			}
			else if (stack.stackTagCompound.getString("moduleType").equals("horiz."))
			{
				return icon[6];
			}
			else if (stack.stackTagCompound.getString("moduleType").equals("vertical"))
			{
				return icon[7];
			}
			else if (stack.stackTagCompound.getString("moduleType").equals("full"))
			{
				return icon[8];
			}
		}
		}
		return null;
	}
	
	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}
	
	@Override
	public int getRenderPasses(int meta)
	{	
		return 3;
	}

}
