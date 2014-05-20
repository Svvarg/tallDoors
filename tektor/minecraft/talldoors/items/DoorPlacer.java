package tektor.minecraft.talldoors.items;

import java.util.List;

import tektor.minecraft.talldoors.TallDoorsBase;
import tektor.minecraft.talldoors.entities.FenceGate1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.DarkMetalEntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.EntranceDoor3;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor1;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor2;
import tektor.minecraft.talldoors.entities.doors_width2.MetalEntranceDoor3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class DoorPlacer extends Item {

	private IIcon[] icon = new IIcon[20];

	public DoorPlacer() {
		super();
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setCreativeTab(TallDoorsBase.tabTallDoors);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		return icon[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		icon[0] = par1IconRegister.registerIcon("tallDoors:rightentranceDoor1");
		icon[1] = par1IconRegister.registerIcon("tallDoors:leftentranceDoor1");
		icon[2] = par1IconRegister.registerIcon("tallDoors:rightentranceDoor2");
		icon[3] = par1IconRegister.registerIcon("tallDoors:leftentranceDoor2");
		icon[4] = par1IconRegister.registerIcon("tallDoors:rightentranceDoor3");
		icon[5] = par1IconRegister.registerIcon("tallDoors:leftentranceDoor3");
		icon[6] = par1IconRegister.registerIcon("tallDoors:fenceGate1");
		icon[7] = par1IconRegister.registerIcon("tallDoors:fenceGate1");
		icon[8] = par1IconRegister.registerIcon("tallDoors:rightMetalEntranceDoor1");
		icon[9] = par1IconRegister.registerIcon("tallDoors:leftMetalEntranceDoor1");
		icon[10] = par1IconRegister.registerIcon("tallDoors:rightDarkMetalEntranceDoor1");
		icon[11] = par1IconRegister.registerIcon("tallDoors:leftDarkMetalEntranceDoor1");
		icon[12] = par1IconRegister.registerIcon("tallDoors:rightDarkMetalEntranceDoor2");
		icon[13] = par1IconRegister.registerIcon("tallDoors:leftDarkMetalEntranceDoor2");
		icon[14] = par1IconRegister.registerIcon("tallDoors:rightDarkMetalEntranceDoor3");
		icon[15] = par1IconRegister.registerIcon("tallDoors:leftDarkMetalEntranceDoor3");
		icon[16] = par1IconRegister.registerIcon("tallDoors:rightMetalEntranceDoor2");
		icon[17] = par1IconRegister.registerIcon("tallDoors:leftMetalEntranceDoor2");
		icon[18] = par1IconRegister.registerIcon("tallDoors:rightMetalEntranceDoor3");
		icon[19] = par1IconRegister.registerIcon("tallDoors:leftMetalEntranceDoor3");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 0:
			return "rightentranceDoor1";
		case 1:
			return "leftentranceDoor1";
		case 2:
			return "rightentranceDoor2";
		case 3:
			return "leftentranceDoor2";
		case 4:
			return "rightentranceDoor3";
		case 5:
			return "leftentranceDoor3";
		case 6:
			return "rightFenceGate1";
		case 7:
			return "leftFenceGate1";
		case 8:
			return "rightMetalEntranceDoor1";
		case 9: 
			return "leftMetalEntranceDoor1";
		case 10:
			return "rightDarkMetalEntranceDoor1";
		case 11: 
			return "leftDarkMetalEntranceDoor1";
		case 12:
			return "rightDarkMetalEntranceDoor2";
		case 13: 
			return "leftDarkMetalEntranceDoor2";
		case 14:
			return "rightDarkMetalEntranceDoor3";
		case 15: 
			return "leftDarkMetalEntranceDoor3";
		case 16:
			return "rightMetalEntranceDoor2";
		case 17: 
			return "leftMetalEntranceDoor2";
		case 18:
			return "rightMetalEntranceDoor3";
		case 19: 
			return "leftMetalEntranceDoor3";
		default:
			return "??";
		}

	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack) {
		switch (par1ItemStack.getItemDamage()) {
		case 0:
			return "Right Entrance Door";
		case 1:
			return "Left Entrance Door";
		case 2:
			return "Right Entrance Door Size 5";
		case 3:
			return "Left Entrance Door Size 5";
		case 4:
			return "Right Entrance Door Size 6";
		case 5:
			return "Left Entrance Door Size 6";
		case 6:
			return "Right Fence Door";
		case 7:
			return "Left Fence Door";
		case 8:
			return "Right Metal Entrance Door";
		case 9: 
			return "Left Metal Entrance Door";
		case 10:
			return "Right Dark Metal Entrance Door";
		case 11: 
			return "Left Dark Metal Entrance Door";	
		case 12:
			return "Right Dark Metal Entrance Door Size 5";
		case 13: 
			return "Left Dark Metal Entrance Door Size 5";	
		case 14:
			return "Right Dark Metal Entrance Door Size 6";
		case 15: 
			return "Left Dark Metal Entrance Door Size 6";
		case 16:
			return "Right Metal Entrance Door Size 5";
		case 17: 
			return "Left Metal Entrance Door Size 5";
		case 18:
			return "Right Metal Entrance Door Size 6";
		case 19: 
			return "Left Metal Entrance Door Size 6";
		default:
			return "??";
		}
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {

		if (!par3World.isRemote) {
			
			int var24 = MathHelper
					.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

			
			if (par1ItemStack.getItemDamage() == 0) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 4, false, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				EntranceDoor1 door = new EntranceDoor1(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);
			} else if (par1ItemStack.getItemDamage() == 1) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 4, true, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				EntranceDoor1 door = new EntranceDoor1(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 2) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 5, false, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				EntranceDoor2 door = new EntranceDoor2(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 3) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 5, true, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				EntranceDoor2 door = new EntranceDoor2(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 4) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 6, false, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				EntranceDoor3 door = new EntranceDoor3(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 5) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 6, true, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				EntranceDoor3 door = new EntranceDoor3(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 6) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 1, false, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				FenceGate1 door = new FenceGate1(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 7) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 1, true, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				FenceGate1 door = new FenceGate1(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			
			else if (par1ItemStack.getItemDamage() == 8) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 4, false, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				MetalEntranceDoor1 door = new MetalEntranceDoor1(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 9) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 4, true, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				MetalEntranceDoor1 door = new MetalEntranceDoor1(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			
			else if (par1ItemStack.getItemDamage() == 10) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 4, false, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				DarkMetalEntranceDoor1 door = new DarkMetalEntranceDoor1(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 11) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 4, true, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				DarkMetalEntranceDoor1 door = new DarkMetalEntranceDoor1(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 12) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 5, false, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				DarkMetalEntranceDoor2 door = new DarkMetalEntranceDoor2(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 13) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 5, true, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				DarkMetalEntranceDoor2 door = new DarkMetalEntranceDoor2(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 14) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 6, false, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				DarkMetalEntranceDoor3 door = new DarkMetalEntranceDoor3(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 15) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 6, true, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				DarkMetalEntranceDoor3 door = new DarkMetalEntranceDoor3(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 16) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 5, false, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				MetalEntranceDoor2 door = new MetalEntranceDoor2(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 17) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 5, true, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				MetalEntranceDoor2 door = new MetalEntranceDoor2(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 18) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 6, false, var24)){
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				MetalEntranceDoor3 door = new MetalEntranceDoor3(par3World);
				door.setOrientation(false, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			else if (par1ItemStack.getItemDamage() == 19) {
				if(!checkFree(par3World,par4,par5+1,par6, 2, 6, true, var24)) {
					par2EntityPlayer.addChatMessage(new ChatComponentText("A voice whispers to you: There is not enough space for this"));
					return false;
				}
				MetalEntranceDoor3 door = new MetalEntranceDoor3(par3World);
				door.setOrientation(true, var24);
				door.setPosition(par4, par5 + 1, par6);

				par3World.spawnEntityInWorld(door);

			}
			--par1ItemStack.stackSize;
		}
		return true;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item par1, CreativeTabs tab, List subItems) {

		subItems.add(new ItemStack(this, 1, 0));
		subItems.add(new ItemStack(this, 1, 1));
		subItems.add(new ItemStack(this, 1, 2));
		subItems.add(new ItemStack(this, 1, 3));
		subItems.add(new ItemStack(this, 1, 4));
		subItems.add(new ItemStack(this, 1, 5));
		subItems.add(new ItemStack(this, 1, 6));
		subItems.add(new ItemStack(this, 1, 7));
		subItems.add(new ItemStack(this, 1, 8));
		subItems.add(new ItemStack(this, 1, 9));
		subItems.add(new ItemStack(this, 1, 10));
		subItems.add(new ItemStack(this, 1, 11));
		subItems.add(new ItemStack(this, 1, 12));
		subItems.add(new ItemStack(this, 1, 13));
		subItems.add(new ItemStack(this, 1, 14));
		subItems.add(new ItemStack(this, 1, 15));
		subItems.add(new ItemStack(this, 1, 16));
		subItems.add(new ItemStack(this, 1, 17));
		subItems.add(new ItemStack(this, 1, 18));
		subItems.add(new ItemStack(this, 1, 19));
	}
	
	private boolean checkFree(World world, int posX, int posY, int posZ, int width, int height, boolean left, int orientation)
	{
		if(left)
		{
			for(int i = 0; i < width; i++)
			{
				for(int k = 0; k<height; k++)
				{
					switch(orientation)
					{
					case 0: if(!world.isAirBlock(posX-i, posY+k, posZ)) return false;
					else break;
					case 1: if(!world.isAirBlock(posX, posY+k, posZ-i)) return false;
					else break;
					case 2: if(!world.isAirBlock(posX+i, posY+k, posZ)) return false;
					else break;
					case 3: if(!world.isAirBlock(posX, posY+k, posZ+i)) return false;
					else break;
					
					}
					
				}
			}
		}
		else
		{
			for(int i = 0; i < width; i++)
			{
				for(int k = 0; k<height; k++)
				{
					switch(orientation)
					{
					case 0: if(!world.isAirBlock(posX+i, posY+k, posZ)) return false;
					else break;
					case 1: if(!world.isAirBlock(posX, posY+k, posZ+i)) return false;
					else break;
					case 2: if(!world.isAirBlock(posX-i, posY+k, posZ)) return false;
					else break;
					case 3: if(!world.isAirBlock(posX, posY+k, posZ-i)) return false;
					else break;
					
					}
					
				}
			}
		}
		return true;
	}
}
