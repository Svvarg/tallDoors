package tektor.minecraft.talldoors;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity;
import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class TallDoorsPacketHandler implements IPacketHandler {
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals("TallDoors")) {
			handle(packet, player);
		}

	}

	private void handle(Packet250CustomPayload packet, Player player) {
		DataInputStream inputStream = new DataInputStream(
				new ByteArrayInputStream(packet.data));
		int x, y, corX, corY, corZ;

		try {
			x = inputStream.readInt();
			y = inputStream.readInt();
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
			ent.produce(x,y);
		}

	}

}
