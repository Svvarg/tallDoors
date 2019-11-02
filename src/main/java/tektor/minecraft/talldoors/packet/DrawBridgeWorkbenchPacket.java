package tektor.minecraft.talldoors.packet;

import tektor.minecraft.talldoors.entities.tileentities.DrawbridgeWorkbenchTileEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class DrawBridgeWorkbenchPacket extends AbstractPacket{

	int x, y, z, s, corX, corY, corZ;
	
	public DrawBridgeWorkbenchPacket()
	{
		
	}
	
	public DrawBridgeWorkbenchPacket(int a, int b, int c, int d, int e, int f, int g)
	{
		this.x = a;
		this.y = b;
		this.z = c;
		this.s = d;
		this.corX = e;
		this.corY = f;
		this.corZ = g;
	}
	
	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(s);
		buffer.writeInt(corX);
		buffer.writeInt(corY);
		buffer.writeInt(corZ);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
		this.s = buffer.readInt();
		this.corX = buffer.readInt();
		this.corY = buffer.readInt();
		this.corZ = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		EntityPlayerMP play = (EntityPlayerMP) player;
		if (play.worldObj.getTileEntity(corX, corY, corZ) instanceof DrawbridgeWorkbenchTileEntity) {
			DrawbridgeWorkbenchTileEntity ent = (DrawbridgeWorkbenchTileEntity) play.worldObj
					.getTileEntity(corX, corY, corZ);
			ent.produce(x, y, z, s);
		}
		
	}

}
