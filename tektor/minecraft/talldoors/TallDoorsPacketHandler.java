package tektor.minecraft.talldoors;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.AxisAlignedBB;
import tektor.minecraft.talldoors.container.KeyMakerGuiContainer;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorModuleWorkbenchTileEntity;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.MosaicTileEntity;
import tektor.minecraft.talldoors.entities.workbenches.KeyMaker;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class TallDoorsPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals("TallDoors")) {
			handle(packet, player);
		} else if (packet.channel.equals("TallDoors_Mosaic")) {
			handleMosaic(packet, player);
		} else if (packet.channel.equals("TallDoors2")) {
			handleKey(packet, player);
		}

	}

	private void handleKey(Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));
		int x, y, z;
		boolean b;
		String chosen;

		try {
			chosen = inputStream.readUTF();
			b = inputStream.readBoolean();
			x = inputStream.readInt();
			y = inputStream.readInt();
			z = inputStream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		EntityPlayerMP play = (EntityPlayerMP) player;
		List<Entity> list = play.worldObj.getEntitiesWithinAABB(Entity.class,
				AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1));
		if (!list.isEmpty()) {
			for (Entity ent : list) {
				if (ent instanceof KeyMaker) {
					((KeyMaker) ent).pressKey(chosen, b);
				}
			}

		}

	}

	private void handleMosaic(Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));
		int corX, corY, corZ;
		String chosen;

		try {
			chosen = inputStream.readUTF();
			corX = inputStream.readInt();
			corY = inputStream.readInt();
			corZ = inputStream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		EntityPlayerMP play = (EntityPlayerMP) player;
		if (play.inventory.getCurrentItem().itemID == TallDoorsBase.mosaicTool.itemID) {
			if (play.worldObj.getBlockTileEntity(corX, corY, corZ) instanceof MosaicTileEntity) {
				MosaicTileEntity ent = (MosaicTileEntity) play.worldObj
						.getBlockTileEntity(corX, corY, corZ);
				ent.setIcon(chosen);
			}
		} else {
			play.inventory.getCurrentItem().stackTagCompound = new NBTTagCompound();
			play.inventory.getCurrentItem().stackTagCompound.setString(
					"chosen", chosen);
		}

	}

	private void handle(Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));
		
		String type = null;
		try {
			type = inputStream.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if ("drawbridge".equals(type)) {
			int x, y, z, s, corX, corY, corZ;
			try {
				x = inputStream.readInt();
				y = inputStream.readInt();
				z = inputStream.readInt();
				s = inputStream.readInt();
				corX = inputStream.readInt();
				corY = inputStream.readInt();
				corZ = inputStream.readInt();

			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			EntityPlayerMP play = (EntityPlayerMP) player;
			if (play.worldObj.getBlockTileEntity(corX, corY, corZ) instanceof DrawbridgeWorkbenchTileEntity) {
				DrawbridgeWorkbenchTileEntity ent = (DrawbridgeWorkbenchTileEntity) play.worldObj
						.getBlockTileEntity(corX, corY, corZ);
				ent.produce(x, y, z, s);
			}
		}
		else if("direction".equals(type)){
			int x,y;
			int corX,corY,corZ;
			
			try {
				corX = inputStream.readInt();
				corY = inputStream.readInt();
				corZ = inputStream.readInt();
				x = inputStream.readInt();
				y = inputStream.readInt();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			EntityPlayerMP play = (EntityPlayerMP) player;
			if (play.worldObj.getBlockTileEntity(corX, corY, corZ) instanceof ModuleAssemblerTileEntity) {
				ModuleAssemblerTileEntity ent = (ModuleAssemblerTileEntity) play.worldObj
						.getBlockTileEntity(corX, corY, corZ);
				ent.rearrange(x, y);
			}
		}
		else if("assembler".equals(type)){
			int x,y;
			int corX,corY,corZ;
			boolean left;
			
			try {
				corX = inputStream.readInt();
				corY = inputStream.readInt();
				corZ = inputStream.readInt();
				x = inputStream.readInt();
				y = inputStream.readInt();
				left = inputStream.readBoolean();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			EntityPlayerMP play = (EntityPlayerMP) player;
			if (play.worldObj.getBlockTileEntity(corX, corY, corZ) instanceof ModuleAssemblerTileEntity) {
				ModuleAssemblerTileEntity ent = (ModuleAssemblerTileEntity) play.worldObj
						.getBlockTileEntity(corX, corY, corZ);
				ent.produce(x,y,left);
			}
		}
		else if("module".equals(type)){
			int priority;
			int corX,corY,corZ;
			String chosen, mtype, texture1, texture2;
			
			try {
				corX = inputStream.readInt();
				corY = inputStream.readInt();
				corZ = inputStream.readInt();
				priority = inputStream.readInt();
				chosen = inputStream.readUTF();
				mtype = inputStream.readUTF();
				texture1 = inputStream.readUTF();
				texture2 = inputStream.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			EntityPlayerMP play = (EntityPlayerMP) player;
			if (play.worldObj.getBlockTileEntity(corX, corY, corZ) instanceof DoorModuleWorkbenchTileEntity) {
				DoorModuleWorkbenchTileEntity ent = (DoorModuleWorkbenchTileEntity) play.worldObj
						.getBlockTileEntity(corX, corY, corZ);
				ent.produce(priority, chosen, mtype,texture1,texture2);
			}
		}
	}

}
