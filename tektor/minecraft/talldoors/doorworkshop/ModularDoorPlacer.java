package tektor.minecraft.talldoors.doorworkshop;

import org.apache.commons.lang3.SerializationUtils;

import tektor.minecraft.talldoors.doorworkshop.entity.DoorBase;
import tektor.minecraft.talldoors.doorworkshop.util.ModuleTexturePackage;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ModularDoorPlacer extends Item {

	public ModularDoorPlacer(int itemID11) {
		super(itemID11);
		this.setMaxDamage(0);
		this.setHasSubtypes(false);
		this.setMaxStackSize(1);
		this.setUnlocalizedName("modularDoorPlacer");
		this.setTextureName("tallDoors:modularDoorPlacer");
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}
	
	
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		if (!par3World.isRemote) {

			int var24 = MathHelper
					.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

//			if (!checkFree(par3World, par4, par5 + 1, par6, 2, 4, true, var24)) {
//				par2EntityPlayer
//						.addChatMessage(new ChatComponentText(
//								"A voice whispers to you: There is not enough space for this"));
//				return false;
//			}
			DoorBase door = new DoorBase(par3World);
			boolean left = par1ItemStack.stackTagCompound.getBoolean("left");
			door.setOrientation(left, var24);
			String[][] modules = ((String[][]) SerializationUtils
					.deserialize(par1ItemStack.stackTagCompound
							.getByteArray("constructionPlan")));
			String[][] textures1 = ((String[][]) SerializationUtils
					.deserialize(par1ItemStack.stackTagCompound
							.getByteArray("texturePlan1")));
			String[][] textures2 = ((String[][]) SerializationUtils
					.deserialize(par1ItemStack.stackTagCompound
							.getByteArray("texturePlan2")));
			ModuleTexturePackage[][] result = new ModuleTexturePackage[modules.length][modules[0].length];
			for(int i = 0; i < result.length; i++)
			{
				for(int k = 0; k < result[0].length; k++)
				{
					if(result[i][k] == null)
					{
						result[i][k] = new ModuleTexturePackage();
						result[i][k].module = modules[i][k];
						result[i][k].texture1 = textures1[i][k];
						result[i][k].texture2 = textures2[i][k];
					}
				}
			}
			
			
			if (!left) {
				switch (var24) {
				case 0:
					door.setPosition(par4+result.length -1, par5 + 1, par6);break;
				case 1:
					door.setPosition(par4, par5 + 1, par6+result.length-1);break;
				case 2:
					door.setPosition(par4-result.length+1, par5 + 1, par6);break;
				case 3:
					door.setPosition(par4, par5 + 1, par6-result.length+1);break;
				}
			}
			else
			{
				door.setPosition(par4, par5 + 1, par6);
			}
			door.setConstructionPlan(result);
			door.constructFromPlan();
			
			par3World.spawnEntityInWorld(door);
			--par1ItemStack.stackSize;
		}

		return true;

	}

	private boolean checkFree(World world, int posX, int posY, int posZ,
			int width, int height, boolean left, int orientation) {
		if (left) {
			for (int i = 0; i < width; i++) {
				for (int k = 0; k < height; k++) {
					switch (orientation) {
					case 0:
						if (!world.isAirBlock(posX - i, posY + k, posZ))
							return false;
						else
							break;
					case 1:
						if (!world.isAirBlock(posX, posY + k, posZ - i))
							return false;
						else
							break;
					case 2:
						if (!world.isAirBlock(posX + i, posY + k, posZ))
							return false;
						else
							break;
					case 3:
						if (!world.isAirBlock(posX, posY + k, posZ + i))
							return false;
						else
							break;

					}

				}
			}
		} else {
			for (int i = 0; i < width; i++) {
				for (int k = 0; k < height; k++) {
					switch (orientation) {
					case 0:
						if (!world.isAirBlock(posX + i, posY + k, posZ))
							return false;
						else
							break;
					case 1:
						if (!world.isAirBlock(posX, posY + k, posZ + i))
							return false;
						else
							break;
					case 2:
						if (!world.isAirBlock(posX - i, posY + k, posZ))
							return false;
						else
							break;
					case 3:
						if (!world.isAirBlock(posX, posY + k, posZ - i))
							return false;
						else
							break;

					}

				}
			}
		}
		return true;
	}

}
