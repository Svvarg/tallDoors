package tektor.minecraft.talldoors.doorworkshop.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import tektor.minecraft.talldoors.doorworkshop.entity.DoorModuleWorkbenchTileEntity;
import tektor.minecraft.talldoors.packet.AbstractPacket;

public class DoorModuleWorkbenchPacket extends AbstractPacket{

	int corX,corY,corZ;
	int priority;
	String chosen;
	String type;
	String texture1,texture2;
	
	public DoorModuleWorkbenchPacket()
	{
		
	}
	
	public DoorModuleWorkbenchPacket(int cor1, int cor2, int cor3, int prio, String chosen, String type, String texture1, String texture2)
	{
		this.corX = cor1;
		this.corY = cor2;
		this.corZ = cor3;
		this.priority = prio;
		this.chosen = chosen;
		this.type = type;
		this.texture1 = texture1;
		this.texture2 = texture2;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(priority);
		ByteBufUtils.writeUTF8String(buffer, chosen);
		ByteBufUtils.writeUTF8String(buffer, type);
		buffer.writeInt(corX);
		buffer.writeInt(corY);
		buffer.writeInt(corZ);
		ByteBufUtils.writeUTF8String(buffer, texture1);
		ByteBufUtils.writeUTF8String(buffer, texture2);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.priority = buffer.readInt();
		this.chosen = ByteBufUtils.readUTF8String(buffer);
		this.type = ByteBufUtils.readUTF8String(buffer);
		this.corX = buffer.readInt();
		this.corY = buffer.readInt();
		this.corZ = buffer.readInt();
		this.texture1 = ByteBufUtils.readUTF8String(buffer);
		this.texture2 = ByteBufUtils.readUTF8String(buffer);
		
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		EntityPlayerMP play = (EntityPlayerMP) player;
		if (play.worldObj.getTileEntity(corX, corY, corZ) instanceof DoorModuleWorkbenchTileEntity) {
			DoorModuleWorkbenchTileEntity ent = (DoorModuleWorkbenchTileEntity) play.worldObj
					.getTileEntity(corX, corY, corZ);
			ent.produce(priority, chosen, type,texture1,texture2);
		}
		
	}

}
