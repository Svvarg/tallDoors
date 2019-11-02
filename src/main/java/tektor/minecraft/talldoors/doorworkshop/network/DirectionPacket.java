package tektor.minecraft.talldoors.doorworkshop.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import tektor.minecraft.talldoors.doorworkshop.entity.ModuleAssemblerTileEntity;
import tektor.minecraft.talldoors.packet.AbstractPacket;

public class DirectionPacket extends AbstractPacket{
	
	public int x,y;
	public int corX,corY,corZ;
	
	public DirectionPacket(){
		
	}
	
	public DirectionPacket(int cor1, int cor2, int cor3, int d, int d2){
		this.corX = cor1;
		this.corY = cor2;
		this.corZ = cor3;
		this.x = d;
		this.y = d2;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		buffer.writeInt(corX);
		buffer.writeInt(corY);
		buffer.writeInt(corZ);
		buffer.writeInt(x);
		buffer.writeInt(y);
		
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
		this.corX = buffer.readInt();
		this.corY = buffer.readInt();
		this.corZ = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
			
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
		EntityPlayerMP play = (EntityPlayerMP) player;
		if (play.worldObj.getTileEntity(corX, corY, corZ) instanceof ModuleAssemblerTileEntity) {
			ModuleAssemblerTileEntity ent = (ModuleAssemblerTileEntity) play.worldObj
					.getTileEntity(corX, corY, corZ);
			ent.rearrange(x, y);
		}
		
	}

}
